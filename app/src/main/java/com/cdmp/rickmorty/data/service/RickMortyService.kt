package com.cdmp.rickmorty.data.service

import com.cdmp.rickmorty.data.entity.CharacterList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyService {
    @GET("api/character/")
    fun getCharacters(@Query("page") page: Int): Deferred<CharacterList>

}