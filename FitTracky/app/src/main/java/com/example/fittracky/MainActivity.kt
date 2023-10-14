package com.example.fittracky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondAcButton = findViewById<Button>(R.id.second_act_btn)
        secondAcButton.setOnClickListener{
            val Intent = Intent(this, Activity2::class.java)
            startActivity(Intent)
        }
    }
}