package com.example.myalbums.ui.contact_info

import androidx.lifecycle.ViewModel
import com.example.myalbums.utils.RxOnItemClickListener
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class ContactDetailsViewModel(val input: Input) : ViewModel() {

    private lateinit var userInfo: UserContactInfo

    val output: Output by lazy {
        val onInfoLoaded = input.loadInfo.flatMap {
            userInfo = UserContactInfo()

            return@flatMap Observable.just(UiModel.success(userInfo))
        }
            .startWith(Observable.just(UiModel.loading()))
            .onErrorReturn { UiModel.error(it.localizedMessage) }

        val onSaveInfo = input.saveInfo.rx.map {
            print(userInfo.firstName)
            return@map it
        }
        Output(onInfoLoaded, onSaveInfo)
    }
}

data class Input(
        val loadInfo: PublishSubject<Boolean>,
        val saveInfo: RxOnItemClickListener<Boolean>
)

data class Output(
        val onInfoLoaded: Observable<UiModel<UserContactInfo>>,
        val onSaveInfo: Observable<Boolean>
)

data class UserContactInfo(var firstName: String? = "", var lastName: String? = "")

