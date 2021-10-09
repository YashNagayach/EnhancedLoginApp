package com.example.yash_coding_task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*

class HomeScreenFragment : Fragment(R.layout.fragment_home) {
    private val hello = "Hello"

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.getUserName().observe(viewLifecycleOwner, { updatedValue ->
            textView?.text = "$hello $updatedValue"
        })
    }
}