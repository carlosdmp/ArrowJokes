package com.cdmp.rickmorty.presentation.locations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.cdmp.rickmorty.R
import com.cdmp.rickmorty.databinding.LoadingHolderBinding
import com.cdmp.rickmorty.databinding.LocationHolderBinding
import com.cdmp.rickmorty.presentation.locations.model.*


class LocationsAdapter(
    val items: MutableList<LocationDisplayModel> = mutableListOf(),
    private val clickCallback: (Int) -> Unit,
    private val loadingReached: () -> Unit
) : ListAdapter<LocationDisplayModel, LocationViewHolder>(LocationModelDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context).run {
        when (viewType) {
            LocationItemViewType -> {
                val binding = DataBindingUtil.inflate<LocationHolderBinding>(
                    this, R.layout.character_holder, parent, false
                )
                LocationItemViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<LoadingHolderBinding>(
                    this, R.layout.loading_holder, parent, false
                )
                LocationLoadingViewHolder(binding, loadingReached)
            }
        }
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = items[position]
        if (holder is LocationItemViewHolder && item is LocationItemDisplayModel) {
            holder.bind(item) { clickCallback(1) }
        } else if (holder is LocationLoadingViewHolder && item is LocationLoadingDisplayModel) {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()
}

