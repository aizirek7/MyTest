package com.example.mytest.fargments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.mytest.R
import com.example.mytest.databinding.FragmentMainBinding
import com.example.mytest.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url!!)
                return true
            }
        }
        CookieManager.getInstance().setAcceptThirdPartyCookies(binding.webView,true)

        binding.webView.apply {
            loadUrl(loadSharedPref(Utils.URL_FROM_URL))
            Log.i(Utils.TAG,loadSharedPref(Utils.URL_FROM_URL))
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }
    fun loadSharedPref(key: String): String {
        val sharedPreferences =
            requireContext().getSharedPreferences(Utils.ShearedPref, Context.MODE_PRIVATE)
        val url = sharedPreferences.getString(key, Utils.KEY_FOR_SP)
        return url.toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}