package com.anushka.tmdbclient.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TVShowList(
    @SerializedName("results")
    val tvShow: List<TvShow>
    )