package com.example.mytest.fargments

import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mytest.R
import com.example.mytest.databinding.FragmentMainBinding
import com.example.mytest.databinding.FragmentPlugBinding


class PlugFragment : Fragment() {
    private var _binding: FragmentPlugBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlugBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        binding.plugButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                setFragment("score")
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setFragment(direction: String) {
        val action = PlugFragmentDirections.actionPlugFragmentToScoreFragment()
        Navigation.findNavController(binding.root).navigate(action)

    }


}