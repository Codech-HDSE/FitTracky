package com.example.fittracky.Exercises
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.fittracky.R
import java.io.IOException

class ImagePagerAdapter(private val context: Context, private val imageList: List<String>, private val titleList: List<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_image_pager, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.adjustViewBounds = true
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

        try {
            // Load image using Glide from assets folder
            Glide.with(context)
                .asGif() // Specify that the image is a GIF
                .load("file:///android_asset/${imageList[position]}") // Load image from assets folder
                .into(imageView)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        titleTextView.text = titleList[position]

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = imageList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
}
