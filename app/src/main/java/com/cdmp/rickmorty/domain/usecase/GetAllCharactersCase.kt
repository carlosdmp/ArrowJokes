package com.cdmp.rickmorty.domain.usecase

import com.cdmp.rickmorty.data.RickMortyApi
import com.cdmp.rickmorty.data.entity.CharacterList

class GetAllCharactersCase(private val api: RickMortyApi) {
    suspend operator fun invoke(): CharacterList = api.fetchAllCharacter().await()

}
