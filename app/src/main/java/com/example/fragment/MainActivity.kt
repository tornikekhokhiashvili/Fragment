package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.fragment.app.commit
import com.example.fragment.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), FirstFragment.FragmentListener {
    private lateinit var fragment1: FirstFragment
    private lateinit var fragment2: SecondFragment
    private lateinit var fragment3: ThirdFragment
    private var color1 = 0
    private var color2 = 0
    private var isFirstLaunch = true
    private lateinit var binding:ActivityMainBinding
    private lateinit var change: Button
    private lateinit var swap: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragment1 = FirstFragment()
        fragment2 = SecondFragment()
        fragment3 = ThirdFragment()

        if(savedInstanceState == null)  {
            supportFragmentManager.commit {
                add(
                    R.id.fragment1_container,
                    fragment1
                )
            }
            supportFragmentManager.commit {
                add(
                    R.id.fragment2_container,
                    fragment2
                )
            }
            supportFragmentManager.commit {
                add(
                    R.id.fragment3_container,
                    fragment3
                )
            }

        } else {
            isFirstLaunch = savedInstanceState.getBoolean("isFirstLaunch")
            color1 = savedInstanceState.getInt("color1")
            color2 = savedInstanceState.getInt("color2")
            binding.fragment2Container.setBackgroundColor(color1)
            binding.fragment3Container.setBackgroundColor(color2)
        }


    }

    override fun onSwapFragments() {
        val fragment2View = findViewById<View>(R.id.fragment2_container)
        val fragment3View = findViewById<View>(R.id.fragment3_container)

        val animationDuration = 500L // You can adjust the duration as needed

        val fragment2X = fragment2View.x
        val fragment2Y = fragment2View.y

        val fragment3X = fragment3View.x
        val fragment3Y = fragment3View.y

        fragment2View.animate()
            .x(fragment3X)
            .y(fragment3Y)
            .setDuration(animationDuration)
            .withEndAction {
                // After the animation, swap the positions of the fragments in the layout
                supportFragmentManager.beginTransaction().apply {
                    detach(fragment2)
                    detach(fragment3)
                    attach(fragment2)
                    attach(fragment3)
                    commit()
                }

                // Reset the view positions
                fragment2View.x = fragment3X
                fragment2View.y = fragment3Y
            }
            .start()

        fragment3View.animate()
            .x(fragment2X)
            .y(fragment2Y)
            .setDuration(animationDuration)
            .start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isFirstLaunch", isFirstLaunch)
        outState.putInt("color1", color1)
        outState.putInt("color2", color2)
    }
    override fun onChangeBackgroundColors() {
       color1 = android.graphics.Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        color2 = android.graphics.Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
       binding.fragment2Container.setBackgroundColor(color1)
       binding.fragment3Container.setBackgroundColor(color2)
    }
}