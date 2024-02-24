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

class AbsActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Abs/ab_coaster_machine.gif",
        "Abs/Bicycle-Crunch.gif",
        "Abs/Captains-Chair-Leg-Raise.gif",
        "Abs/Crunch-With-Leg-Raise.gif",
        "Abs/Decline-Sit-up.gif",
        "Abs/Dumbbell-Side-Bend.gif",
        "Abs/Hanging-Side-Knee-Raises.gif",
        "Abs/Kneeling-Cable-Crunch.gif",
        "Abs/Landmine-Twist.gif",
        "Abs/Lying-Leg-Raise.gif",
        "Abs/Lying-Scissor-Kick.gif",
        "Abs/Mountain-climber.gif",
        "Abs/Oblique-Floor-Crunches.gif",
        "Abs/Seated-Bench-Leg-Pull-in.gif",
        "Abs/standing-cable-twist.gif"
    )


    private val titleList = listOf(
        "01) Ab Coaster Machine",
        "02) Bicycle Crunch",
        "03) Captains Chair Leg Raise",
        "04) Crunch With Leg Raise",
        "05) Decline Sit up",
        "06) Dumbbell Side Bend",
        "07) Hanging Knee Raises",
        "08) Kneeling Cable Crunch",
        "09) Landmine Twist",
        "10) Lying Leg Raise",
        "11) Lying Scissor Kick",
        "12) Mountain climber",
        "13) Oblique Floor Crunches",
        "14) Seated Bench Leg Pull in",
        "15) Standing cabletwist"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs)

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


