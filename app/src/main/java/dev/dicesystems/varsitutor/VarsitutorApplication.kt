package dev.dicesystems.varsitutor

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VarsitutorApplication: Application() {
    override fun onCreate(){
        super.onCreate()
    }
}