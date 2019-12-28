package com.iamrajendra.newsdragger.di

import android.app.Application
import com.iamrajendra.newsdragger.NewsApp
import com.iamrajendra.newsdragger.di.base.NewsDatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Dagger support
        AndroidInjectionModule::class,

        // App
        NewsDatabaseModule::class,
        NewsServiceModule::class,
        ActivityModule::class,
        ViewModelModule::class
]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(newsApp: NewsApp)
}
