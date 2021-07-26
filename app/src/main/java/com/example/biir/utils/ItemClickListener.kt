package com.example.biir.utils

import android.view.View

interface ItemClickListener {
    fun onClick(
        view: View?,
        position: Int,
        isLongClick: Boolean
    )
}