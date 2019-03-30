package com.cdmp.rickmorty.data

import androidx.annotation.WorkerThread
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.extensions.io.monad.map
import arrow.effects.fix
import com.cdmp.rickmorty.data.entity.CharacterList
import com.cdmp.rickmorty.data.service.RickMortyService
import retrofit2.Call
import retrofit2.Response

class RickMortyApi(private val service: RickMortyService) {

    @WorkerThread
    suspend fun fetchAllCharacter() = service.getAllCharacters()

}