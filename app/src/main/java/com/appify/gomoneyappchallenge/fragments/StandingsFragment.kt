package com.appify.gomoneyappchallenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appify.gomoneyappchallenge.adapters.StandingsAdapter
import com.appify.gomoneyappchallenge.databinding.FragmentStandingsBinding
import com.appify.gomoneyappchallenge.viewmodels.CompetitionViewModel

class StandingsFragment : Fragment() {
    private lateinit var viewmodel: CompetitionViewModel
    private var _binding: FragmentStandingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStandingsBinding.inflate(inflater, container, false)

        val recyclerview = binding.recyclerview
        val retryBtn = binding.retryBtn
        val msgText = binding.msgText

        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewmodel = ViewModelProvider(requireActivity()).get(CompetitionViewModel::class.java)
        viewmodel.idLiveData.observe(requireActivity(), Observer {
            id = it
        })
        getStandings(id)
        viewmodel.standingsLiveData.observe(requireActivity(), Observer {
            if (it.size>0) {
                recyclerview.visibility = View.VISIBLE
                retryBtn.visibility = View.GONE
                msgText.visibility = View.GONE
            } else {
                retryBtn.visibility = View.VISIBLE
                msgText.visibility = View.VISIBLE
                recyclerview.visibility = View.GONE
            }
            recyclerview.adapter = StandingsAdapter(it)
        })

        retryBtn.setOnClickListener(View.OnClickListener {
            getStandings(id)
        })

        return binding.root
    }

    fun getStandings(id: String) {
        viewmodel.getStandings(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}