package com.example.fittracky.view.Exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.fittracky.R

class TricepsActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Triceps/Barbell-Reverse-Close-grip-Bench-Press.gif",
        "Triceps/Cable-Crossover-Triceps-Extension.gif",
        "Triceps/Cable-Rope-Overhead-Triceps-Extension.gif",
        "Triceps/Close-Grip-Bench-Press.gif",
        "Triceps/Decline-Close-Grip-Bench-To-Skull-Crusher.gif",

        "Triceps/Decline-Dumbbell-Triceps-Extension.gif",
        "Triceps/Dumbbell-Incline-Two-Arm-Extension.gif",
        "Triceps/Dumbbell-Kickback.gif",
        "Triceps/EZ-Barbell-Incline-Triceps-Extension.gif",
        "Triceps/High-Pulley-Overhead-Tricep-Extension.gif",

        "Triceps/Reverse-Grip-Pushdown.gif",
        "Triceps/Rope-Pushdown.gif",
        "Triceps/Seated-Dumbbell-Triceps-Extension.gif",
        "Triceps/Seated-EZ-Bar-Overhead-Triceps-Extension.gif",
        "Triceps/V-bar-Pushdown.gif",

        )


    private val titleList = listOf(
        "01) Barbell Reverse Close grip Bench Press",
        "02) Cable Crossover Triceps Extension",
        "03) Cable Rope Overhead Triceps Extension",
        "04) Close Grip Bench Press",
        "05) Decline Close Grip Bench To Skull Crusher",
        "06) Decline Dumbbell Triceps Extension",
        "07) Dumbbell Incline Two Arm Extension",
        "08) Dumbbell Kickback",
        "09) EZ Barbell Incline Triceps Extension",
        "10) High Pulley Overhead Tricep Extension",
        "11) Reverse Grip Pushdown",
        "12) Rope Pushdown",
        "13) Seated Dumbbell Triceps Extension",
        "14) Seated EZ Bar Overhead Triceps Extension",
        "15) V-bar Pushdown"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triceps)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed() // This will perform the default back action (e.g., navigate back)
        }

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotsLayout)


        adapter = ImagePagerAdapter(this, imageList, titleList)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)

        // Add dots
        addDots(imageList.size)
        selectDot(0) // Initially select the first dot
    }


    private fun addDots(count: Int) {
        for (i in 0 until count) {
            val dot = ImageView(this)
            dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dot_inactive))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dot, params)
        }
    }

    private fun selectDot(position: Int) {
        for (i in 0 until dotsLayout.childCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == position) R.drawable.ic_dot_active else R.drawable.ic_dot_inactive
                )
            )
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        selectDot(position)
    }

    override fun onPageScrollStateChanged(state: Int) {}
}


