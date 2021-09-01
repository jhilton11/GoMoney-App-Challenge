package com.appify.gomoneyappchallenge.utils

import android.util.Log
import com.appify.gomoneyappchallenge.model.Competition
import com.appify.gomoneyappchallenge.model.Match
import com.appify.gomoneyappchallenge.model.Standings
import com.appify.gomoneyappchallenge.model.Team
import org.json.JSONException
import org.json.JSONObject

object Converters {

    fun getMatches(stringArray: String): List<Match> {
        val matches: MutableList<Match> = mutableListOf()

        try {
            val jsonArray = JSONObject(stringArray).getJSONArray("matches")
            Log.d("converter", jsonArray.length().toString() + " items found")
            for (index in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(index)
                val matchId = jsonObject.getString("id")
                val homeTeam = jsonObject.getJSONObject("homeTeam").getString("name")
                val awayTeam = jsonObject.getJSONObject("awayTeam").getString("name")
                val minute = 0
                val md = jsonObject.getInt("matchday")
                val time = jsonObject.getString("utcDate")
                val homeScore = 0
                val awayScore = 0
                val match = Match(matchId, minute, homeTeam, awayTeam, homeScore, awayScore, time, md)
                matches.add(match)
            }
        } catch (e: JSONException) {
            Log.d("converter", "Error converting json: " + e.localizedMessage)
        }

        return matches
    }

    fun getCompetitions(stringData: String): List<Competition> {
        val competitions: MutableList<Competition> = mutableListOf()

        try {
            val jsonArray = JSONObject(stringData).getJSONArray("competitions")
            Log.d("converter", jsonArray.length().toString() + " items found")
            for (index in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(index)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val code = jsonObject.getString("code")
                val competition = Competition(id, name, code)
                competitions.add(competition)
            }
        } catch (e: JSONException) {
            Log.d("converter", "Error converting json: " + e.localizedMessage)
        }

        return competitions
    }

    fun getTeams(stringData: String): List<Team> {
        val teams: MutableList<Team> = mutableListOf()

        try {
            val jsonArray = JSONObject(stringData).getJSONArray("teams")
            Log.d("converter", jsonArray.length().toString() + " items found")
            for (index in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(index)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val imageUrl = jsonObject.getString("crestUrl")
                val tla = jsonObject.getString("tla")
                val team = Team(id, name, imageUrl, tla)
                teams.add(team)
            }
        } catch (e: JSONException) {
            Log.d("converter", "Error converting json: " + e.localizedMessage)
        }

        return teams
    }

    fun getStandings(stringData: String): List<Standings> {
        val standingsArray: MutableList<Standings> = mutableListOf()

        try {
            val seasonArray = JSONObject(stringData).getJSONArray("standings")
            val jsonArray = seasonArray.getJSONObject(0).getJSONArray("table")
            Log.d("converter", jsonArray.length().toString() + " items found")
            for (index in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(index)
                val position = jsonObject.getInt("position")
                val name = jsonObject.getJSONObject("team").getString("name")
                val points = jsonObject.getInt("points")
                val playedGames = jsonObject.getInt("playedGames")
                val gd = jsonObject.getInt("goalDifference")
                val imgUrl = jsonObject.getJSONObject("team").getString("crestUrl")
                val standing = Standings(position, name, playedGames, gd, points, imgUrl)
                standingsArray.add(standing)
            }
        } catch (e: JSONException) {
            Log.d("converter", "Error converting json: " + e.localizedMessage)
        }

        return standingsArray
    }

}