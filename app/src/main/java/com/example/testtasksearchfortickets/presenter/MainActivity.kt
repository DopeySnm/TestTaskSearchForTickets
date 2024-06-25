package com.example.testtasksearchfortickets.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtasksearchfortickets.R
import com.example.testtasksearchfortickets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = (supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment)
            .navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
}
