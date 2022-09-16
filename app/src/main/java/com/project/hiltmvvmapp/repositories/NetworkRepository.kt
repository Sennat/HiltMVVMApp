package com.project.hiltmvvmapp.repositories

import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.states.ResultState
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getAllFruitRecords() : Flow<ResultState>
    fun createNewFruitRecords(fruit : Fruit) : Flow<ResultState>

}