package com.example.fragment

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var fragment1: FirstFragment
    private lateinit var fragment2: SecondFragment
    private lateinit var fragment3: ThirdFragment
    private var isSwapped = false
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragment1 = FirstFragment()
        fragment2 = SecondFragment()
        fragment3 = ThirdFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment1_container, fragment1)
            .replace(R.id.fragment2_container, fragment2)
            .replace(R.id.fragment3_container, fragment3)
            .commit()
    }

    fun onChangeFragmentColor(color1: Int, color2: Int) {
        fragment2.changeBackgroundColor(color1)
        fragment3.changeBackgroundColor(color2)
    }

    fun onSwapFragmentsPosition() {
        val fragment2Container = fragment2.view
        val fragment3Container = fragment3.view
        val parent2 = fragment2Container?.parent as? ViewGroup
        val parent3 = fragment3Container?.parent as? ViewGroup
        parent2?.removeView(fragment2Container)
        parent3?.removeView(fragment3Container)
        parent2?.addView(fragment3Container)
        parent3?.addView(fragment2Container)
        isSwapped = !isSwapped
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("color1", fragment2.getBackgroundColor())
        outState.putInt("color2", fragment3.getBackgroundColor())
        outState.putBoolean("isSwapped", isSwapped)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val color1 = savedInstanceState.getInt("color1")
        val color2 = savedInstanceState.getInt("color2")
        onChangeFragmentColor(color1, color2)

        val fragmentSwapFlag = savedInstanceState.getBoolean("isSwapped")
        if (fragmentSwapFlag) {
            onSwapFragmentsPosition()
        }
    }
}