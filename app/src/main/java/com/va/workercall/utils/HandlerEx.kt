package com.va.workercall.utils

import android.os.Handler
import android.os.Looper
import timber.log.Timber

fun saveDelay(delayMillis: Long = 0, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        try {
            action()
        } catch (e: Exception) {
            Timber.e("safeDelay: $e")
        }
    }, delayMillis)
}