package com.appify.gomoneyappchallenge.adapters

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appify.gomoneyappchallenge.R
import com.appify.gomoneyappchallenge.databinding.StandingsRowLayoutBinding
import com.appify.gomoneyappchallenge.model.Standings
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class StandingsAdapter(val standingsArray: List<Standings>): RecyclerView.Adapter<StandingsAdapter.Holder>() {
    private lateinit var context:Context

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = StandingsRowLayoutBinding.bind(itemView)
        val position = binding.position
        val image = binding.image
        val teamName = binding.teamName
        val mp = binding.mp
        val gd = binding.gd
        val points = binding.points
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.standings_row_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val standings = standingsArray[position]
        holder.position.text = standings.position.toString()
        holder.teamName.text = standings.teamName
        holder.mp.text = standings.mp.toString()
        holder.gd.text = standings.gd.toString()
        holder.points.text = standings.points.toString()
        GlideToVectorYou.justLoadImage(context as Activity?, Uri.parse(standings.imgUrl), holder.image)
    }

    override fun getItemCount(): Int {
        return standingsArray.size
    }
}