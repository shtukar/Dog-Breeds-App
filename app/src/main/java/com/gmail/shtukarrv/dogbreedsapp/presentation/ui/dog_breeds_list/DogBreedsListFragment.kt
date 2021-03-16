package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.shtukarrv.dogbreedsapp.R
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.hide
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.show
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images.DogBreedImagesViewModel.Companion.ARG_DOG_BREED_NAME
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images.DogBreedImagesViewModel.Companion.ARG_DOG_SUB_BREED_NAME
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.SpaceItemDecoration
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.Status
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dog_breeds.*
import javax.inject.Inject

class DogBreedsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DogBreedsListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DogBreedsListViewModel::class.java)
    }

    private lateinit var dogBreedsAdapter: DogBreedsListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_dog_breeds, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

    }

    private fun setupUI() {
        dogBreedsAdapter = DogBreedsListAdapter(requireContext()) { breedName, subBreedName ->
            findNavController().navigate(
                R.id.dogBreedImagesListFragment,
                bundleOf(
                    ARG_DOG_BREED_NAME to breedName,
                    ARG_DOG_SUB_BREED_NAME to subBreedName
                )
            )
        }
        rvDogBreeds.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(SpaceItemDecoration(context, R.drawable.shape_list_divider))
            adapter = dogBreedsAdapter
        }

        dogBreedsAdapter.itemClicked {
            findNavController().navigate(
                R.id.dogBreedImagesListFragment,
                bundleOf(
                    ARG_DOG_BREED_NAME to it.name
                )
            )
        }
    }

    private fun setupObserver() {
        viewModel.getDogBreeds().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data?.isNotEmpty() == true) {
                        renderDogBreedsList(it.data)
                    } else {
                        renderEmptyListError()
                    }
                }
                Status.LOADING -> {
                    renderLoading()
                }
                Status.ERROR -> {
                    renderError(it.message)
                }
            }
        })
    }

    private fun renderLoading() {
        shimmerView.show()
        rvDogBreeds.hide()
        tvEmptyList.hide()
        tvError.hide()
    }

    private fun renderDogBreedsList(list: List<DogBreed>) {
        shimmerView.hide()
        rvDogBreeds.show()
        tvEmptyList.hide()
        tvError.hide()
        dogBreedsAdapter.items = list.toMutableList()
    }

    private fun renderEmptyListError() {
        shimmerView.hide()
        rvDogBreeds.hide()
        tvEmptyList.show()
        tvError.hide()
    }

    private fun renderError(errorMessage: String?) {
        shimmerView.hide()
        rvDogBreeds.hide()
        tvEmptyList.hide()
        tvError.show()
        tvError.text = errorMessage
    }
}
