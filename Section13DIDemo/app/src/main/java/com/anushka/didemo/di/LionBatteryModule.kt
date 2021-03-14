package com.anushka.didemo.di

import com.anushka.didemo.Battery
import com.anushka.didemo.LIonBattery
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LionBatteryModule {

    @Binds
    abstract fun provideLionBatteryModule(lIonBattery: LIonBattery):Battery

}