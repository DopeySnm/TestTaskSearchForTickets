package com.example.testtasksearchfortickets.presenter

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.setOnItemSelectedListener {
            changeTab(it.itemId)
            true
        }
        changeTab(R.id.select_air_tickets)
    }

    private fun changeTab(@IdRes id: Int){
        val navHostId = binding.navHost.id
        val transaction = childFragmentManager.beginTransaction()
        when (id) {
            R.id.select_air_tickets -> {
                transaction.replace(navHostId, MainScreenFragment.newInstance())
            }
            R.id.select_hotels -> {
                transaction.replace(navHostId, StabFragment.newInstance())
            }
            R.id.select_geolocation -> {
                transaction.replace(navHostId, StabFragment.newInstance())
            }
            R.id.select_subscriptions -> {
                transaction.replace(navHostId, StabFragment.newInstance())
            }
            R.id.select_profile -> {
                transaction.replace(navHostId, StabFragment.newInstance())
            }
        }
        transaction.commit()
    }
}
