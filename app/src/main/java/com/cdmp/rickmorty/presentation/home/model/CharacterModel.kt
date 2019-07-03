package com.cdmp.rickmorty.presentation.home.model

const val HomeItemViewType = 0
const val HomeLoadingViewType = 1

sealed class HomeItemDisplayModel {
    abstract fun getViewType(): Int
    abstract fun areItemsTheSame(other: HomeItemDisplayModel): Boolean
    abstract fun areContentsTheSame(other: HomeItemDisplayModel): Boolean
}

data class CharacterDisplayModel(
    val id: Int,
    val name: String,
    val image: String
) : HomeItemDisplayModel() {
    override fun getViewType() = HomeItemViewType

    override fun areItemsTheSame(other: HomeItemDisplayModel) =
        other is CharacterDisplayModel && id == other.id

    override fun areContentsTheSame(other: HomeItemDisplayModel) =
        equals(other)

}

object LoadingDisplayModel : HomeItemDisplayModel() {
    override fun getViewType() = HomeLoadingViewType

    override fun areItemsTheSame(other: HomeItemDisplayModel) =
        other is CharacterDisplayModel

    override fun areContentsTheSame(other: HomeItemDisplayModel) =
        equals(other)
}