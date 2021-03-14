package com.anushka.circlecalc

class MyCalc : Calculations {

    private val pi = 3.14

    override fun calculateCircumference(radius: Double): Double {
        if (radius > 0) {
            return 2 * pi * radius
        }
        return -1.0
    }

    override fun calculateArea(radius: Double): Double {
        return pi * radius * radius
    }
}