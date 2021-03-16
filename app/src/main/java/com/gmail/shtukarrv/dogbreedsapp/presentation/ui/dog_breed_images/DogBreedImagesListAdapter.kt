package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breed_images

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gmail.shtukarrv.dogbreedsapp.R
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseDiffCalculator
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseRecyclerAdapter
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseRecyclerHolder
import kotlinx.android.synthetic.main.dog_breed_image_item.view.*

class DogBreedImagesListAdapter(context: Context) : BaseRecyclerAdapter<DogBreedImage>(context) {

    override val diffCalculator: BaseDiffCalculator<DogBreedImage> = BaseDiffCalculator()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dog_breed_image_item, parent, false)
        return BaseRecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        Glide.with(holder.itemView)
            .load(items[position].imageUrl)
            .placeholder(R.drawable.placeholder_dog_paw)
            .error(R.drawable.placeholder_dog_paw)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .into(holder.itemView.ivDogBreed)
    }
}