package com.example.testtasksearchfortickets.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.FragmentStab2Binding

class Stab2Fragment : Fragment(R.layout.fragment_stab2) {
    private val binding: FragmentStab2Binding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backImageView.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }
}
