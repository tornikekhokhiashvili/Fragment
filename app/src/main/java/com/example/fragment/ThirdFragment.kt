package com.example.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private var backgroundColor:Int = Color.WHITE
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
    fun changeBackgroundColor(color:Int) {
        backgroundColor=color
        binding.root.setBackgroundColor(color)
    }
    fun getBackgroundColor():Int{
        return backgroundColor
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}