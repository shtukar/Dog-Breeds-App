package com.gmail.shtukarrv.dogbreedsapp.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gmail.shtukarrv.dogbreedsapp.R
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.dog_breeds_list.DogBreedsListFragment
import dagger.android.support.DaggerAppCompatActivity

fun Context.getMainActivityLaunchIntent(): Intent {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    return intent
}

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
