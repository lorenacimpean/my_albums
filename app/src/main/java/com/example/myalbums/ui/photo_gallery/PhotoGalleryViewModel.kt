package com.example.myalbums.ui.photo_gallery

import androidx.lifecycle.ViewModel
import com.example.myalbums.utils.RxOnItemClickListener
import io.reactivex.rxjava3.core.Observable

class PhotoGalleryViewModel(val input: Input) : ViewModel() {

    val output: Output by lazy {
        val onBackClicked = input.clickOnBack.rx
        Output(onBackClicked)
    }

}

data class Output(
        val onBackClicked: Observable<Boolean>)

data class Input(
        val clickOnBack: RxOnItemClickListener<Boolean>)
