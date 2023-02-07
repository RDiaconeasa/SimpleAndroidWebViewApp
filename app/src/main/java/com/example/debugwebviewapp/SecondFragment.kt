package com.example.debugwebviewapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.debugwebviewapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var _webView: WebView? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val webView get() = _webView!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _webView = view.findViewById(R.id.webview)

        createWebView()
    }

    override fun onPause() {
        super.onPause()
        toggleWebViewActivityState(false)
    }

    override fun onResume() {
        super.onResume()
        toggleWebViewActivityState(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        destroyWebView()

        _binding = null
        _webView = null
    }

    fun createWebView() {
        webView.webViewClient = AppWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(arguments?.getString("launchUrl").toString())
    }

    fun toggleWebViewActivityState(isActive: Boolean) {
        webView.settings.javaScriptEnabled = isActive

        if (isActive) {
            webView.resumeTimers()
        } else {
            webView.pauseTimers()
        }
    }

    fun destroyWebView() {
        webView?.destroy()
    }
}