package com.mostafa.data.remote

import com.mostafa.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("viewed/{period}.json")
    suspend fun getNewsArticles(
        @Path("period") period: Int = 1,
        @Query("api-key") clientId: String = Constants.API_KEY
    ): NewsResponse

}