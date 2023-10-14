package com.example.fittracky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager


class CardAdapter(val cards: List<Card>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]

        holder.title.text = card.title
        holder.description.text = card.description
    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        val cards = listOf(
            Card("Title 1", "Description 1"),
            Card("Title 2", "Description 2"),
            Card("Title 3", "Description 3")
        )

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CardAdapter(cards)
    }


    override fun getItemCount(): Int = cards.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
    }


}


class Card(
    val title: String,
    val description: String
)

