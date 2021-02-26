package com.example.myalbums.ui.custom_views

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutAppEditTextBinding
import com.google.android.material.textfield.TextInputLayout

@InverseBindingMethods(
    InverseBindingMethod(
        type = AppEditText::class,
        attribute = "android:text",
        method = "getText"
    ),
)
class AppEditText(context: Context, attrs: AttributeSet) : TextInputLayout(context, attrs) {

    private val binding: LayoutAppEditTextBinding

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_app_edit_text, this, true)

    }

    var text: String
        get() = binding.input.toString()
        set(value) {
            binding.input = value
        }

    var label: String = ""
        set(value) {
            binding.label = value
            field = value
        }

    fun setInputType(inputType: Int?) {
        binding.inputType = inputType
    }

    fun addTextChangedListener(listener: TextWatcher) =
        binding.defaultEditText.addTextChangedListener(listener)

    fun validateField(): Boolean {
        if (binding.input.toString()
                .trim()
                .isEmpty()
        ) {
            binding.defaultTextInputLayout.error = context.getString(R.string.required_field)
            return false
        }
        else {
            binding.defaultTextInputLayout.isErrorEnabled = false
        }
        return true
    }

}





