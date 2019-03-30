package com.cdmp.rickmorty.presentation.home.model

import apps.cdmp.diffadapter.DiffModel

sealed class HomeItemDisplayModel : DiffModel<HomeItemDisplayModel> {
    abstract fun getViewType(): Int
}

data class CharacterDisplayModel(
    val id: Int,
    val name: String,
    val image: String
) : HomeItemDisplayModel() {
    override fun getViewType() = 0

    override fun areItemsTheSame(other: HomeItemDisplayModel) =
        other is CharacterDisplayModel && id == other.id

    override fun areContentsTheSame(other: HomeItemDisplayModel) =
        equals(other)

}

object LoadingDisplayModel : HomeItemDisplayModel() {
    override fun getViewType() = 1

    override fun areItemsTheSame(other: HomeItemDisplayModel) =
        other is CharacterDisplayModel

    override fun areContentsTheSame(other: HomeItemDisplayModel) =
        equals(other)
}