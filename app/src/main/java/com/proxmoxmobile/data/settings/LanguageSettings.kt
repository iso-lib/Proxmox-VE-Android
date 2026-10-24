package com.proxmoxmobile.data.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.LocaleList
import java.util.Locale

/**
 * In-app language selection.
 *
 * The app ships string resources for English (default), German, Spanish,
 * Simplified Chinese, French, Italian, Portuguese, Russian, Japanese and
 * Korean. By default it follows the system language; the user can
 * override it on the login screen. The choice is stored in plain
 * SharedPreferences and applied by wrapping the activity base context in
 * [MainActivity.attachBaseContext], so it takes effect for the whole app and
 * survives restarts.
 */
enum class AppLanguage(val tag: String, val displayName: String) {
    SYSTEM("", ""),
    SIMPLIFIED_CHINESE("zh-CN", "简体中文"),
    ENGLISH("en", "English"),
    GERMAN("de", "Deutsch"),
    SPANISH("es", "Español"),
    FRENCH("fr", "Français"),
    ITALIAN("it", "Italiano"),
    PORTUGUESE("pt", "Português"),
    RUSSIAN("ru", "Русский"),
    JAPANESE("ja", "日本語"),
    KOREAN("ko", "한국어");

    companion object {
        fun fromTag(tag: String?): AppLanguage =
            values().firstOrNull { it.tag == tag } ?: SYSTEM
    }
}

object LanguageSettings {
    private const val PREFS_NAME = "app_settings"
    private const val KEY_LANGUAGE = "app_language"

    fun getSaved(context: Context): AppLanguage {
        val tag = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LANGUAGE, null)
        return AppLanguage.fromTag(tag)
    }

    fun save(context: Context, language: AppLanguage) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_LANGUAGE, language.tag)
            .apply()
    }

    /** Returns a context whose resources resolve in the saved language. */
    fun wrapContext(base: Context): Context {
        val language = getSaved(base)
        if (language == AppLanguage.SYSTEM) {
            return base
        }
        val locale = Locale.forLanguageTag(language.tag)
        Locale.setDefault(locale)
        val configuration = Configuration(base.resources.configuration)
        configuration.setLocale(locale)
        configuration.setLocales(LocaleList(locale))
        return base.createConfigurationContext(configuration)
    }

    fun findActivity(context: Context): Activity? {
        var current: Context? = context
        while (current is ContextWrapper) {
            if (current is Activity) {
                return current
            }
            current = current.baseContext
        }
        return current as? Activity
    }
}
