package com.example.debugwebviewapp

import android.webkit.WebView
import android.webkit.WebViewClient

class AppWebViewClient: WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            view?.loadUrl(url)
        }
        return true
    }
}