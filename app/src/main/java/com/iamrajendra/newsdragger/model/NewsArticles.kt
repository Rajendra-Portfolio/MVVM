package com.iamrajendra.newsdragger.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iamrajendra.newsdragger.model.NewsArticles.NewsArticles.tableName
import com.iamrajendra.newsdragger.model.NewsArticles.NewsArticles.Column

@Entity(tableName = tableName)
data class NewsArticles(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = Column.author)
    @SerializedName(Column.author)
    val author: String? = null,

    @ColumnInfo(name = Column.title)
    @SerializedName(Column.title)
    val title: String? = null,

    @ColumnInfo(name = Column.description)
    @SerializedName(Column.description)
    val description: String? = null,

    @ColumnInfo(name = Column.url)
    @SerializedName(Column.url)
    val url: String? = null,

    @ColumnInfo(name = Column.urlToImage)
    @SerializedName(Column.urlToImage)
    val urlToImage: String? = null,

    @ColumnInfo(name = Column.publishedAt)
    @SerializedName(Column.publishedAt)
    val publishedAt: String? = null
) {
    object NewsArticles {
        const val tableName = "news_article"

        object Column {
            const val id = "id"
            const val author = "author"
            const val title = "title"
            const val description = "description"
            const val url = "url"
            const val urlToImage = "urlToImage"
            const val publishedAt = "publishedAt"
        }
    }
}