package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.shtukarrv.dogbreedsapp.R
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.dpToPx
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.hide
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.show
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.HorizontalSpaceItemDecoration
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.Status
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dog_breed_images.*
import javax.inject.Inject

class DogBreedImagesListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DogBreedImagesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DogBreedImagesViewModel::class.java)
    }

    private lateinit var dogBreedImagesAdapter: DogBreedImagesListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_dog_breed_images, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize(arguments)
        setupUI()
        viewModel.fetchDogBreedImages()
    }

    private fun setupUI() {
        dogBreedImagesAdapter = DogBreedImagesListAdapter(requireContext())
        rvDogBreedImages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(HorizontalSpaceItemDecoration(8f.dpToPx().toInt()))
            adapter = dogBreedImagesAdapter
        }
    }

    private fun setupObserver() {
        viewModel.getDogBreedImages().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data?.isNotEmpty() == true) {
                        renderDogBreedImagesList(it.data)
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
        viewModel.getDogBreedFullName().observe(viewLifecycleOwner, {
            tvDogBreedName.text = it
        })
    }

    private fun renderLoading() {
        shimmerView.show()
        rvDogBreedImages.hide()
        tvEmptyList.hide()
        tvError.hide()
    }

    private fun renderDogBreedImagesList(list: List<DogBreedImage>) {
        shimmerView.hide()
        rvDogBreedImages.show()
        tvEmptyList.hide()
        tvError.hide()
        dogBreedImagesAdapter.items = list.toMutableList()
    }

    private fun renderEmptyListError() {
        shimmerView.hide()
        rvDogBreedImages.hide()
        tvEmptyList.show()
        tvError.hide()
    }

    private fun renderError(errorMessage: String?) {
        shimmerView.hide()
        rvDogBreedImages.hide()
        tvEmptyList.hide()
        tvError.show()
        tvError.text = errorMessage
    }
}
