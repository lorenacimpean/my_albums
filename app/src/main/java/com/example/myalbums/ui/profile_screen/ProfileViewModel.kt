package com.example.myalbums.ui.profile_screen

import androidx.lifecycle.ViewModel
import com.example.myalbums.utils.RxOnItemClickListener
import io.reactivex.rxjava3.core.Observable

class ProfileViewModel(val input: Input) : ViewModel() {

    val output: Output by lazy {
        val onContactOpened = input.openContactInfo.rx
        Output(onContactOpened)
    }
}

data class Output(
        val onContactInfoTapped: Observable<Boolean>)

data class Input(
        val openContactInfo: RxOnItemClickListener<Boolean>)