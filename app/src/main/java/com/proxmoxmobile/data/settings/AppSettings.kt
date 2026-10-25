package com.proxmoxmobile.data.settings

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Persisted app-wide settings edited on the settings screen.
 *
 * Values are stored in SharedPreferences (the same "app_settings" file
 * LanguageSettings uses, under different keys) and mirrored in StateFlows
 * so screens recompose or restart their polling loops when a value
 * changes, without an activity restart. Defaults preserve the previous
 * behaviour: dark theme on, 30 second refresh, auto-login off.
 */
object AppSettings {
    private const val PREFS_NAME = "app_settings"
    private const val KEY_DARK_MODE = "dark_mode"
    private const val KEY_REFRESH_INTERVAL_SECONDS = "refresh_interval_seconds"
    private const val KEY_AUTO_LOGIN = "auto_login"

    const val DEFAULT_DARK_MODE = true
    const val DEFAULT_REFRESH_INTERVAL_SECONDS = 30
    const val DEFAULT_AUTO_LOGIN = false

    private val _darkMode = MutableStateFlow(DEFAULT_DARK_MODE)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()

    private val _refreshIntervalSeconds = MutableStateFlow(DEFAULT_REFRESH_INTERVAL_SECONDS)
    val refreshIntervalSeconds: StateFlow<Int> = _refreshIntervalSeconds.asStateFlow()

    private val _autoLogin = MutableStateFlow(DEFAULT_AUTO_LOGIN)
    val autoLogin: StateFlow<Boolean> = _autoLogin.asStateFlow()

    fun initialize(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        _darkMode.value = prefs.getBoolean(KEY_DARK_MODE, DEFAULT_DARK_MODE)
        _refreshIntervalSeconds.value =
            prefs.getInt(KEY_REFRESH_INTERVAL_SECONDS, DEFAULT_REFRESH_INTERVAL_SECONDS)
        _autoLogin.value = prefs.getBoolean(KEY_AUTO_LOGIN, DEFAULT_AUTO_LOGIN)
    }

    fun setDarkMode(context: Context, enabled: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit().putBoolean(KEY_DARK_MODE, enabled).apply()
        _darkMode.value = enabled
    }

    fun setRefreshIntervalSeconds(context: Context, seconds: Int) {
        val clamped = seconds.coerceIn(10, 60)
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit().putInt(KEY_REFRESH_INTERVAL_SECONDS, clamped).apply()
        _refreshIntervalSeconds.value = clamped
    }

    fun setAutoLogin(context: Context, enabled: Boolean) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit().putBoolean(KEY_AUTO_LOGIN, enabled).apply()
        _autoLogin.value = enabled
    }

    /** Synchronous read for places that run outside composition. */
    fun getAutoLogin(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_AUTO_LOGIN, DEFAULT_AUTO_LOGIN)
    }
}
