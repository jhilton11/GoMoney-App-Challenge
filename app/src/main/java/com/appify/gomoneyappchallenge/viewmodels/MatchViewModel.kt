package com.appify.gomoneyappchallenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.appify.gomoneyappchallenge.repository.CompetitionRepository
import com.appify.gomoneyappchallenge.repository.MatchRepository

class MatchViewModel(application: Application): AndroidViewModel(application) {
    val repository = MatchRepository(application)
    val competitionRepo = CompetitionRepository(application)
    fun getMatches() {
        repository.getMatches()
    }

    fun getCompetitions() {
        competitionRepo.getCompetitions()
    }

    val matchLiveData by lazy {
        repository.matchLiveData
    }

    val competitionLiveData by lazy {
        competitionRepo.competitionLiveData
    }
}