package com.example.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
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
            if(!isFirstLaunch){
                supportFragmentManager.commit {
                    replace(binding.fragment2Container.id,ThirdFragment())
                        replace(binding.fragment3Container.id,SecondFragment())
                }
            }
        }


    }

    override fun onSwapFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        if (isFirstLaunch) {
            transaction.replace(R.id.fragment2_container,ThirdFragment())
                .replace(R.id.fragment3_container,SecondFragment())
                .commit()

        } else {
            transaction.replace(R.id.fragment2_container,SecondFragment())
                .replace(R.id.fragment3_container,ThirdFragment())
                .commit()
        }

        isFirstLaunch = !isFirstLaunch
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