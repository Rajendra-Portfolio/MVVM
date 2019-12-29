package com.iamrajendra.newsdragger.repo

import com.akshay.newsapp.utils.MockitoTest
import com.akshay.newsapp.utils.assertItems
import com.iamrajendra.newsdragger.api.NewsService
import com.iamrajendra.newsdragger.db.NewsArticlesDao
import com.iamrajendra.newsdragger.model.NewsArticles
import com.iamrajendra.newsdragger.model.NewsSourceResponse
import com.iamrajendra.newsdragger.model.ViewState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doReturnConsecutively
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock

@RunWith(JUnit4::class)
class NewsRepositoryTest : MockitoTest() {

    @Mock
    lateinit var newsDao: NewsArticlesDao

    @Mock
    lateinit var newsSourceService: NewsService

    @InjectMocks
    lateinit var newsRepository: NewsRepository

    @Test
    fun `get news articles when there is internet`() = runBlocking {
        // GIVEN
        val cachedArticles = listOf(NewsArticles(title = "Cached"))
        val response = NewsSourceResponse(articles = listOf(NewsArticles(title = "Fetched")))

        // WHEN
        whenever(newsSourceService.getNewsFromGoogle()) doReturn response
        whenever(newsDao.getNewsArticles()) doReturnConsecutively listOf(cachedArticles, response.articles)

        // THEN
        newsRepository.getNewsArticles().assertItems(
                ViewState.loading(),
                ViewState.success(cachedArticles),
                ViewState.success(response.articles)
        )
    }

    @Test
    fun `get cached news articles when there is no internet`() = runBlocking {
        // GIVEN
        val cachedArticles = listOf(NewsArticles(title = "Cached"))
        val error = RuntimeException("No internet connection")

        // WHEN
        whenever(newsSourceService.getNewsFromGoogle()) doThrow error
        whenever(newsDao.getNewsArticles()) doReturn cachedArticles

        // THEN
        newsRepository.getNewsArticles().assertItems(
                ViewState.loading(),
                ViewState.success(cachedArticles),
                ViewState.error(error.message.orEmpty())
        )
    }
}