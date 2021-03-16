package com.gmail.shtukarrv.dogbreedsapp.presentation.extentions

import android.view.View
import com.gmail.shtukarrv.dogbreedsapp.presentation.common.DebouncingOnClickListener

fun View.doOnClick(clickAction: (view: View) -> Unit) {
    this.setOnClickListener(DebouncingOnClickListener(clickAction))
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(makeInvisible: Boolean = false) {
    this.visibility = if (makeInvisible) View.INVISIBLE else View.GONE
}