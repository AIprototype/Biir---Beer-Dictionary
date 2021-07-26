package com.example.biir.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadImage")
fun bindingImage(view: ImageView?, url: String?) {
    if (url != null) {
        if (url.isNotEmpty())
            if (view != null) {
                GlideImageLoadingService(view.context).loadImage(url = url, imageView = view)
            }
    }
}

@BindingAdapter("android:text")
fun setFloat(view: TextView, value: Float) {
    if (value.isNaN()) {
        view.text = ""
    } else {
        val stringValue: String = value.toInt().toString()
        view.text = stringValue
    }
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}