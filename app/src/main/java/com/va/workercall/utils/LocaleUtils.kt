package com.va.workercall.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.va.workercall.common.Constant
import com.va.workercall.data.local.SharedPreferenceHelper
import com.va.workercall.ui.main.MainActivity
import com.va.workercall.utils.Utils.isAtLeastSdkVersion
import timber.log.Timber
import java.util.*

object LocaleUtils {
    fun applyLocale(context: Context) {
        val preferences = PreferenceManager
            .getDefaultSharedPreferences(context)
        var localeString =
            preferences.getString(Constant.PREF_SETTING_LANGUAGE, Constant.LANGUAGE_EN)
        if (TextUtils.isEmpty(localeString)) {
            localeString = Constant.LANGUAGE_EN
        }
        Timber.e("applyLocale $localeString")
        val newLocale = Locale(localeString)
        updateResource(context, newLocale)
        if (context !== context.applicationContext) {
            updateResource(context.applicationContext, newLocale)
        }
    }

    fun updateResource(context: Context, locale: Locale) {
        Locale.setDefault(locale)
        val resources = context.resources
        val current = getLocaleCompat(resources)
        if (current === locale) {
            return
        }
        val configuration = Configuration(resources.configuration)
        when {
            isAtLeastSdkVersion(Build.VERSION_CODES.N) -> {
                configuration.setLocale(locale)
            }
            isAtLeastSdkVersion(Build.VERSION_CODES.JELLY_BEAN_MR1) -> {
                configuration.setLocale(locale)
            }
            else -> {
                configuration.locale = locale
            }
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    fun getLocaleCompat(resources: Resources): Locale {
        val configuration = resources.configuration
        return if (isAtLeastSdkVersion(Build.VERSION_CODES.N)) configuration.locales[0] else configuration.locale
    }

    fun applyLocaleAndRestart(activity: Activity, localeString: String) {
        Timber.e("applyLocaleAndRestart $localeString")
        SharedPreferenceHelper.storeString(Constant.PREF_SETTING_LANGUAGE, localeString)
        applyLocale(activity)
        val intent = Intent(activity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
        ActivityCompat.finishAffinity(activity)
    }
}