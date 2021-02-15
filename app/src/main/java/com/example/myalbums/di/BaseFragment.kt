package com.example.myalbums.di

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.myalbums.R

open class BaseFragment : DisposableFragment() {

    fun enableBackButton() {
        setHasOptionsMenu(true)
        val actionBar: ActionBar? = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun enableOptionMenu(menu: Menu, inflater: MenuInflater, stringId: Int? = null) {
        menu.clear()
        inflater.inflate(R.menu.menu_items, menu)
        if (stringId != null) {
            menu.getItem(0).title = getText(stringId)
            menu.getItem(0).icon = null
        }
    }

}