package com.iamrajendra.newsdragger.di.base

import android.app.Application
import com.iamrajendra.newsdragger.db.NewsArticlesDao
import com.iamrajendra.newsdragger.db.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsDatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): NewsDatabase = NewsDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideUserDao(db: NewsDatabase): NewsArticlesDao = db.newsArticlesDao()
}