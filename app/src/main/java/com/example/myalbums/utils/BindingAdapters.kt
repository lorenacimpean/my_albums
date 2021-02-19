package com.example.myalbums.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.myalbums.R
import com.squareup.picasso.Picasso

@BindingAdapter("itemImage")
fun ImageView.setPhoto(url: String) {
    Picasso.get()
        .load(url)
        .resize(R.dimen.photoSize, R.dimen.photoSize)
        .into(this)

}



