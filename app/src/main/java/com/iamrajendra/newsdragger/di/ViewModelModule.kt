package com.iamrajendra.newsdragger.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iamrajendra.newsdragger.di.base.ViewModelFactory
import com.iamrajendra.newsdragger.di.base.ViewModelKey
import com.iamrajendra.newsdragger.main.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Describes all the [ViewModel] which need to be
 * created using DI.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsArticleViewModel(newsArticleViewModel: NewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
