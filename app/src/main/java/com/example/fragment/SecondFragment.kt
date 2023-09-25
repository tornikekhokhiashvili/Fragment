package com.example.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.random.Random

class SecondFragment : Fragment(R.layout.fragment_second) {
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    fun setRandomBackgroundColor() {
        val randomColor = getRandomColor()
     view?.setBackgroundColor(randomColor)

    }

    private fun getRandomColor(): Int {
        val random = Random.Default
        val color = android.graphics.Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        Log.d("ColorDebug", "Generated Color: $color")
        return color
    }
}