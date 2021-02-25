package com.example.myalbums.ui.contact_info

import androidx.lifecycle.ViewModel
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
//
//class ContactDetailsViewModel(val input: Input) : ViewModel() {
//
////    val output: Output by lazy {
////        val onInfoLoaded = input.loadInfo.flatMap {
////            val user = UserModel()
////            return@flatMap Observable.just(UiModel.success(user))
////        }
////            .startWith(Observable.just(UiModel.loading()))
////            .onErrorReturn { UiModel.error(it.localizedMessage) }
////
////
////        Output(onInfoLoaded)
////    }
//}
//
//data class Input(val loadInfo: PublishSubject<Boolean>)
//data class Output(val onInfoLoaded: Observable<UiModel<UserModel>>)

data class UserModel(
        var firstName: String? = null,
        var lastName: String? = null,
        var emailAddress: String? = null,
        var phoneNumber: String? = null,
        var streetAddress: String? = null,
        var city: String? = null,
        var country: String? = null,
        var zioCode: Int? = null)

