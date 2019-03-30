package com.cdmp.rickmorty.data.service

import arrow.integrations.retrofit.adapter.CallK
import com.cdmp.rickmorty.data.entity.CharacterList
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyService {
    @GET("api/character/")
    fun getAllCharacters(): Deferred<CharacterList>

}