package com.appify.gomoneyappchallenge.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appify.gomoneyappchallenge.R
import com.appify.gomoneyappchallenge.model.Match

class MatchesAdapter(private val matches:List<Match>): RecyclerView.Adapter<MatchHolder>() {
    init {
        Log.d("adapter", matches.size.toString() + " items found")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.match_row_layout, parent, false)
        return MatchHolder(view)
    }

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        val match = matches[position]
        holder.bindTo(match)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

}
