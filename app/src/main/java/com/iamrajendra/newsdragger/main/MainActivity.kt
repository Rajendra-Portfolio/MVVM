package com.iamrajendra.newsdragger.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamrajendra.newsdragger.R
import com.iamrajendra.newsdragger.adpter.NewsArticlesAdapter
import com.iamrajendra.newsdragger.di.base.BaseActivity
import com.iamrajendra.newsdragger.model.ViewState
import com.iamrajendra.newsdragger.utils.getViewModel
import com.iamrajendra.newsdragger.utils.observeNotNull
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    private val newsArticleViewModel by lazy { getViewModel<NewsViewModel>() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = NewsArticlesAdapter { Toast.makeText(applicationContext,"On Click",Toast.LENGTH_LONG).show() }
        news_list.adapter = adapter
        news_list.layoutManager = LinearLayoutManager(this)
        newsArticleViewModel.getNewsArticles().observeNotNull(this, { state ->
            when (state) {
                is ViewState.Success -> {
                  Log.i(TAG,"get data successfully ${state.data}")
                    adapter.replaceItems(state.data)
                }
                is ViewState.Loading -> {
                Log.i(TAG,"Data is loading");
                }
                is ViewState.Error -> {
                    Log.e(TAG,"something went wrong${state.message}")
                }
            }
        })
    }

    companion object{
        var TAG:String = MainActivity::class.java.simpleName
    }
}
