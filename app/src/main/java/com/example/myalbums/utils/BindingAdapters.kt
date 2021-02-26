package com.example.myalbums.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("itemImage")
fun ImageView.setPhoto(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this)

}

