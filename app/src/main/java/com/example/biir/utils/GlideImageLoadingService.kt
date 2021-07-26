package com.example.biir.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.biir.R
import ss.com.bannerslider.ImageLoadingService

class GlideImageLoadingService(var context: Context) : ImageLoadingService {

    var circularProgressDrawable = CircularProgressDrawable(context)

    override fun loadImage(url: String, imageView: ImageView) {
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER

        circularProgressDrawable.strokeWidth = 6f
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.start()

        Glide.with(context)
            .load(url)
            // to disable caching
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            //.fitCenter()
            .into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView) {
        Glide.with(context)
            .load(resource)
            // to disable caching
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imageView)
    }

    override fun loadImage(url: String, placeHolder: Int, errorDrawable: Int, imageView: ImageView) {

        imageView.scaleType = ImageView.ScaleType.CENTER

        Glide.with(context)
            .load(url)
            // to disable caching
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .placeholder(placeHolder)
            .error(errorDrawable)
            .into(imageView)
    }
}