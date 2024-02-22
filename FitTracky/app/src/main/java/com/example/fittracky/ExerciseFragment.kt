// ExerciseFragment.kt
package com.example.fittracky

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        // Set click listener using View Binding
//        binding.absCardView.setOnClickListener {
//            // Navigate to AbsFragment
//            // Ensure you have the appropriate action ID from your navigation graph
//            findNavController().navigate(R.id.abs_fragment)
//        }

        binding.absCardView.setOnClickListener {
            val intent = Intent(requireContext(), AbsActivity::class.java)
            startActivity(intent)
        }


        // Handle clicks for other exercises if needed
        // ...
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
