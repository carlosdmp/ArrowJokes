package com.cdmp.rickmorty.presentation.locations.model

import androidx.recyclerview.widget.DiffUtil

const val LocationItemViewType = 0
const val LocationLoadingViewType = 1

sealed class LocationDisplayModel {
    abstract fun getViewType(): Int
}

data class LocationItemDisplayModel(val name: String) : LocationDisplayModel() {
    override fun getViewType() = LocationItemViewType
}

object LocationLoadingDisplayModel : LocationDisplayModel() {
    override fun getViewType() = LocationLoadingViewType
}

object LocationModelDiffer : DiffUtil.ItemCallback<LocationDisplayModel>() {
    override fun areItemsTheSame(oldItem: LocationDisplayModel, newItem: LocationDisplayModel) =
        if (oldItem is LocationItemDisplayModel && newItem is LocationItemDisplayModel) {
            oldItem.name == newItem.name
        } else oldItem is LocationLoadingDisplayModel && newItem is LocationLoadingDisplayModel

    override fun areContentsTheSame(oldItem: LocationDisplayModel, newItem: LocationDisplayModel) =
        if (oldItem is LocationItemDisplayModel && newItem is LocationItemDisplayModel) {
            oldItem.name == newItem.name
        } else oldItem is LocationLoadingDisplayModel && newItem is LocationLoadingDisplayModel
}