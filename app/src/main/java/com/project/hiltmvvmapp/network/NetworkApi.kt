package com.project.hiltmvvmapp.network

import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.util.Constants.RestApiUrl.ALL_PATH
import com.project.hiltmvvmapp.util.Constants.RestApiUrl.API_PATH
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface NetworkApi {

    @GET(ALL_PATH)
    fun getFruitsAsync(): Deferred<Response<List<Fruit>>>

    @PUT(API_PATH)
    suspend fun createNewFruit(
        @Body fruit: Fruit
    ): Response<Unit>

    /*companion object RestApi{
        private const val BASE_URL = "https://www.fruityvice.com/"
        private const val API_PATH = "api/fruit/"
        private const val SEARCH_PATH = "$API_PATH{fruit}"
        private const val ALL_PATH = "${API_PATH}all"
    }*/

}