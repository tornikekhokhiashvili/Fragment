package com.example.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    private var _binding:FragmentSecondBinding?=null
    private val binding get() = _binding!!
    private var backGroundColor:Int = Color.WHITE
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater,container,false)
    return binding.root
    }
    fun changeBackgroundColor(color:Int){
        backGroundColor = color
        binding.root.setBackgroundColor(color)
    }
    fun getBackgroundColor():Int{
        return backGroundColor
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}