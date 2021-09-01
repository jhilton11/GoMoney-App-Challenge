package com.appify.gomoneyappchallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appify.gomoneyappchallenge.adapters.CompetitionAdapter
import com.appify.gomoneyappchallenge.databinding.FragmentCompetitionBinding
import com.appify.gomoneyappchallenge.databinding.FragmentTodayMatchesBinding
import com.appify.gomoneyappchallenge.viewmodels.MatchViewModel

class CompetitionFragment : Fragment() {
    private lateinit var viewmodel: MatchViewModel
    private var _binding: FragmentCompetitionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompetitionBinding.inflate(inflater, container, false)
        val recyclerview = binding.recyclerview
        val retryBtn = binding.retryBtn
        val msgText = binding.msgText
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewmodel = ViewModelProvider(requireActivity()).get(MatchViewModel::class.java)
        getCompetitions()
        viewmodel.competitionLiveData.observe(requireActivity(), Observer {
            if (it.size>0) {
                recyclerview.visibility = View.VISIBLE
                retryBtn.visibility = View.GONE
                msgText.visibility = View.GONE
            } else {
                retryBtn.visibility = View.VISIBLE
                msgText.visibility = View.VISIBLE
                recyclerview.visibility = View.GONE
            }
            recyclerview.adapter = CompetitionAdapter(it)
        })

        retryBtn.setOnClickListener(View.OnClickListener {
            getCompetitions()
        })

        return binding.root
    }

    private fun getCompetitions() {
        viewmodel.getCompetitions()
    }

}