package com.mrtwon.humorcucknoris.FragmentWebView

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.mrtwon.humorcucknoris.R

class FragmentWebView: Fragment() {
    lateinit var web_view: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        web_view = view.findViewById(R.id.web_view)
        if(savedInstanceState == null) {
            init(web_view)
            web_view.loadUrl("https://icndb.com/api/")
        }else{
            web_view.restoreState(savedInstanceState)
        }
        super.onViewCreated(view, savedInstanceState)
    }


    // save state for webview
    override fun onSaveInstanceState(outState: Bundle) {
        web_view.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    // init to property for webView
    @SuppressLint("SetJavaScriptEnabled")
    fun init(wv: WebView){
        wv.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.mediaPlaybackRequiresUserGesture = false
        }
    }
}