package com.appify.gomoneyappchallenge.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appify.gomoneyappchallenge.adapters.MatchesAdapter
import com.appify.gomoneyappchallenge.databinding.FragmentTodayMatchesBinding
import com.appify.gomoneyappchallenge.viewmodels.MatchViewModel

class TodayMatchesFragment : Fragment() {
    private lateinit var viewmodel: MatchViewModel
    private var _binding:FragmentTodayMatchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTodayMatchesBinding.inflate(inflater, container, false)
        val recyclerview = binding.recyclerview
        val retryBtn = binding.retryBtn
        val msgText = binding.msgText
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewmodel = ViewModelProvider(this).get(MatchViewModel::class.java)
        getMatches()
        viewmodel.matchLiveData.observe(viewLifecycleOwner, {
            if (it.size>0) {
                recyclerview.visibility = View.VISIBLE
                retryBtn.visibility = View.GONE
                msgText.visibility = View.GONE
            } else {
                retryBtn.visibility = View.VISIBLE
                msgText.visibility = View.VISIBLE
                recyclerview.visibility = View.GONE
            }

            recyclerview.adapter = MatchesAdapter(it)
        })

        retryBtn.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "Retrying call", Toast.LENGTH_LONG).show()
            getMatches()
        })

        return binding.root
    }

    fun getMatches() {
        viewmodel.getMatches()
    }
}