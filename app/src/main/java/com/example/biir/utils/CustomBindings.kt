package com.example.biir.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.biir.R
import java.lang.Exception

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

@BindingAdapter("displayIbuText")
fun setText(view: TextView, value: String?) {
    if (value == null || value.trim() == "null") {
        view.text = ""
    } else {
        try {
            val ibu = Integer.parseInt(value)
            when {
                ibu <= 20 -> {
                    view.text = view.context.getString(R.string.smooth)
                }
                ibu in 21..50 -> {
                    view.text = view.context.getString(R.string.bitter)
                }
                else -> {
                    view.text = view.context.getString(R.string.hipster_plus)
                }
            }
        } catch (e:Exception) {
            view.text = value
        }
    }
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}