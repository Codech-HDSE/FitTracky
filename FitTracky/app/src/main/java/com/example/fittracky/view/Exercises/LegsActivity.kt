package com.example.fittracky.view.Exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.fittracky.R

class LegsActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Legs/Barbell-Hack-Squat.gif",
        "Legs/Barbell-Split-Squat.gif",
        "Legs/Barbell-sumo-squat.gif",
        "Legs/Dumbbell-Goblet-Squat.gif",
        "Legs/dumbbell-lunges.gif",

        "Legs/front-squat.gif",
        "Legs/Landmine-Lunge.gif",
        "Legs/Leg-Curl.gif",
        "Legs/LEG-EXTENSION.gif",
        "Legs/Leg-Press.gif",

        "Legs/Lever-Deadlift.gif",
        "Legs/Lying-Dumbbell-Leg-Curl.gif",
        "Legs/Smith-Machine-Lunge.gif",
        "Legs/Stiff-Leg-Deadlift.gif",
        "Legs/Trap-Bar-Deadlift.gif",

        )


    private val titleList = listOf(
        "01) Barbell Hack Squat",
        "02) Barbell Split Squat",
        "03) Barbell Sumo Squat",
        "04) Dumbbell Goblet Squat",
        "05) Dumbbell lunges",
        "06) Front squat",
        "07) Landmine Lunge",
        "08) Leg Curl",
        "09) Leg Extension",
        "10) Leg Press",
        "11) Lever Deadlift",
        "12) Lying Dumbbell Leg Curl",
        "13) Smith Machine Lunge",
        "14) Stiff Leg Deadlift",
        "15) Trap Bar Deadlift"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legs)

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


