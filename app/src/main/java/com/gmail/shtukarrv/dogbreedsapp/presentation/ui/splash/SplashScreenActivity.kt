package com.gmail.shtukarrv.dogbreedsapp.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.gmail.shtukarrv.dogbreedsapp.presentation.ui.getMainActivityLaunchIntent
import com.gmail.shtukarrv.dogbreedsapp.R
import dagger.android.support.DaggerAppCompatActivity

class SplashScreenActivity : DaggerAppCompatActivity() {

    companion object {
        const val DELAY_2000 = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(getMainActivityLaunchIntent())
            finishAffinity()
        }, DELAY_2000)
    }
}
