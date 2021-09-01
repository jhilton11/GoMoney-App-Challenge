package com.appify.gomoneyappchallenge

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.appify.gomoneyappchallenge.adapters.ViewPagerAdapter
import com.appify.gomoneyappchallenge.databinding.ActivityCompetitionBinding
import com.appify.gomoneyappchallenge.viewmodels.CompetitionViewModel
import com.google.android.material.tabs.TabLayoutMediator

class CompetitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompetitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String? = intent.getStringExtra("id")
        val name: String? = intent.getStringExtra("name")
        val viewmodel = ViewModelProvider(this).get(CompetitionViewModel::class.java)
        id?.let { Log.d("id", it) }
        name?.let { Log.d("name", it) }
        viewmodel.idLiveData.value = id

        val toolbar = binding.toolBar
        val viewpager = binding.viewPager
        val tabLayout = binding.tabLayout

        toolbar.title = name
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        viewpager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Standings"
                1 -> tab.text = "Fixtures"
                2 -> tab.text = "Teams"
            }
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}