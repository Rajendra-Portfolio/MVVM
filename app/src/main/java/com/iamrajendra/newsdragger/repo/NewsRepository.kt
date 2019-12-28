package com.iamrajendra.newsdragger.repo

import com.iamrajendra.newsdragger.api.NewsService
import com.iamrajendra.newsdragger.db.NewsArticlesDao
import com.iamrajendra.newsdragger.model.NewsArticles
import com.iamrajendra.newsdragger.model.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


import javax.inject.Inject
import javax.inject.Singleton


    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    @Singleton
    class NewsRepository @Inject constructor(
        private val newsDao: NewsArticlesDao,
        private val newsService: NewsService
    ) {

        /**
         * Fetch the news articles from database if exist else fetch from web
         * and persist them in the database
         */
        fun getNewsArticles(): Flow<ViewState<List<NewsArticles>>> {
            return flow {
                // 1. Start with loading + data from database
                emit(ViewState.loading())
                emit(ViewState.success(newsDao.getNewsArticles()))

                // 2. Try fetching new news -> save + emit
                val newsSource = newsService.getNewsFromGoogle()
                newsDao.insertArticles(newsSource.articles)

                // 3. Get articles from database [Single source of truth]
                emit(ViewState.success(newsDao.getNewsArticles()))
            }.catch {
                emit(ViewState.error(it.message.orEmpty()))
            }.flowOn(Dispatchers.IO)
        }

    }

