package com.cdmp.rickmorty.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cdmp.rickmorty.R
import com.cdmp.rickmorty.databinding.CharacterHolderBinding
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
) : ListAdapter<HomeItemDisplayModel, CharactersAdapter.HomeViewHolder>(object :
    DiffUtil.ItemCallback<HomeItemDisplayModel>() {
    override fun areItemsTheSame(oldItem: HomeItemDisplayModel, newItem: HomeItemDisplayModel) =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: HomeItemDisplayModel, newItem: HomeItemDisplayModel) =
        oldItem.areContentsTheSame(newItem)
}) {
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (val i = getItem(position)) {
            is CharacterDisplayModel -> {
                if (holder is HomeViewHolder.CharacterViewHolder) {
                    holder.bind(i) { clickCallback(i.id) }
                }
            }
            is LoadingDisplayModel -> {
                if (holder is HomeViewHolder.LoadingViewHolder) {
                    holder.bind()
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context).run {
        when (viewType) {
            HomeItemViewType -> {
                val binding = DataBindingUtil.inflate<CharacterHolderBinding>(
                    this, R.layout.character_holder, parent, false
                )
                HomeViewHolder.CharacterViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<LoadingHolderBinding>(
                    this, R.layout.loading_holder, parent, false
                )
                HomeViewHolder.LoadingViewHolder(binding, loadingReached)
            }
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).getViewType()


    sealed class HomeViewHolder (view: View) : RecyclerView.ViewHolder(view){
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

        class LoadingViewHolder(binding: LoadingHolderBinding, val onBindCallback: () -> Unit) :
            HomeViewHolder(binding.root) {
            fun bind() {
                onBindCallback()
            }
        }
    }


}