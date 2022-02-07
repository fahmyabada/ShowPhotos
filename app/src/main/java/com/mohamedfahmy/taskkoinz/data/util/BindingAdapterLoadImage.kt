package com.mohamedfahmy.taskkoinz.data.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mohamedfahmy.taskkoinz.R


@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {

        val circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 60f
        circularProgressDrawable.start()

        Glide
            .with(view)
            .load(url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.placeholder)
            .into(view)
    }

}
