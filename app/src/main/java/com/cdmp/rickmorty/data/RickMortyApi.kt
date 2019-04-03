package com.cdmp.rickmorty.data

import androidx.annotation.WorkerThread
import com.cdmp.rickmorty.data.service.RickMortyService
import java.lang.RuntimeException

class RickMortyApi(private val service: RickMortyService) {

    @WorkerThread
    fun fetchCharacters(page: Int) =
        service.getCharacters(page)
            .execute().body() ?: throw RuntimeException("Null body")

}