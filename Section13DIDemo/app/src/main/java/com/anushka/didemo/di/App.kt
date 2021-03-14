package com.anushka.didemo.di

import android.app.Application

class App: Application() {

    lateinit var smarthPhoneComponent: SmarthPhoneComponent
    override fun onCreate() {
        smarthPhoneComponent = initDagger()
        super.onCreate()
    }

    private fun initDagger() = DaggerSmarthPhoneComponent
        .builder()
        .memoryCardModule(MemoryCardModule(100))
        .build()
}