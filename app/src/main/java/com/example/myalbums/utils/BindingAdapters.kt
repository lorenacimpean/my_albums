package com.example.myalbums.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.myalbums.R
import com.example.myalbums.ui.contact_info.ErrorType
import com.example.myalbums.ui.contact_info.ValidationError
import com.example.myalbums.ui.custom_views.AppEditText
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

@BindingAdapter("itemImage")
fun ImageView.setPhoto(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this)

}

@BindingAdapter(value = ["android:textAttrChanged"])
fun setTextListener(editText: AppEditText, listener: InverseBindingListener?) {
    if (listener != null) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                editText.error = ValidationError()
            }

            override fun afterTextChanged(editable: Editable) {

                listener.onChange()
            }
        })

    }
}

@BindingAdapter("errorText")
fun setError(textInputLayout: TextInputLayout, error: ValidationError?) {
    if (error?.hasError == true) {
        when (error.errorType) {
            ErrorType.FIELD_EMPTY ->
                textInputLayout.error = textInputLayout.context.getString(R.string.required_field)

            else                  -> textInputLayout.error = ""
        }

    }

}
