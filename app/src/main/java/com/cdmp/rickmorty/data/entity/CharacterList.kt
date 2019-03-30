package com.cdmp.rickmorty.data.entity

import com.cdmp.rickmorty.domain.model.CharacterListModel
import com.cdmp.rickmorty.domain.model.CharacterModel
import com.google.gson.annotations.SerializedName


data class CharacterList(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
) {

    fun toDomainModel() = CharacterListModel(
        list = results.map {
            CharacterModel(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                origin = CharacterModel.Location(it.origin.name, it.origin.url),
                location = CharacterModel.Location(it.location.name, it.location.url),
                image = it.image,
                episode = it.episode,
                url = it.url,
                created = it.created
            )
        },
        nextPage = if (info.next.isNotEmpty()) {
            info.next.split("=").let {
                it[it.size - 1].toInt()
            }
        } else {
            null
        }
    )

    data class Result(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("species")
        val species: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("origin")
        val origin: Origin,
        @SerializedName("location")
        val location: Location,
        @SerializedName("image")
        val image: String,
        @SerializedName("episode")
        val episode: List<String>,
        @SerializedName("url")
        val url: String,
        @SerializedName("created")
        val created: String
    ) {

        data class Location(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )

        data class Origin(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )
    }

    data class Info(
        @SerializedName("count")
        val count: Int,
        @SerializedName("pages")
        val pages: Int,
        @SerializedName("next")
        val next: String,
        @SerializedName("prev")
        val prev: String
    )


}