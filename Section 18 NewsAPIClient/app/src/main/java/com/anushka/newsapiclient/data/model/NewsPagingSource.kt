package com.anushka.newsapiclient.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anushka.newsapiclient.data.api.NewsAPIService

class NewsPagingSource(
    private val newsAPIService: NewsAPIService,
    private val country: String
) : PagingSource<Int, Article>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage = params.key ?: 1
            val response = newsAPIService.getTopHeadlines(country, nextPage)
            val result = response.body()?.articles ?: emptyList()

            val nextKey = if(result.isEmpty()) null else nextPage.plus(1)

            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}