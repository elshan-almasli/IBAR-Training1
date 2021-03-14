package com.anushka.circlecalc

import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.Exception

class MyCalcTest{

    private lateinit var myCalc: MyCalc

    @Before
    fun setUp() {
        myCalc = MyCalc()
    }

    @Test
    fun calculateCircumference_insertRadius_returnCorrectResult(){
        val result = myCalc.calculateCircumference(2.0)
        assertThat(result).isEqualTo(12.56)
    }

    @Test
    fun calculateCircumference_zeroRadius_returnCorrectResult(){
        val result = myCalc.calculateArea(0.0)
        assertThat(result).isEqualTo(0)
    }

    @Test()
    fun calculateCircumference_insertMinusRadius_returnErrorResult(){

//        throwException()
        val result = myCalc.calculateCircumference(-1.0)
        assertThat(result).isEqualTo(-1.0)
//        Assert.assertEquals(result,"radius may not be negative")
    }

    private fun throwException(): Boolean = throw IllegalArgumentException()

    @Test
    fun calculateArea_insertRadius_returnCorrectResult(){
        val result = myCalc.calculateArea(1.0)
        assertThat(result).isEqualTo(3.14)
    }

    @Test
    fun calculateAre_insertZeroRadius(){
        val result = myCalc.calculateArea(0.0)
        assertThat(result).isEqualTo(0)
    }

}