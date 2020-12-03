package com.mapasgoogle.javi.calculator

/**
 * Created by javi on 22/11/17.
 */
class Operation {
    var numberOne = 0.0
    var numberTwo = 0.0
    var operation1: String? = null

    constructor(numberOne: Int, numberTwo: Int, operation: String?) {
        this.numberOne = numberOne.toDouble()
        this.numberTwo = numberTwo.toDouble()
        operation1 = operation
    }

    constructor() {}

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

    fun operationPorcentage(n1: Double, n2: Double): Double {
        return n1 / 100 * n2
    }
}