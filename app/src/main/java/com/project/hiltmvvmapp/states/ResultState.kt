package com.project.hiltmvvmapp.states

import com.project.hiltmvvmapp.model.Fruit

sealed class ResultState {
    object LOADING : ResultState()
    data class SUCCESS(val response: List<Fruit>) : ResultState()
    data class FAILURE(val error: Exception) : ResultState()
    object UPDATED : ResultState()
}
