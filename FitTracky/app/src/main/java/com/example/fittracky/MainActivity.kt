package com.example.fittracky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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
                else -> false
            }
        }

        replaceFragment(HomeFragment())


    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

//    If Framelayout is changed to fragment container
//    private fun replaceFragment(fragment: Fragment){
//        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
//    }


}