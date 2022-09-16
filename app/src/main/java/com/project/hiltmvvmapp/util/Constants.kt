package com.project.hiltmvvmapp.util

class Constants {

    companion object RestApiUrl{
        const val BASE_URL = "https://www.fruityvice.com/"
        const val API_PATH = "api/fruit/"
        const val SEARCH_PATH = "$API_PATH{fruit}"
        const val ALL_PATH = "${API_PATH}all"
    }
}