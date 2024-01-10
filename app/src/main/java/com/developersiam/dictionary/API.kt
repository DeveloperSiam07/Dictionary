package com.developersiam.dictionary

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("en/{word}")
    suspend fun getMeaning(@Path("word") word : String): Response<List<WordResult>>
}