package com.project.hiltmvvmapp.states

sealed class ResultState {
    object LOADING : ResultState()
    data class SUCCESS<T>(val response: List<T>) : ResultState()
    data class FAILURE(val error: Exception) : ResultState()
    object UPDATED : ResultState()
}
