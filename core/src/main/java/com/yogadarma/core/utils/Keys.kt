package com.yogadarma.core.utils

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun baseUrl(): String

    external fun apiKey(): String
}