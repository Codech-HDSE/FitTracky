package com.example.fittracky

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class StepsCounterFragment : Fragment(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var stepSensor: Sensor? = null
    private var totalSteps = 0
    private var previewsTotalSteps = 0
    private lateinit var progressBar: ProgressBar
    private lateinit var steps: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.steps_counter, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        steps = view.findViewById(R.id.steps)

        resetSteps()
        loadData()

        mSensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        return view
    }

    override fun onResume() {
        super.onResume()
        if (stepSensor == null) {
            Toast.makeText(requireContext(), "This Device has no step counter sensor", Toast.LENGTH_SHORT).show()
        } else {
            mSensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        mSensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = event.values[0].toInt()
            val currentSteps = totalSteps - previewsTotalSteps
            steps.text = currentSteps.toString()
            progressBar.progress = currentSteps
        }
    }

    private fun resetSteps() {
        steps.setOnClickListener {
            Toast.makeText(requireContext(), "Long press to reset steps", Toast.LENGTH_SHORT).show()
        }

        steps.setOnLongClickListener {
            previewsTotalSteps = totalSteps
            steps.text = "0"
            progressBar.progress = 0
            saveData()
            true
        }
    }

    private fun saveData() {
        val sharedPref = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("Key", previewsTotalSteps.toString())
            apply()
        }
    }

    private fun loadData() {
        val sharedPref = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val savedNumber = sharedPref.getString("Key", "0")?.toInt() ?: 0
        previewsTotalSteps = savedNumber
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Not implemented in this example
    }
}
