package com.cdmp.rickmorty.presentation.locations.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cdmp.rickmorty.databinding.LoadingHolderBinding
import com.cdmp.rickmorty.databinding.LocationHolderBinding
import com.cdmp.rickmorty.presentation.locations.model.LocationDisplayModel

sealed class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view)

class LocationItemViewHolder(private var binding: LocationHolderBinding) : LocationViewHolder(binding.root) {
    fun bind(item: LocationDisplayModel, clickListener: () -> Unit) {
        binding.root.setOnClickListener { _ -> clickListener() }
        binding.name.text = item.toString()
        binding.executePendingBindings()
    }
}

class LocationLoadingViewHolder(binding: LoadingHolderBinding, val onBindCallback: () -> Unit) :
    LocationViewHolder(binding.root) {
    fun bind() {
        onBindCallback()
    }
}
