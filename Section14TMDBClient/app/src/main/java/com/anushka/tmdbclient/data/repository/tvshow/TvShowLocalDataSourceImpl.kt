package com.anushka.tmdbclient.data.repository.tvshow

import com.anushka.tmdbclient.data.db.TvShowDao
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvDao:TvShowDao):
    TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> {
       return tvDao.selectAllTVShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvDao.insertTVShow(tvShows)
        }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           tvDao.deleteAllTVShows()
       }
    }
}