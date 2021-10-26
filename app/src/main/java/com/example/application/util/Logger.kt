package com.example.application.util

import android.util.Log

class Logger {
    companion object {
        fun d(text: String) {
            Log.d(null, text)
        }

        fun e(text: String) {
            Log.e(null, text)
        }
    }
}