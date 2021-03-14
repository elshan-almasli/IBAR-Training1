package com.anushka.viewmodeldemo1

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var count: Int = 0

    fun updateCount(inputNumber: Int) {
        count += inputNumber
    }

    fun getCount(): Int {
        return count
    }
}