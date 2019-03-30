package com.cdmp.rickmorty.domain.model

import com.cdmp.rickmorty.presentation.home.model.CharacterDisplayModel

data class CharacterListModel(val list: List<CharacterModel>, val nextPage: Int?)
data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    fun toDisplayModel() = CharacterDisplayModel(
        id = id,
        name = name,
        image = image
    )

    data class Location(
        val name: String,
        val url: String
    )
}