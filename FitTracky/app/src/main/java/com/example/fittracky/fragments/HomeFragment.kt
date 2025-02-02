package com.example.fittracky.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.fittracky.model.FitnessData
import com.example.fittracky.database.FitnessDatabase
import com.example.fittracky.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var stepSensor: Sensor? = null
    private var totalSteps = 0
    private var previewsTotalSteps = 0
    private lateinit var progressBar: ProgressBar
    private lateinit var steps: TextView
    private lateinit var stepsBig: TextView
    private lateinit var burnedCalories: TextView
    private lateinit var distance: TextView


    private val REQUEST_ACTIVITY_RECOGNITION_PERMISSION = 1001

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        progressBar = view.findViewById(R.id.stepsProgressBar)
        steps = view.findViewById(R.id.steps)
        stepsBig = view.findViewById(R.id.steps_big)
        burnedCalories = view.findViewById(R.id.burned_calories)
        distance = view.findViewById(R.id.distance)

        resetSteps()
        loadData()

        mSensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        // Check for permission and request if not granted
        checkActivityRecognitionPermission()

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
            stepsBig.text = currentSteps.toString()
            progressBar.progress = currentSteps

            // Calculate duration of walking in hours
            val walkingDurationHours = calculateWalkingDuration(currentSteps, STRIDE_LENGTH_M)

            // Calculate and display calories burned based on walking duration
            val caloriesBurned = calculateCaloriesBurned(currentSteps, 4.0, walkingDurationHours) // Assuming MET value for walking
            burnedCalories.text = caloriesBurned.toInt().toString()

            // Calculate and display distance traveled
            val distanceTraveled = calculateDistance(currentSteps, STRIDE_LENGTH_M)
            distance.text = String.format("%.2f", distanceTraveled) // Display distance with two decimal places
        }
    }


    private fun resetSteps() {
        steps.setOnClickListener {
            Toast.makeText(requireContext(), "Long press to reset steps", Toast.LENGTH_SHORT).show()
        }

        stepsBig.setOnClickListener {
            Toast.makeText(requireContext(), "Long press to reset steps", Toast.LENGTH_SHORT).show()
        }

        steps.setOnLongClickListener {
            if (totalSteps - previewsTotalSteps == 0) {
                showNoDataAlert()
            } else {
                showResetConfirmationDialog()
            }
            true
        }

        stepsBig.setOnLongClickListener {
            if (totalSteps - previewsTotalSteps == 0) {
                showNoDataAlert()
            } else {
                showResetConfirmationDialog()
            }
            true
        }
    }

    // Function to show alert if no data (steps == 0)
    private fun showNoDataAlert() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("No Data")
            .setMessage("There are no steps recorded to reset.")
            .setPositiveButton("OK", null)
            .create()
        dialog.show()
    }

    // Modify showResetConfirmationDialog to avoid saving when steps == 0
    private fun showResetConfirmationDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Reset Steps")
            .setMessage("Do you want to reset the data?")
            .setPositiveButton("Yes") { _, _ ->
                lifecycleScope.launch {
                    try {
                        // Save the current fitness data only if steps are greater than zero
                        if (totalSteps - previewsTotalSteps > 0) {
                            saveFitnessData()
                        } else {
                            Toast.makeText(requireContext(), "No steps recorded, data will not be saved.", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(requireContext(), "Error saving data", Toast.LENGTH_SHORT).show()
                    }

                    // Reset the steps after saving
                    resetUI()
                    saveData()
                }
            }
            .setNegativeButton("No") { _, _ ->
                // Just reset the steps without saving
                resetUI()
                saveData()
            }
            .create()
        dialog.show()
    }


    private fun resetUI() {
        previewsTotalSteps = totalSteps
        steps.text = "0"
        stepsBig.text = "0"
        burnedCalories.text = "0"
        distance.text = "0"
        progressBar.progress = 0
    }

    private fun saveFitnessData() {
        try {
            val fitnessData = FitnessData(
                date = getCurrentDate(),
                steps = totalSteps - previewsTotalSteps,
                distance = calculateDistance(totalSteps - previewsTotalSteps, STRIDE_LENGTH_M),
                calories = calculateCaloriesBurned(totalSteps - previewsTotalSteps, 4.0, calculateWalkingDuration(totalSteps - previewsTotalSteps, STRIDE_LENGTH_M))
            )
            val db = FitnessDatabase.getDatabase(requireContext())
            lifecycleScope.launch {
                db.fitnessDataDao().insertFitnessData(fitnessData)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error saving data: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
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

    private fun checkActivityRecognitionPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                REQUEST_ACTIVITY_RECOGNITION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_ACTIVITY_RECOGNITION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, you can start using activity recognition API here
                } else {
                    // Permission denied, handle accordingly (e.g., show a message)
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    // Constants
    val WEIGHT_KG = 70 // User's weight in kilograms
    val STRIDE_LENGTH_M = 0.76 // Average stride length in meters (example value)
    val AVERAGE_WALKING_SPEED_M_PER_S = 1.4 // Average walking speed in meters per second (example value)

    // Method to calculate the duration of walking in hours
    fun calculateWalkingDuration(steps: Int, strideLength: Double): Double {
        val distanceInMeters = steps * strideLength
        val durationInSeconds = distanceInMeters / AVERAGE_WALKING_SPEED_M_PER_S
        return durationInSeconds / 3600.0 // Convert seconds to hours
    }

    // Method to calculate calories burned based on duration of walking
    fun calculateCaloriesBurned(steps: Int, metValue: Double, durationHours: Double): Double {
        return metValue * WEIGHT_KG * durationHours
    }

    // Method to calculate distance traveled
    fun calculateDistance(steps: Int, strideLength: Double): Double {
        val distanceInMeters = steps * strideLength
        return distanceInMeters / 1000.0 // Convert meters to kilometers
    }

}
