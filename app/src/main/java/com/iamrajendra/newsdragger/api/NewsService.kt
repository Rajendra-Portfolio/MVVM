package com.iamrajendra.newsdragger.api

import com.iamrajendra.newsdragger.BuildConfig
import com.iamrajendra.newsdragger.model.NewsSourceResponse
import retrofit2.http.GET

interface NewsService {

    @GET("articles?source=google-news&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getNewsFromGoogle(): NewsSourceResponse

}
