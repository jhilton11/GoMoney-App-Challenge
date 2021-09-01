package com.appify.gomoneyappchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.appify.gomoneyappchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolBar
        val bottomNavView = binding.bottomNavView

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.container)
        bottomNavView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }
}