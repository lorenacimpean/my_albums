package com.example.myalbums.ui.contact_info

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.example.myalbums.BR
import com.example.myalbums.utils.RxOnItemClickListener
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class ContactDetailsViewModel(val input: Input) : ViewModel() {

    private lateinit var userInfo: UserInfo

    val output: Output by lazy {
        val onInfoLoaded = input.loadInfo.flatMap {
            userInfo = UserInfo()
            userInfo.firstName = "test"
            userInfo.lastName = "info"

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
        val onInfoLoaded: Observable<UiModel<UserInfo>>,
        val onSaveInfo: Observable<Boolean>
)

class UserInfo : BaseObservable() {

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }

}


