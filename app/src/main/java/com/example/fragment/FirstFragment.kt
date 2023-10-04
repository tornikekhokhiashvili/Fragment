package com.example.fragment
import android.content.Context
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
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentListener")
        }
    }
    private lateinit var change:Button
    private lateinit var swap:Button
    private var listener: FragmentListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    change.setOnClickListener {
        listener?.onChangeBackgroundColors()
    }
        swap.setOnClickListener {
            listener?.onSwapFragments()
        }

    }
    private fun init(){
        change = view?.findViewById(R.id.Change_Color)!!
        swap = view?.findViewById(R.id.Swap_Fragment)!!
    }
    interface FragmentListener {
        fun onSwapFragments()
        fun onChangeBackgroundColors()
    }

}