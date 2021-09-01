package com.appify.gomoneyappchallenge.adapters

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appify.gomoneyappchallenge.databinding.MatchRowLayoutBinding
import com.appify.gomoneyappchallenge.model.Match
import java.text.SimpleDateFormat
import java.util.*

class MatchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = MatchRowLayoutBinding.bind(itemView)
    val homeTeam = binding.homeTeam
    val awayTeam = binding.awayTeam
    val homeScore = binding.homeScore
    val awayScore = binding.awayScore
    val minute = binding.minute
    val time = binding.time
    val md = binding.md;

    fun bindTo(match: Match) {
        homeTeam.text = match.homeTeam
        awayTeam.text = match.awayTeam
        homeScore.text = match.homeScore.toString()
        awayScore.text = match.awayScore.toString()
        minute.text = match.minute.toString() + "\'"
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("HH:mm")
        val matchTime = formatter.format(parser.parse(match.time))
        time.text = matchTime
        md.text = "MD " + (match.matchNo);
    }
}