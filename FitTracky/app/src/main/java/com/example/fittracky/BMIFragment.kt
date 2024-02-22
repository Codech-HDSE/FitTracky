package com.example.fittracky

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fittracky.databinding.ActivityMainBinding
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

        binding.WeightPicker.minValue=30
        binding.WeightPicker.maxValue=150

        binding.heightPicker.minValue=100
        binding.heightPicker.maxValue=250

        binding.WeightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()

        }
        binding.WeightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()

        }
    }





    private fun calculateBMI(){

val height=binding.heightPicker.value
     val doubleHeight=height.toDouble()/100

        val weight=binding.WeightPicker.value

        val bmi=weight.toDouble() /(doubleHeight*doubleHeight)


        binding.resultsTv.text= String.format("Your BMI is: %2f",bmi)
        binding.healthyTv.text= String.format("Considered :%s",healthyMessage(bmi))
    }

    private fun healthyMessage(bmi: Double): String {


        if(bmi <18.5)
            return "Underweight"
        if(bmi <25.0)
            return "Healthy"
        if(bmi <30.0)
            return "Overweight"
        return "Obese"


    }
}



