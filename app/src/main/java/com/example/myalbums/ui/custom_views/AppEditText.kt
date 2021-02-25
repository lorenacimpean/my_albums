package com.example.myalbums.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutAppEditTextBinding

class AppEditText(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding: LayoutAppEditTextBinding

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_app_edit_text, this, true)
    }

    fun setLabel(label: String?) {
        binding.label = label
    }

    fun setText(input: String?) {
        binding.input = input
    }

    fun setInputType(inputType: Int?) {
        binding.inputType = inputType
    }

    fun setError(error: String?) {
        binding.defaultTextInputLayout.error = error
    }

    fun getText(): String {
        return binding.defaultEditText.text.toString()
    }
}
