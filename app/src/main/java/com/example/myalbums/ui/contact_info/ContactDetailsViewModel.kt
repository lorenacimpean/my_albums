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
            return@flatMap Observable.just(UiModel.success(userInfo))
        }
            .startWith(Observable.just(UiModel.loading()))
            .onErrorReturn { UiModel.error(it.localizedMessage) }

        val onSaveInfo = input.saveInfo.rx.map {
            return@map UiModel.success(validateAllFields())
        }
        Output(onInfoLoaded, onSaveInfo)
    }

    private fun validateAllFields(): ValidationErrors {
        val errors = ValidationErrors()
        errors.firstNameError = validateField(userInfo.firstName)
        errors.lastNameError = validateField(userInfo.lastName)
        errors.emailError = validateField(userInfo.email)
        errors.phoneError = validateField(userInfo.phone)
        errors.cityError = validateField(userInfo.city)
        errors.addressError = validateField(userInfo.address)
        errors.countryError = validateField(userInfo.country)
        errors.zipError = validateField(userInfo.zipCode)
        return errors

    }

    private fun validateField(input: String?): ValidationError? {
        return if (input?.trim()
                ?.isEmpty() == true
        ) {
            ValidationError(ErrorType.FIELD_EMPTY, true)
        }
        else null

    }

}

data class ValidationErrors(
        var firstNameError: ValidationError? = null,
        var lastNameError: ValidationError? = null,
        var emailError: ValidationError? = null,
        var phoneError: ValidationError? = null,
        var addressError: ValidationError? = null,
        var cityError: ValidationError? = null,
        var countryError: ValidationError? = null,
        var zipError: ValidationError? = null,
)

enum class ErrorType { FIELD_EMPTY, NONE }
data class ValidationError(
        var errorType: ErrorType,
        var hasError: Boolean
) {

    constructor() : this(ErrorType.NONE, false)

}

data class Input(
        val loadInfo: PublishSubject<Boolean>,
        val saveInfo: RxOnItemClickListener<Boolean>
)

data class Output(
        val onInfoLoaded: Observable<UiModel<UserInfo>>,
        val onSaveInfo: Observable<UiModel<ValidationErrors>>
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

    @get:Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var phone: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phone)
        }

    @get:Bindable
    var address: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }

    @get:Bindable
    var city: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @get:Bindable
    var country: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.country)
        }

    @get:Bindable
    var zipCode: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.zipCode)
        }

}



