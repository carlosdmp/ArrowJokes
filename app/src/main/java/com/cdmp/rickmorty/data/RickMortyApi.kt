package com.cdmp.rickmorty.data

import androidx.annotation.WorkerThread
import com.cdmp.rickmorty.data.entity.CharacterList
import com.cdmp.rickmorty.data.service.RickMortyService
import retrofit2.Response
import java.lang.RuntimeException

class RickMortyApi(private val service: RickMortyService) {

    @WorkerThread
    suspend fun fetchCharacters(page: Int) = service.getCharacters(page)

}