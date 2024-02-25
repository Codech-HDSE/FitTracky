package com.example.fittracky.Exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.fittracky.R
import java.io.InputStream

class ShouldersActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Shoulders/45-Degree-Incline-Row.gif",
        "Shoulders/Alternating-Dumbbell-Front-Raise.gif",
        "Shoulders/Barbell-Upright-Row.gif",
        "Shoulders/Bent-Over-Lateral-Raise.gif",
        "Shoulders/bent-over-reverse-cable-fly.gif",

        "Shoulders/cable-rear-delt-fly.gif",
        "Shoulders/Dumbbell-Bent-Arm-Laterl-Raise.gif",
        "Shoulders/Dumbbell-Shoulder-Press.gif",
        "Shoulders/Half-Kneeling-High-Cable-Row-Rope.gif",
        "Shoulders/Leaning-Cable-Lateral-Raise.gif",

        "Shoulders/Rear-Delt-Machine-Flys.gif",
        "Shoulders/Smith-Machine-Behind-Neck-Press.gif",
        "Shoulders/Smith-Machine-Shoulder-Press.gif",
        "Shoulders/Two-Arm-Dumbbell-Front-Raise.gif",
        "Shoulders/weighted-round-arm.gif",

        )


    private val titleList = listOf(
        "01) 45-Degree Incline Row",
        "02) Alternating Dumbbell Front Raise",
        "03) Barbell Upright Row",
        "04) Bent Over Lateral Raise",
        "05) Bent Over Reverse Cable Fly",
        "06) Cable Rear Delt Fly",
        "07) Dumbbell Bent Arm Laterl Raise",
        "08) Dumbbell-Shoulder-Press",
        "09) Half Kneeling High Cable Row Rope",
        "10) Leaning Cable Lateral Raise",
        "11) Rear Delt Machine Flys.",
        "12) Smith Machine Behind Neck Press",
        "13) Smith Machine Shoulder Press",
        "14) Two Arm Dumbbell Front Raise",
        "15) Weighted Rround Arm"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoulders)

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


