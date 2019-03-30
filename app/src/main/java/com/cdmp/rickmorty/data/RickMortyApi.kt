package com.cdmp.rickmorty.data

import androidx.annotation.WorkerThread
import com.cdmp.rickmorty.data.service.RickMortyService

class RickMortyApi(private val service: RickMortyService) {

    @WorkerThread
    suspend fun fetchCharacters(page: Int) = service.getCharacters(page)

}