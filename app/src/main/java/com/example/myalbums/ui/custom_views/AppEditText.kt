package com.example.myalbums.ui.custom_views

import android.content.Context
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutAppEditTextBinding
import com.example.myalbums.ui.contact_info.ValidationError
import com.google.android.material.textfield.TextInputLayout

@InverseBindingMethods(
    InverseBindingMethod(
        type = AppEditText::class,
        attribute = "android:text",
        event = "android:textAttrChanged",
        method = "getText"
    ),
)

class AppEditText(context: Context, attrs: AttributeSet) : TextInputLayout(context, attrs) {

    private val binding: LayoutAppEditTextBinding

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_app_edit_text, this, true)

    }

    var text: String?
        get() = binding.input.toString()
        set(value) {
            binding.input = value
        }

    var error: ValidationError?
        get() = binding.error
        set(value) {
            binding.error = value
        }

    var label: String = ""
        set(value) {
            binding.label = value
            field = value
        }

    var inputType: Int = TYPE_CLASS_TEXT
        set(value) {
            binding.inputType = value
            field = value
        }

    fun addTextChangedListener(listener: TextWatcher) =
        binding.defaultEditText.addTextChangedListener(listener)

}





