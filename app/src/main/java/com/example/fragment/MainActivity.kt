package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private lateinit var fragment1: FirstFragment
    private lateinit var fragment2: SecondFragment
    private lateinit var fragment3: ThirdFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment1 = FirstFragment()
        fragment2 = SecondFragment()
        fragment3 = ThirdFragment()
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

    }
}