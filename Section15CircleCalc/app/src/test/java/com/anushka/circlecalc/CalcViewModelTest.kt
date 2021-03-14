package com.anushka.circlecalc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

class CalcViewModelTest {

    private lateinit var calculations: Calculations
    private lateinit var calcViewModel: CalcViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculations = Mockito.mock(Calculations::class.java)
        Mockito.`when`(calculations.calculateArea(1.0)).thenReturn(3.14)
        Mockito.`when`(calculations.calculateCircumference(1.0)).thenReturn(6.28)
        calcViewModel = CalcViewModel(calculations)
    }

    @Test
    fun calculateArea_radiusSent_updateData() {
        calcViewModel.calculateArea(1.0)
        val result = calcViewModel.areaValue.value
        assertThat(result).isEqualTo("3.14")

    }

    @Test
    fun calculateCircumference_radiusSent_updateData(){
        calcViewModel.calculateCircumference(1.0)
        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("6.28")
    }

}