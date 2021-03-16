package com.gmail.shtukarrv.dogbreedsapp.presentation.extentions

import android.content.res.Resources

fun Float.dpToPx(): Float {
    return (this * Resources.getSystem().displayMetrics.density)
}