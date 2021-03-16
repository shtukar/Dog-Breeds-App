package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.shtukarrv.dogbreedsapp.R
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogSubBreed
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseDiffCalculator
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseRecyclerAdapter
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseRecyclerHolder
import kotlinx.android.synthetic.main.dog_sub_breed_item.view.*

class DogSubBreedsListAdapter(context: Context) : BaseRecyclerAdapter<DogSubBreed>(context) {

    override val diffCalculator: BaseDiffCalculator<DogSubBreed> = BaseDiffCalculator()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dog_sub_breed_item, parent, false)
        return BaseRecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.tvDogSubBreedName.text = items[position].name.capitalize()
    }
}