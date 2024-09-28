package com.example.fittracky.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracky.R
import com.example.fittracky.databinding.FragmentBmiBinding

class BMIFragment : Fragment() {

    private lateinit var binding: FragmentBmiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Set min and max values for weight and height pickers
            WeightPicker.minValue = 10
            WeightPicker.maxValue = 200
            heightPicker.minValue = 100
            heightPicker.maxValue = 250

            // Calculate BMI when weight or height changes
            WeightPicker.setOnValueChangedListener { _, _, _ ->
                calculateBMI()
            }
            heightPicker.setOnValueChangedListener { _, _, _ ->
                calculateBMI()
            }

            // Handle gender selection
            genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                calculateBMI() // Recalculate BMI when gender changes
            }
        }
    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100

        val weight = binding.WeightPicker.value
        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

        binding.resultsTv.text = String.format("Your BMI is: %.2f", bmi)
        binding.healthyTv.text = String.format("Considered: %s", healthyMessage(bmi))
    }

    private fun healthyMessage(bmi: Double): String {
        val genderId = binding.genderRadioGroup.checkedRadioButtonId
        val gender = if (genderId == R.id.maleRadioButton) "male" else "female"

        return when (gender) {
            "male" -> {
                when {
                    bmi <= 18.5 -> "Underweight Male"
                    bmi <= 25.0 -> "Healthy Male"
                    bmi <= 30.0 -> "Overweight Male"
                    else -> "Obese Male"
                }
            }

            "female" -> {
                when {
                    bmi <= 18.5 -> "Underweight Female"
                    bmi <= 25.0 -> "Healthy Female"
                    bmi <= 30.0 -> "Overweight Female"
                    else -> "Obese Female"
                }
            }
            else -> "Invalid gender"
        }
    }
}




