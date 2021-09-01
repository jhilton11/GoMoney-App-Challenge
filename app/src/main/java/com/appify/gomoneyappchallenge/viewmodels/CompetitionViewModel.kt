package com.appify.gomoneyappchallenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appify.gomoneyappchallenge.repository.StandingsRepository
import com.appify.gomoneyappchallenge.repository.TeamsRepository

class CompetitionViewModel(application: Application) : AndroidViewModel(application) {
    val teamsRepository = TeamsRepository(application)
    val standingsRepository = StandingsRepository(application)
    val idLiveData: MutableLiveData<String> = MutableLiveData()

    fun getTeams(id: String) {
        teamsRepository.getTeams(id)
    }

    val teamsLiveData by lazy {
        teamsRepository.teamLiveData
    }

    fun getStandings(id: String) {
        standingsRepository.getStandings(id)
    }

    val standingsLiveData by lazy {
        standingsRepository.standingsLiveData
    }
}