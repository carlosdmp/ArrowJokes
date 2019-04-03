package com.cdmp.rickmorty.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.cdmp.diffadapter.diff.DiffAdapter
import com.cdmp.rickmorty.databinding.CharacterHolderBinding
import com.cdmp.rickmorty.domain.model.CharacterModel
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import arrow.effects.extensions.io.dispatchers.default
import com.cdmp.rickmorty.R
import com.cdmp.rickmorty.databinding.LoadingHolderBinding
import com.cdmp.rickmorty.presentation.home.model.CharacterDisplayModel
import com.cdmp.rickmorty.presentation.home.model.HomeItemDisplayModel
import com.cdmp.rickmorty.presentation.home.model.HomeItemViewType
import com.cdmp.rickmorty.presentation.home.model.LoadingDisplayModel
import com.cdmp.rickmorty.utils.view.CircleTransform
import com.squareup.picasso.Picasso


class CharactersAdapter(
    items: MutableList<HomeItemDisplayModel> = mutableListOf(),
    private val clickCallback: (Int) -> Unit,
    private val loadingReached: () -> Unit
) : DiffAdapter<HomeViewHolder, HomeItemDisplayModel>(items) {

    override fun onBind(holder: HomeViewHolder, item: HomeItemDisplayModel) {
        if (holder is CharacterViewHolder && item is CharacterDisplayModel) {
            holder.bind(item) { clickCallback(item.id) }
        } else if (holder is LoadingViewHolder && item is LoadingDisplayModel) {
            holder.bind()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context).run {
        when (viewType) {
            HomeItemViewType -> {
                val binding = DataBindingUtil.inflate<CharacterHolderBinding>(
                    this, R.layout.character_holder, parent, false
                )
                CharacterViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<LoadingHolderBinding>(
                    this, R.layout.loading_holder, parent, false
                )
                LoadingViewHolder(binding, loadingReached)
            }
        }
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()
}

sealed class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view)

class CharacterViewHolder(private var binding: CharacterHolderBinding) : HomeViewHolder(binding.root) {

    fun bind(item: CharacterDisplayModel, clickListener: () -> Unit) {
        binding.root.setOnClickListener { _ -> clickListener() }
        Picasso.get()
            .load(item.image)
            .placeholder(R.color.characterBackground)
            .transform(CircleTransform)
            .into(binding.image)
        binding.name.text = item.name
        binding.executePendingBindings()
    }
}

class LoadingViewHolder(binding: LoadingHolderBinding, val onBindCallback: () -> Unit) : HomeViewHolder(binding.root) {

    fun bind() {
        onBindCallback()
    }
}
