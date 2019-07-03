package com.cdmp.rickmorty.presentation.locations

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
import com.cdmp.rickmorty.databinding.LocationsFragmentBinding
import com.cdmp.rickmorty.presentation.home.HomeFragment
import com.cdmp.rickmorty.presentation.home.HomeViewModel
import com.cdmp.rickmorty.presentation.home.MainActivity
import com.cdmp.rickmorty.presentation.home.adapter.CharactersAdapter
import com.cdmp.rickmorty.presentation.locations.adapter.LocationsAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.locations_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment : Fragment() {

    private lateinit var binding: LocationsFragmentBinding

    private val viewModel: HomeViewModel by viewModel()
    private val adapter = LocationsAdapter(
        clickCallback = {
            viewModel.characterClicked(it)
        }, loadingReached = {
            viewModel.loadNext()
        }
    )

    companion object {
        fun newInstance() = LocationsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.locations_fragment, container, false
        )
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locations_recycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        locations_recycler.adapter = adapter
        //   viewModel.characterList.observe(this, Observer { characters ->
        //   characters?.let { adapter.updateListItems(it) }
        //   })
        //  viewModel.start()
    }
}