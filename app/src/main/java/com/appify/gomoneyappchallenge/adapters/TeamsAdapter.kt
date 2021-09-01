package com.appify.gomoneyappchallenge.adapters

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appify.gomoneyappchallenge.R
import com.appify.gomoneyappchallenge.databinding.TeamsRowLayoutBinding
import com.appify.gomoneyappchallenge.model.Team
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class TeamsAdapter(val teams: List<Team>): RecyclerView.Adapter<TeamsAdapter.Holder>() {
    private lateinit var context: Context

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TeamsRowLayoutBinding.bind(itemView)
        val image = binding.image
        val teamName = binding.teamName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.teams_row_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val team = teams[position]
        GlideToVectorYou.justLoadImage(context as Activity?, Uri.parse(team.imageUrl), holder.image)
        holder.teamName.text = team.name
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}