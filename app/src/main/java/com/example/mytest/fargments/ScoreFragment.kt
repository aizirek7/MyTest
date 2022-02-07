package com.example.mytest.fargments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytest.R
import com.example.mytest.databinding.FragmentScoreBinding
import kotlin.random.Random
import android.view.animation.RotateAnimation

import android.view.animation.Animation
import androidx.navigation.Navigation


class ScoreFragment : Fragment() {
    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!
    var random = Random
    var text = 1
    private var lastAngle = (-1).toFloat()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            text = 1
        } else {
            text = savedInstanceState.getInt("score", 0)
            Log.i(Utils.TAG, text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.scoreStartButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                spinBottle()
                binding.scoreText.text = text++.toString()
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun spinBottle() {
        val angle = (random.nextInt(3600 - 360) + 360).toFloat()

        // Центр вращения
        val pivotX: Float = (binding.scoreImageView.width / 2).toFloat()
        val pivotY: Float = (binding.scoreImageView.width / 2).toFloat()


        val animation = RotateAnimation(lastAngle, angle, pivotX, pivotY)
        lastAngle = angle
        animation.duration = 2500
        animation.fillAfter = true

        binding.scoreImageView.startAnimation(animation)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", text)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            val int = savedInstanceState.getInt("score", 0) - 1
            // Restore last state for checked position.
            binding.scoreText.text = int.toString()
        }
        else{
            text = (random.nextInt(3600 - 360) + 360)
        }
    }

//    if (savedInstanceState != null)
//    rand = savedInstanceState.getInt("rand");
//    else
//    rand = (int) (Math.random()*10000+1);
//    5

}

