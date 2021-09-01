package com.appify.gomoneyappchallenge.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.appify.gomoneyappchallenge.adapters.TeamsAdapter
import com.appify.gomoneyappchallenge.databinding.FragmentTeamsBinding
import com.appify.gomoneyappchallenge.viewmodels.CompetitionViewModel

class TeamsFragment : Fragment() {
    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CompetitionViewModel
    private lateinit var id:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)

        Log.d("Fragment", "In the teams fragment")
        val recyclerview = binding.recyclerview
        val retryBtn = binding.retryBtn
        val msgText = binding.msgText

        recyclerview.layoutManager = GridLayoutManager(requireActivity(), 3)
        recyclerview.setHasFixedSize(true)

        viewModel = ViewModelProvider(requireActivity()).get(CompetitionViewModel::class.java)
        viewModel.idLiveData.observe(requireActivity(), Observer {
            id = it
        })
        getTeams()
        viewModel.teamsLiveData.observe(requireActivity(), Observer {
            if (it.size>0) {
                recyclerview.visibility = View.VISIBLE
                retryBtn.visibility = View.GONE
                msgText.visibility = View.GONE
            } else {
                retryBtn.visibility = View.VISIBLE
                msgText.visibility = View.VISIBLE
                recyclerview.visibility = View.GONE
            }
            recyclerview.adapter = TeamsAdapter(it)
        })

        return binding.root
    }

    fun getTeams() {
        viewModel.getTeams(id)
    }

}