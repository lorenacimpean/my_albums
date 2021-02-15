package com.example.myalbums.utils

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("appImageBinding")
fun setImageUri(view: ImageView, imageUri: String?) {
    if (imageUri == null) {
        view.setImageURI(null)
    }
    else {
        view.setImageURI(Uri.parse(imageUri))
    }
}

@BindingAdapter("appImageBinding")
fun setImageUri(view: ImageView, imageUri: Uri?) {
    view.setImageURI(imageUri)
}

@BindingAdapter("appImageBinding")
fun setImageDrawable(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("appImageBinding")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("appImageBtnBinding")
fun setImageDrawable(view: ImageButton, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("appStringRes")
fun stringRes(view: TextView, text: Int?) {
    try {
        view.text = text?.let { view.resources.getString(it) }
    } catch (e: Resources.NotFoundException) {
        view.text = null
    }
}
