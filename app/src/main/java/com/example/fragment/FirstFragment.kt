package com.example.fragment
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.FragmentFirstBinding
import kotlin.random.Random

class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
    binding.ChangeColor.setOnClickListener {
        val randomColor1=generateRandomColor()
        val randomColor2=generateRandomColor()
        val mainActivity =requireActivity() as MainActivity
        mainActivity.onChangeFragmentColor(randomColor1,randomColor2)
    }
    binding.SwapFragment.setOnClickListener {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.onSwapFragmentsPosition()
    }
        return binding.root
    }
    private fun generateRandomColor(): Int {
        return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }
}
