package com.example.fittracky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val secondAcButton = findViewById<Button>(R.id.third_act_btn)
        secondAcButton.setOnClickListener{
            val Intent = Intent(this, Activity3::class.java)
            startActivity(Intent)
            //test
        }
    }
}