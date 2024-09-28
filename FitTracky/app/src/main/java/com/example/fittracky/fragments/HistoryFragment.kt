package com.example.fittracky.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittracky.model.FitnessData
import com.example.fittracky.database.FitnessDatabase
import com.example.fittracky.R
import com.example.fittracky.viewmodel.HistoryAdapter
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private var fitnessDataList: List<FitnessData> = emptyList() // Initialize with an empty list

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.history_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Set up adapter
        historyAdapter = HistoryAdapter(fitnessDataList)
        recyclerView.adapter = historyAdapter

        // Load real data from Room database
        loadFitnessData()

        return view
    }

    private fun loadFitnessData() {
        lifecycleScope.launch {
            try {
                val db = FitnessDatabase.getDatabase(requireContext())
                fitnessDataList = db.fitnessDataDao().getAllFitnessData()

                // Log the data
                for (data in fitnessDataList) {
                    Log.d("HistoryFragment", "Data: $data")
                }

                // Update RecyclerView
                historyAdapter.updateData(fitnessDataList)
            } catch (e: Exception) {
                Log.e("HistoryFragment", "Error loading data: ${e.message}")
            }
        }
    }

}


