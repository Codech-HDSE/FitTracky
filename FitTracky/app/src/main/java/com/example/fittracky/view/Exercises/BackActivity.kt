package com.example.fittracky.view.Exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.fittracky.R

class BackActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Back/Barbell-Bent-Over-Row.gif",
        "Back/Barbell-Deadlift.gif",
        "Back/Cable-Rear-Pulldown.gif",
        "Back/Cable-Seated-Pullover.gif",
        "Back/Cable-Straight-Arm-Pulldown.gif",

        "Back/close-grip-cable-row.gif",
        "Back/Close-Grip-Lat-Pulldown.gif",
        "Back/Dumbbell-Row.gif",
        "Back/Lat-Pulldown.gif",
        "Back/Pull-up.gif",

        "Back/Reverse-Grip-Barbell-Row.gif",
        "Back/Reverse-Lat-Pulldown.gif",
        "Back/Seated-Cable-Row.gif",
        "Back/Smith-Machine-Bent-Over-Row.gif",
        "Back/t-bar-rows.gif",

    )


    private val titleList = listOf(
        "01) Barbell Bent Over Row",
        "02) Barbell Deadlift",
        "03) Cable Rear Pulldown",
        "04) Cable Seated Pullover",
        "05) Cable Straight Arm Pulldown",
        "06) Close grip cable row",
        "07) Close Grip Lat Pulldown",
        "08) Dumbbell Row",
        "09) Lat Pulldown",
        "10) Pull up",
        "11) Reverse Grip Barbell Row",
        "12) Reverse Lat Pulldown",
        "13) Seated Cable Row",
        "14) Smith Machine Bent Over Row",
        "15) t-bar rows"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back)

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


