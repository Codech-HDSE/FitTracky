// ExerciseFragment.kt
package com.example.fittracky.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracky.view.Exercises.AbsActivity
import com.example.fittracky.view.Exercises.BackActivity
import com.example.fittracky.view.Exercises.BicepsActivity
import com.example.fittracky.view.Exercises.ChestActivity
import com.example.fittracky.view.Exercises.LegsActivity
import com.example.fittracky.view.Exercises.ShouldersActivity
import com.example.fittracky.view.Exercises.TricepsActivity
import com.example.fittracky.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.absCardView.setOnClickListener {
            val intent = Intent(requireContext(), AbsActivity::class.java)
            startActivity(intent)
        }

        binding.bicepsCardView.setOnClickListener {
            val intent = Intent(requireContext(), BicepsActivity::class.java)
            startActivity(intent)
        }

        binding.backCardView.setOnClickListener {
            val intent = Intent(requireContext(), BackActivity::class.java)
            startActivity(intent)
        }

        binding.chestCardView.setOnClickListener {
            val intent = Intent(requireContext(), ChestActivity::class.java)
            startActivity(intent)
        }

        binding.tricepsCardView.setOnClickListener {
            val intent = Intent(requireContext(), TricepsActivity::class.java)
            startActivity(intent)
        }

        binding.legsCardView.setOnClickListener {
            val intent = Intent(requireContext(), LegsActivity::class.java)
            startActivity(intent)
        }

        binding.shouldersCardView.setOnClickListener {
            val intent = Intent(requireContext(), ShouldersActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
