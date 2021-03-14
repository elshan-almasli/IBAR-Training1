package com.anushka.didemo

import android.util.Log
import javax.inject.Inject


class LIonBattery @Inject constructor(): Battery {
    override fun getPower() {
        Log.i("TAG", "getPower: from Lion Battery")
    }
}