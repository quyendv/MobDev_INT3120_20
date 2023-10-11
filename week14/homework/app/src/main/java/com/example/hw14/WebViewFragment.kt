package com.example.hw14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment

class WebViewFragment : Fragment() {
    private lateinit var webView: WebView
    private val url = "https://quyendv.github.io/Self-Introduction/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        webView = view.findViewById(R.id.webView)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Cấu hình WebViewClient để xử lý các sự kiện liên quan đến trang web
        webView.webViewClient = MyWebViewClient()

        // Load URL
        webView.loadUrl(url)

        return view
    }

    // Định nghĩa WebViewClient để xử lý sự kiện liên quan đến trang web
    private inner class MyWebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            // Xử lý tương tự việc đang tải URL
            return false
        }
    }
}
