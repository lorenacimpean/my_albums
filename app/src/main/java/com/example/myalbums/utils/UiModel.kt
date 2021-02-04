package com.example.myalbums.utils

data class UiModel<out T>(val status: Status, val data: T?, val error: String?) {
    companion object {

        fun <T> success(data: T?): UiModel<T> {
            return UiModel(Status.SUCCESS, data, null)
        }

        fun <T> error(errorMessage: String?, data: T? = null): UiModel<T> {
            return UiModel(Status.ERROR, data, errorMessage)
        }

        fun <T> loading(data: T? = null): UiModel<T> {
            return UiModel(Status.LOADING, data, null)
        }
    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
