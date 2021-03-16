package com.anushka.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anushka.newsapiclient.data.api.NewsAPIService
import com.anushka.newsapiclient.data.model.APIResponse
import com.anushka.newsapiclient.data.model.Article
import com.anushka.newsapiclient.data.model.NewsPagingSource
import com.anushka.newsapiclient.data.util.Resource
import com.anushka.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val newsAPIService: NewsAPIService
) : AndroidViewModel(app) {
    val newsHeadLines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            } else {
                newsHeadLines.postValue(Resource.Error("Internet is not available"))
            }

        } catch (e: Exception) {
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    fun getNewsData(country: String):Flow<PagingData<Article>>{
        return Pager(PagingConfig(pageSize = 5)){
            NewsPagingSource(newsAPIService,country)
        }.flow.cachedIn(viewModelScope)
    }


}














