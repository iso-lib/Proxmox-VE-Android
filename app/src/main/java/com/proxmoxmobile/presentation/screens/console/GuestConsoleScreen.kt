package com.proxmoxmobile.presentation.screens.console

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslCertificate
import android.net.http.SslError
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.proxmoxmobile.R
import com.proxmoxmobile.data.model.ServerConfig
import com.proxmoxmobile.data.security.CertificateFingerprint
import com.proxmoxmobile.presentation.viewmodel.MainViewModel
import java.io.ByteArrayInputStream
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

/**
 * Guest console implemented as a thin WebView shell around the console page
 * served by the Proxmox VE web interface itself (`/?console=...`).
 *
 * This deliberately adds no dependencies and no bundled assets: the server
 * ships xterm.js (for LXC / serial) and noVNC (for QEMU graphics), and the
 * WebView is a system component. The app's existing password-session ticket
 * is handed over as the PVEAuthCookie cookie, so the page opens already
 * authenticated. API-token sessions have no ticket and cannot use the web
 * console; that case is surfaced to the user instead.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuestConsoleScreen(
    node: String,
    vmid: Int,
    consoleType: String,
    viewModel: MainViewModel,
    navController: NavController
) {
    val server by viewModel.currentServer.collectAsState()
    val ticket by viewModel.authToken.collectAsState()

    var webView by remember { mutableStateOf<WebView?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var loadError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.console_title, vmid)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.dashboard_back)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            loadError = null
                            isLoading = true
                            webView?.reload()
                        },
                        enabled = ticket != null && server != null
                    ) {
                        Icon(
                            Icons.Outlined.Refresh,
                            contentDescription = stringResource(R.string.console_reload)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val currentServer = server
            val currentTicket = ticket
            when {
                currentServer == null -> {
                    Text(
                        text = stringResource(R.string.console_no_server),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                currentTicket == null -> {
                    Text(
                        text = stringResource(R.string.console_requires_password_login),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                loadError != null -> {
                    Text(
                        text = stringResource(R.string.console_load_failed, loadError!!),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    ConsoleWebView(
                        server = currentServer,
                        ticket = currentTicket,
                        node = node,
                        vmid = vmid,
                        consoleType = consoleType,
                        onWebViewReady = { webView = it },
                        onLoadingChanged = { isLoading = it },
                        onLoadError = { loadError = it }
                    )
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ConsoleWebView(
    server: ServerConfig,
    ticket: String,
    node: String,
    vmid: Int,
    consoleType: String,
    onWebViewReady: (WebView) -> Unit,
    onLoadingChanged: (Boolean) -> Unit,
    onLoadError: (String) -> Unit
) {
    val consoleUrl = remember(server, ticket, node, vmid, consoleType) {
        buildConsoleUrl(server, node, vmid, consoleType)
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.builtInZoomControls = true
                settings.displayZoomControls = false

                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        onLoadingChanged(true)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        onLoadingChanged(false)
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        if (request?.isForMainFrame == true) {
                            onLoadingChanged(false)
                            onLoadError(error?.description?.toString() ?: "unknown error")
                        }
                    }

                    override fun onReceivedSslError(
                        view: WebView?,
                        handler: android.webkit.SslErrorHandler?,
                        error: SslError?
                    ) {
                        if (shouldAcceptSslError(server, error)) {
                            handler?.proceed()
                        } else {
                            handler?.cancel()
                        }
                    }
                }

                // Hand the app's session ticket to the web page so it opens
                // already authenticated instead of showing the PVE login form.
                val baseUrl = baseUrlOf(server)
                val cookieManager = CookieManager.getInstance()
                cookieManager.setAcceptCookie(true)
                cookieManager.setAcceptThirdPartyCookies(this, true)
                val secureFlag = if (server.useHttps) "; Secure" else ""
                cookieManager.setCookie(baseUrl, "PVEAuthCookie=$ticket; path=/$secureFlag")
                cookieManager.flush()

                onWebViewReady(this)
                loadUrl(consoleUrl)
            }
        },
        onRelease = { it.destroy() }
    )
}

private fun baseUrlOf(server: ServerConfig): String {
    val protocol = if (server.useHttps) "https" else "http"
    return "$protocol://${server.host}:${server.port}"
}

private fun buildConsoleUrl(
    server: ServerConfig,
    node: String,
    vmid: Int,
    consoleType: String
): String {
    val encodedNode = URLEncoder.encode(node, StandardCharsets.UTF_8.toString())
    return if (consoleType == "lxc") {
        "${baseUrlOf(server)}/?console=lxc&vmid=$vmid&node=$encodedNode&xtermjs=1"
    } else {
        "${baseUrlOf(server)}/?console=kvm&vmid=$vmid&node=$encodedNode&novnc=1&resize=scale"
    }
}

/**
 * Mirrors the API client's TLS policy for the WebView:
 * - a pinned certificate fingerprint always wins (proceed only on match)
 * - otherwise proceed when the user disabled SSL verification for this server
 * - strict verification keeps the platform default (cancel)
 */
private fun shouldAcceptSslError(server: ServerConfig, error: SslError?): Boolean {
    if (error == null) return false
    val pinned = CertificateFingerprint.normalize(server.certificateFingerprint)
    if (pinned != null) {
        val certificate = extractX509Certificate(error.certificate) ?: return false
        return CertificateFingerprint.matches(certificate, pinned)
    }
    return !server.verifySsl
}

private fun extractX509Certificate(sslCertificate: SslCertificate?): X509Certificate? {
    if (sslCertificate == null) return null
    return try {
        val bytes = SslCertificate.saveState(sslCertificate)
            .getByteArray("x509-certificate") ?: return null
        CertificateFactory.getInstance("X.509")
            .generateCertificate(ByteArrayInputStream(bytes)) as? X509Certificate
    } catch (e: Exception) {
        null
    }
}
