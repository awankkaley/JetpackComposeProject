package com.example.core.util

import com.example.core.domain.model.Popular
import com.example.core.data.remote.PopularItem

object DataMapper {
    fun mapResponseToDomain(input: List<PopularItem>): List<Popular> =
        input.map {
            Popular(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                adult = it.adult,
                voteCount = it.voteCount
            )
        }
}