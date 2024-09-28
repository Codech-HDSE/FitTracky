package com.example.fittracky.view.Exercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.fittracky.R

class ChestActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: ImagePagerAdapter


    private val imageList = listOf(
        "Chest/Barbell-Bench-Press.gif",
        "Chest/Cable-Crossover.gif",
        "Chest/Chest-and-Front-of-Shoulder-Stretch.gif",
        "Chest/Chest-Dips.gif",
        "Chest/Close-Grip-Dumbbell-Press.gif",

        "Chest/Dumbbell-Fly.gif",
        "Chest/Dumbbell-Press.gif",
        "Chest/Dumbbell-Pullover.gif",
        "Chest/Kneeling-Landmine-Press.gif",
        "Chest/Lying-Cable-Fly.gif",

        "Chest/Pec-Deck-Fly.gif",
        "Chest/Reverse-Grip-Incline-Dumbbell-Press.gif",
        "Chest/Seated-Cable-Close-Grip-Chest-Press.gif",
        "Chest/Smith-Machine-Incline-Bench-Press.gif",
        "Chest/Svend-Press.gif",

        )


    private val titleList = listOf(
        "01) Barbell Bench Press",
        "02) Cable Crossover",
        "03) Chest and Front of Shoulder Stretch",
        "04) Chest Dips",
        "05) Close Grip-Dumbbell Press",
        "06) Dumbbell Fly",
        "07) Dumbbell Press",
        "08) Dumbbell Pullover",
        "09) Kneeling Landmine Press",
        "10) Lying Cable Fly",
        "11) Pec Deck Fly",
        "12) Reverse Grip Incline Dumbbell Press",
        "13) Seated Cable Close Grip Chest Press",
        "14) Smith Machine Incline Bench Press",
        "15) Svend Press"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chest)

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


