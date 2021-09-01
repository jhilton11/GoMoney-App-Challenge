package com.appify.gomoneyappchallenge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appify.gomoneyappchallenge.CompetitionActivity
import com.appify.gomoneyappchallenge.R
import com.appify.gomoneyappchallenge.databinding.CompetitonRowLayoutBinding
import com.appify.gomoneyappchallenge.model.Competition

class CompetitionAdapter(val competitions: List<Competition>): RecyclerView.Adapter<CompetitionAdapter.Holder>() {
    private lateinit var context:Context

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CompetitonRowLayoutBinding.bind(itemView)
        val name = binding.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.competiton_row_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val competition = competitions[position]
        holder.name.text = competition.name
        holder.binding.root.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, CompetitionActivity::class.java)
            intent.putExtra("id", competition.id)
            intent.putExtra("name", competition.name)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return competitions.size
    }
}