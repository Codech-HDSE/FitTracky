package com.example.fittracky.view.Exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.fittracky.R

class BicepsActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Biceps/cable-curl.gif",
        "Biceps/Cable-Pulldown-Bicep-Curl.gif",
        "Biceps/Cable-Reverse-Grip-EZ-bar-Biceps-Curl.gif",
        "Biceps/close-grip-barbell-curl.gif",
        "Biceps/Close-Grip-Z-Bar-Curl.gif",

        "Biceps/Concentration-Curl.gif",
        "Biceps/Dumbbell-Alternate-Preacher-Curl.gif",
        "Biceps/Dumbbell-High-Curl.gif",
        "Biceps/dumbbell-reverse-curl.gif",
        "Biceps/Flexor-Incline-Dumbbell-Curls.gif",

        "Biceps/One-Arm-Cable-Bicep-Curl.gif",
        "Biceps/overhead-cable-curl.gif",
        "Biceps/rope-bicep-curls.gif",
        "Biceps/Seated-Hammer-Curl.gif",
        "Biceps/Z-Bar-Preacher-Curl.gif",

        )


    private val titleList = listOf(
        "01) Cable Curl",
        "02) Cable Pulldown Bicep Curl",
        "03) Cable Reverse Grip EZ-bar Biceps Curl",
        "04) Close Grip Barbell Curl",
        "05) Close Grip Z-Bar Curl",
        "06) Concentration Curl",
        "07) Dumbbell Alternate Preacher Curl",
        "08) Dumbbell High Curl",
        "09) Dumbbell reverse Curl",
        "10) Flexor Incline Dumbbell Curls",
        "11) One Arm Cable Bicep Curl",
        "12) Overhead Cable Curl",
        "13) Rope Bicep Curls",
        "14) Seated Hammer Curl",
        "15) Z-Bar Preacher Curl"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biceps)

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


