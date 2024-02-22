package com.example.fittracky

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class ImagePagerAdapter(private val context: Context, private val imageList: List<Int>, private val titleList: List<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_image_pager, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.adjustViewBounds = true
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

        Glide.with(context)
            .load(imageList[position])
            .into(imageView)

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
