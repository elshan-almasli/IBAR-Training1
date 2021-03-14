package com.anushka.didemo.di

import com.anushka.didemo.MainActivity
import com.anushka.didemo.SmartPhone
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MemoryCardModule::class,
        LionBatteryModule::class
    ]
)
interface SmarthPhoneComponent {

    fun inject(mainActivity: MainActivity)
}