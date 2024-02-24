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
        "Abs/abdominal-stretch.gif",
        "Abs/Alternate-Lying-Floor-Leg-Raise.gif",
        "Abs/Bicycle-Crunch.gif",
        "Abs/Captains-Chair-Leg-Raise.gif",
        "Abs/Cross-Crunch.gif",
        "Abs/Crunch.gif",
        "Abs/Crunch-With-Leg-Raise.gif",
        "Abs/Dead-Bug.gif",
        "Abs/Decline-Sit-up.gif",
        "Abs/Double-Leg-Stretch.gif",
        "Abs/Dumbbell-Side-Bend.gif",
        "Abs/Floor-Crunch.gif",
        "Abs/Front-to-Side-Plank.gif",
        "Abs/Hanging-Knee-Raises.gif",
        "Abs/Hanging-Side-Knee-Raises.gif",
        "Abs/Heel-Touch.gif",
        "Abs/Incline-Leg-Hip-Raise.gif",
        "Abs/Kneeling-Cable-Crunch.gif",
        "Abs/Landmine-Twist.gif",
        "Abs/Leg-Raise-Dragon-Flag.gif",
        "Abs/Lying-Knee-Raise.gif",
        "Abs/Lying-Leg-Raise.gif",
        "Abs/Lying-Scissor-Kick.gif",
        "Abs/Mountain-climber.gif",
        "Abs/Oblique-Floor-Crunches.gif",
        "Abs/Quarter-Sit-up.gif",
        "Abs/Reverse-Crunch-1.gif",
        "Abs/Seated-Bench-Leg-Pull-in.gif",
        "Abs/Seated-Flutter-Kick.gif",
        "Abs/Side-Bridge.gif",
        "Abs/Side-Plank-Oblique-Crunch.gif",
        "Abs/Spider-Plank.gif",
        "Abs/standing-cable-twist.gif",
        "Abs/T-Cross-Sit-up.gif",
        "Abs/Tuck-Crunch.gif"
    )


    private val titleList = listOf(
        "Ab Coaster Machine",
        "Abdominal Stretch",
        "Alternate Lying Floor Leg Raise",
        "Bicycle Crunch",
        "Captains Chair Leg Raise",
        "Cross Crunch",
        "Crunch",
        "Crunch With Leg Raise",
        "Dead Bug",
        "Decline Sit up",
        "Double-Leg Stretch",
        "Dumbbell Side Bend",
        "Floor Crunch",
        "Front to Side Plank",
        "Hanging Knee Raises",
        "Heel Touch",
        "Incline Leg Hip Raise",
        "Kneeling Cable Crunch",
        "Landmine Twist",
        "Leg Raise Dragon Flag",
        "Lying Knee Raise",
        "Lying Leg Raise",
        "Lying Scissor Kick",
        "Mountain climber",
        "Oblique Floor Crunches",
        "Quarter Sit up",
        "Reverse Crunch 1",
        "Seated Bench Leg Pull in",
        "Seated Flutter Kick",
        "Side Bridge",
        "Side Plank Oblique Crunch",
        "Spider Plank",
        "standing cabletwist",
        "T-Cross Sit up",
        "Tuck Crunch"
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


