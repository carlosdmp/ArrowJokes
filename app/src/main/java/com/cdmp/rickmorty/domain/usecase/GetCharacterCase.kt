package com.cdmp.rickmorty.domain.usecase

import arrow.core.Either
import arrow.core.Try
import com.cdmp.rickmorty.data.RickMortyApi
import com.cdmp.rickmorty.domain.model.CharacterListModel
import com.cdmp.rickmorty.domain.model.DomainError

typealias CaseResult<T> = Either<DomainError, T>

class GetCharacterCase(private val api: RickMortyApi) {
    suspend fun getPage(page: Int?): CaseResult<CharacterListModel> =
        Try { api.fetchCharacters(page ?: 1).toDomainModel() }.toEither { DomainError(it) }

}
