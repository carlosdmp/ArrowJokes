package com.cdmp.rickmorty.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cdmp.rickmorty.R
import com.cdmp.rickmorty.databinding.HomeFragmentBinding
import com.cdmp.rickmorty.presentation.home.adapter.CharactersAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private val viewModel: HomeViewModel by viewModel()
    private val adapter = CharactersAdapter(
        clickCallback = {
            viewModel.characterClicked(it)
        }, loadingReached = {
            viewModel.loadNext()
        }
    )

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false
        )
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        character_recycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        character_recycler.adapter = adapter
        viewModel.characterList.observe(this, Observer { characters ->
            characters?.let { adapter.updateListItems(it) }
        })
        viewModel.start()


    }
}
