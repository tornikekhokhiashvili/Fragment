package com.example.fragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class FirstFragment : Fragment(R.layout.fragment_first) {
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    private lateinit var secondFragment: SecondFragment
    private lateinit var thirdFragment: ThirdFragment
    private var areFragmentsSwapped = false
    private lateinit var change:Button
    private lateinit var swap:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        change.setOnClickListener {
            try {
                val secondFragment = requireFragmentManager().findFragmentById(R.id.fragment2_container) as SecondFragment
                val thirdFragment = requireFragmentManager().findFragmentById(R.id.fragment3_container) as ThirdFragment
                secondFragment.setRandomBackgroundColor()
                thirdFragment.setRandomBackgroundColor()
                Log.d("FirstFragment", "Colors changed successfully")
            } catch (e: Exception) {
                Log.e("FirstFragment", "Error changing colors: ${e.message}")
                e.printStackTrace()
            }
        }

        swap.setOnClickListener {
            Log.d("FirstFragment", "Swap button clicked")
            swapFragments()
        }

    }
    private fun init(){
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()
        change = view?.findViewById(R.id.Change_Color)!!
        swap = view?.findViewById(R.id.Swap_Fragment)!!
    }
    private fun swapFragments() {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (!areFragmentsSwapped) {
            if (!secondFragment.isAdded) {
                transaction.add(R.id.fragment3_container, secondFragment)
            }
            if (!thirdFragment.isAdded) {
                transaction.add(R.id.fragment2_container, thirdFragment)
            }
            transaction.show(secondFragment)
            transaction.show(thirdFragment)
            areFragmentsSwapped = true
        } else {
            transaction.hide(secondFragment)
            transaction.hide(thirdFragment)
            areFragmentsSwapped = false
        }

        transaction.commit()
    }
}