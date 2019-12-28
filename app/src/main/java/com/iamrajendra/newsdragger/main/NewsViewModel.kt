package com.iamrajendra.newsdragger.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iamrajendra.newsdragger.model.NewsArticles
import com.iamrajendra.newsdragger.model.ViewState
import com.iamrajendra.newsdragger.repo.NewsRepository
import javax.inject.Inject

class NewsViewModel  @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel(){

    private val newsArticles: LiveData<ViewState<List<NewsArticles>>> = newsRepository.getNewsArticles().asLiveData()

    /**
     * Return news articles to observeNotNull on the UI.
     */
    fun getNewsArticles(): LiveData<ViewState<List<NewsArticles>>> = newsArticles
}