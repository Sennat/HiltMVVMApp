package com.project.hiltmvvmapp.repositories

import com.project.hiltmvvmapp.exceptions.FailureResponseException
import com.project.hiltmvvmapp.exceptions.NullResponseException
import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.network.NetworkApi
import com.project.hiltmvvmapp.states.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val networkApi: NetworkApi) : NetworkRepository {

    override fun getAllFruitRecords(): Flow<ResultState> =
        flow {
            emit(ResultState.LOADING)
            delay(2000)

            try {
                val res = networkApi.getFruitsAsync().await()
                if (res.isSuccessful) {
                    res.body()?.let {
                        emit(ResultState.SUCCESS(it))
                    } ?: throw NullResponseException("Data not available")
                } else {
                    throw FailureResponseException(res.errorBody()?.string())
                }

            } catch (e: Exception) {
                emit(ResultState.FAILURE(e))
            }
        }

    override fun createNewFruitRecords(fruit: Fruit): Flow<ResultState> = flow {
        emit(ResultState.LOADING)
        delay(2000)

        try {
            val res = networkApi.createNewFruit(fruit)
            if (res.isSuccessful) {
                res.body()?.let {
                    emit(ResultState.UPDATED)
                } ?: throw NullResponseException("Data not available")
            } else {
                throw FailureResponseException(res.errorBody()?.string())
            }

        } catch (e: Exception) {
            emit(ResultState.FAILURE(e))
        }
    }

}