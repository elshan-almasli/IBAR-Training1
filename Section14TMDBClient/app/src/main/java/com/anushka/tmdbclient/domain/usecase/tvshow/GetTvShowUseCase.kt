package com.anushka.tmdbclient.domain.usecase.tvshow

import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.domain.repository.ITvShowRepository

class GetTvShowUseCase(private val iTvShowRepository: ITvShowRepository) {

    suspend fun execute(): List<TvShow>? = iTvShowRepository.getTvShow()
}