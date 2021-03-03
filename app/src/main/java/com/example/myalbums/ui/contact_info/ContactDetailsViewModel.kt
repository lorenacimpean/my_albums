package com.example.myalbums.ui.contact_info

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.example.myalbums.BR
import com.example.myalbums.repo.LocationRepo
import com.example.myalbums.repo.SharedPreferencesRepo
import com.example.myalbums.utils.RxOnItemClickListener
import com.example.myalbums.utils.UiModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class ContactDetailsViewModel(
    val input : Input,
    private val sharedPreferences : SharedPreferencesRepo,
    private val locationRepo : LocationRepo) :
        ViewModel() {

    private lateinit var userInfo : UserInfo
    val output : Output by lazy {
        val onInfoLoaded = input.loadInfo.flatMap {
            sharedPreferences.getUserInfoFromSharedPreferences()
                    .map { info ->
                        userInfo = info ?: UserInfo()
                        return@map UiModel.success(userInfo)
                    }
        }
                .startWith(Observable.just(UiModel.loading()))
                .onErrorReturn { UiModel.error(it.localizedMessage) }
        val onSaveInfo = input.saveInfo.rx.flatMap {
            val errors = validateAllFields()
            if (errors.areAllFieldsValid()) {
                sharedPreferences.saveUserInfo(userInfo)
                return@flatMap Observable.just(UiModel.success(errors))
            } else {
                return@flatMap Observable.just(UiModel.error(errorMessage = "Please check that fields are not empty",
                                                             data = errors))
            }
        }
                .startWith(Observable.just(UiModel.loading()))
                .onErrorReturn { UiModel.error(it.localizedMessage) }
        val onLocationClick = input.clickLocation.rx.flatMap {
            locationRepo.getCurrentLocation()
                    .map {
                        //add decode location
                        print(it.latitude)
                        userInfo.address = "TEST"
                        userInfo.city = "TEST"
                        userInfo.country = "TEST"
                        userInfo.zipCode = "TEST"
                        return@map UiModel.success(userInfo)
                    }
        }
                .startWith(Observable.just(UiModel.loading()))
                .onErrorReturn { UiModel.error(it.localizedMessage) }

        Output(onInfoLoaded, onLocationClick, onSaveInfo)
    }

    private fun validateAllFields() : ValidationErrors {
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

    private fun validateField(input : String?) : ValidationError {
        return if (input?.trim()
                    ?.isEmpty() == true
        ) {
            ValidationError(ErrorType.FIELD_EMPTY, true)
        } else ValidationError()
    }
}

data class ValidationErrors(
    var firstNameError : ValidationError? = null,
    var lastNameError : ValidationError? = null,
    var emailError : ValidationError? = null,
    var phoneError : ValidationError? = null,
    var addressError : ValidationError? = null,
    var cityError : ValidationError? = null,
    var countryError : ValidationError? = null,
    var zipError : ValidationError? = null,
) {

    fun areAllFieldsValid() : Boolean {
        return firstNameError?.hasError == false &&
                lastNameError?.hasError == false &&
                emailError?.hasError == false &&
                phoneError?.hasError == false &&
                addressError?.hasError == false &&
                cityError?.hasError == false &&
                countryError?.hasError == false &&
                zipError?.hasError == false
    }
}

enum class ErrorType { FIELD_EMPTY, NONE }
data class ValidationError(
    var errorType : ErrorType,
    var hasError : Boolean
) {

    constructor() : this(ErrorType.NONE, false)
}

data class Input(
    val loadInfo : PublishSubject<Boolean>,
    val saveInfo : RxOnItemClickListener<Boolean>,
    val clickLocation : RxOnItemClickListener<Boolean>
)

data class Output(
    val onInfoLoaded : Observable<UiModel<UserInfo>>,
    val onLocationClick : Observable<UiModel<UserInfo>>,
    val onSaveInfo : Observable<UiModel<ValidationErrors>>
)

data class UserInfo(
    private var _firstName : String,
    private var _lastName : String,
    private var _email : String,
    private var _phone : String,
    private var _address : String,
    private var _city : String,
    private var _country : String,
    private var _zipCode : String) : BaseObservable() {

    var firstName : String
        @Bindable get() = _firstName
        set(value) {
            _firstName = value
            notifyPropertyChanged(BR.firstName)
        }
    var lastName : String
        @Bindable get() = _lastName
        set(value) {
            _lastName = value
            notifyPropertyChanged(BR.lastName)
        }
    var email : String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }
    var phone : String
        @Bindable get() = _phone
        set(value) {
            _phone = value
            notifyPropertyChanged(BR.phone)
        }
    var address : String
        @Bindable get() = _address
        set(value) {
            _address = value
            notifyPropertyChanged(BR.address)
        }
    var city : String
        @Bindable get() = _city
        set(value) {
            _city = value
            notifyPropertyChanged(BR.city)
        }
    var country : String
        @Bindable get() = _country
        set(value) {
            _country = value
            notifyPropertyChanged(BR.country)
        }
    var zipCode : String
        @Bindable get() = _zipCode
        set(value) {
            _zipCode = value
            notifyPropertyChanged(BR.zipCode)
        }

    constructor() : this("",
                         "",
                         "",
                         "",
                         "",
                         "",
                         "",
                         "")
}