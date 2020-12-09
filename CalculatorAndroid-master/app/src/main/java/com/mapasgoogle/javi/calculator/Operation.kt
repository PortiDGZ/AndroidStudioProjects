package com.mapasgoogle.javi.calculator

/**
 * Created by javi on 22/11/17.
 */
class Operation(numberOne: Int, numberTwo: Int, operation: String?) {
    var numberOne = 0.0
    var numberTwo = 0.0
    var operation1: String? = operation

    init {
        this.numberOne = numberOne.toDouble()
        this.numberTwo = numberTwo.toDouble()
    }

    fun operationProduct(n1: Double, n2: Double): Double {
        return n1 * n2
    }

    fun operationSum(n1: Double, n2: Double): Double {
        return n1 + n2
    }

    fun operationDeduct(n1: Double, n2: Double): Double {
        return n1 - n2
    }

    fun operationDivision(n1: Double, n2: Double): Double {
        return n1 / n2
    }

    fun operationPercentage(n1: Double, n2: Double): Double {
        return n1 / 100 * n2
    }
}