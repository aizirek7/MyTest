package com.example.mytest.fargments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mytest.R
import com.example.mytest.databinding.FragmentMainBinding
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import java.net.URL
import java.nio.charset.Charset


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        saveSharedPref(Utils.URL, Utils.KEY_FOR_SP)
        response()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun response() {
        Log.i(Utils.TAG, loadSharedPref(Utils.KEY_FOR_SP))
        try {
            Log.i("TAG", "https://hirelmany.online/RxZygwC2")
            val url = URL(loadSharedPref(Utils.KEY_FOR_SP)).readText(Charset.forName("ISO-8859-1"))
            Log.i(Utils.TAG, url)
            checkUrl(loadSharedPref(Utils.KEY_FOR_SP))
        } catch (e: HttpException) {
            Log.i("str", e.message.toString())
            setFragment("plug")
        } catch (e: Throwable) {
            Log.i("str", e.message.toString())
            setFragment("plug")
        }
    }

    private fun checkUrl(url: String){
        val url_from_url = URL(loadSharedPref(Utils.KEY_FOR_SP)).readText(Charset.forName("ISO-8859-1"))
        if (url_from_url.isNotEmpty()) {
            saveSharedPref(url_from_url, Utils.URL_FROM_URL)
            Log.i(Utils.TAG, "checkUrl")
            setFragment("web")
        }
        else{
            setFragment("plug")
        }

    }


    fun saveSharedPref(url: String, key: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences(Utils.ShearedPref, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(key, url)
        }.apply()
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

    fun setFragment(direction: String){
        if (direction == "plug"){
            val action = MainFragmentDirections.actionMainFragmentToPlugFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
        if (direction == "score"){
            val action = PlugFragmentDirections.actionPlugFragmentToScoreFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
        else if (direction == "web"){
            val action = MainFragmentDirections.actionMainFragmentToWebFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }
}