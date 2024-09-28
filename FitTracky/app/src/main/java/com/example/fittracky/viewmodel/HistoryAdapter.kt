package com.example.fittracky.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracky.R
import com.example.fittracky.model.FitnessData

class HistoryAdapter(private var fitnessDataList: List<FitnessData>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.history_date)
        val steps: TextView = view.findViewById(R.id.history_steps)
        val distance: TextView = view.findViewById(R.id.history_distance)
        val calories: TextView = view.findViewById(R.id.history_calories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val data = fitnessDataList[position]
        holder.date.text = data.date
        holder.steps.text = "Steps: ${data.steps}"
        holder.distance.text = "Distance: ${String.format("%.2f", data.distance)} km"
        holder.calories.text = "Calories: ${String.format("%.2f", data.calories)} kcal"
    }

    override fun getItemCount() = fitnessDataList.size

    fun updateData(newFitnessData: List<FitnessData>) {
        fitnessDataList = newFitnessData
        notifyDataSetChanged()
    }
}
