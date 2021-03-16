package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.shtukarrv.dogbreedsapp.R
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreed
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogBreedImage
import com.gmail.shtukarrv.dogbreedsapp.domain.entity.DogSubBreed
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseDiffCalculator
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseRecyclerAdapter
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.BaseRecyclerHolder
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.dpToPx
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.hide
import com.gmail.shtukarrv.dogbreedsapp.presentation.extentions.show
import com.gmail.shtukarrv.dogbreedsapp.presentation.utils.HorizontalSpaceItemDecoration
import kotlinx.android.synthetic.main.dog_breed_item.view.*

class DogBreedsListAdapter(
    context: Context,
    private val subBreedClickListener: (breedName: String, subBreedName: String) -> Unit
) : BaseRecyclerAdapter<DogBreed>(context) {

    override val diffCalculator: BaseDiffCalculator<DogBreed> = BaseDiffCalculator()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dog_breed_item, parent, false)
        return BaseRecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.tvDogBreedName.text = items[position].name.capitalize()

        if (items[position].subBreed.isNotEmpty()) {
            holder.itemView.rvDogSubBreeds.show()
            setupSubList(
                holder.itemView.rvDogSubBreeds,
                items[position].subBreed,
                items[position].name
            )
        } else {
            holder.itemView.rvDogSubBreeds.hide()
        }
    }

    private fun setupSubList(
        rvDogSubBreeds: RecyclerView,
        list: List<DogSubBreed>,
        breedName: String
    ) {
        val subBreedsAdapter = DogSubBreedsListAdapter(context)
        rvDogSubBreeds.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(HorizontalSpaceItemDecoration(8f.dpToPx().toInt()))
            adapter = subBreedsAdapter
        }
        subBreedsAdapter.items = list.toMutableList()
        subBreedsAdapter.itemClicked {
            subBreedClickListener.invoke(breedName, it.name)
        }
    }
}