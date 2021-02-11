package com.example.myalbums.di

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myalbums.R

open class BaseFragment : Fragment() {


    fun enableBackButton() {
        val actionBar: ActionBar? = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }


    fun enableOptionMenuWithIcon(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_items, menu)
    }

    fun enableOptionMenuWithText(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_items, menu)
        menu.getItem(0).title = getText(R.string.apply)
        menu.getItem(0).icon = null
    }

}