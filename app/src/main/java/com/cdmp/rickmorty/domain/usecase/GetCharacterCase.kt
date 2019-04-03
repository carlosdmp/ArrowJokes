package com.cdmp.rickmorty.domain.usecase

import com.cdmp.rickmorty.data.RickMortyApi
import com.cdmp.rickmorty.domain.model.CharacterListModel

class GetCharacterCase(private val api: RickMortyApi) {
    fun getPage(page: Int?): CharacterListModel =
        api.fetchCharacters(page ?: 1).toDomainModel()

}
