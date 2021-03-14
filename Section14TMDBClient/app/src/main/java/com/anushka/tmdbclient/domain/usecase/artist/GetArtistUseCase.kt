package com.anushka.tmdbclient.domain.usecase.artist

import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.domain.repository.IArtistRepository

class GetArtistUseCase(private val artistRepository: IArtistRepository) {

    suspend fun execute(): List<Artist>? = artistRepository.getArtist()

}