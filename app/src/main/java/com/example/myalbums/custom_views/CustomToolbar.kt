package com.example.myalbums.custom_views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.example.myalbums.R


class CustomToolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {


    var title: String? = null
    var leftIcon: Int? = null
    var rightIcon: Int? = null

    fun configureToolbar(title: String?, leftIcon: IconType, rightIcon: IconType) {
        this.title = title
        this.leftIcon = getDrawableByType(leftIcon)
        this.rightIcon = getDrawableByType(rightIcon)

    }

    private fun getDrawableByType(iconType: IconType?): Int? {
        return when (iconType) {
            IconType.BACK -> R.mipmap.back_button
            IconType.NOTIFICATION -> R.mipmap.app_logo
            IconType.APPLY -> R.mipmap.apply_icon
            else                  -> null
        }

    }
}

enum class IconType {
    BACK, NOTIFICATION, APPLY
}