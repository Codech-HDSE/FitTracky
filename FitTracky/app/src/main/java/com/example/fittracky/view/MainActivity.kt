package com.example.fittracky.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fittracky.R
import com.example.fittracky.fragments.BMIFragment
import com.example.fittracky.fragments.ExerciseFragment
import com.example.fittracky.fragments.HistoryFragment
import com.example.fittracky.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//    Step 1 - Add Bottom Navigation View
//    Step 2 - Create a replace fragment method
//    Step 3 - Create onItemListener

//    Step 1
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Completing step 1
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.bottom_Exercise -> {
                    replaceFragment(ExerciseFragment())
                    true
                }

                R.id.bottom_bmi -> {
                    replaceFragment(BMIFragment())
                    true
                }

                R.id.bottom_history -> {  // New option for history
                    replaceFragment(HistoryFragment())
                    true
                }

                else -> false
            }
        }

        replaceFragment(HomeFragment())


    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }


}