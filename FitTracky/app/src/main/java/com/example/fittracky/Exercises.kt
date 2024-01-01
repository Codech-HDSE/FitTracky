package com.example.fittracky

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Exercises: AppCompatActivity(), SensorEventListener{

    private var mSensorManager: SensorManager? = null
    private var stepSensor: Sensor? = null
    private var totalSteps = 0
    private var previewsTotalSteps = 0
    private lateinit var progressBar: ProgressBar
    private lateinit var steps: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.steps_counter)

        progressBar = findViewById(R.id.progressBar)
        steps = findViewById(R.id.steps)

        resetSteps()
        loadData()

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }


    override fun onResume() {
        super.onResume()
        if (stepSensor == null) {
            Toast.makeText(this, "This Device has no step counter sensor", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this@Exercises, "Long press to reset steps", Toast.LENGTH_SHORT).show()
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
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("Key", previewsTotalSteps.toString())
            apply()
        }
    }

    private fun loadData() {
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val savedNumber = sharedPref.getString("Key", "0")?.toInt() ?: 0
        previewsTotalSteps = savedNumber
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Not implemented in this example
    }
}