package com.example.myalbums.utils

data class UiModel<out T>(val state: State, val data: T?, val error: String?) {
    companion object {

        fun <T> success(data: T?): UiModel<T> {
            return UiModel(State.SUCCESS, data, null)
        }

        fun <T> error(errorMessage: String?, data: T? = null): UiModel<T> {
            return UiModel(State.ERROR, data, errorMessage)
        }

        fun <T> loading(data: T? = null): UiModel<T> {
            return UiModel(State.LOADING, data, null)
        }
    }

}

enum class State {
    SUCCESS,
    ERROR,
    LOADING
}
