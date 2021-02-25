package com.example.myalbums.ui.custom_views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.*
import com.example.myalbums.R
import com.example.myalbums.databinding.LayoutAppEditTextBinding

class AppEditText(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

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

    fun setLabel(label: String?) {
        binding.label = label
    }

    fun setInputType(inputType: Int?) {
        binding.inputType = inputType
    }

    fun setError(error: String?) {
        binding.defaultTextInputLayout.error = error
    }

    fun addTextChangedListener(listener: TextWatcher) =
        binding.defaultEditText.addTextChangedListener(listener)

    fun setInputText(inputTextField: String) {
        binding.defaultEditText.setText(inputTextField)
    }

}

@InverseBindingMethods(
    InverseBindingMethod(
        type = AppEditText::class,
        attribute = "android:text",
        method = "getText"
    )

)

object AppEditTextBinder {

    @JvmStatic
    @BindingAdapter(value = ["android:textAttrChanged"])
    fun setListener(editText: AppEditText, listener: InverseBindingListener?) {
        if (listener != null) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun afterTextChanged(editable: Editable) {
                    listener.onChange()
                }
            })
        }
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(editText: AppEditText, text: String) {
        if (text != editText.text) {
            editText.text = (text)
        }
    }
}


