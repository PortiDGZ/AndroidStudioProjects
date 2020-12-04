package com.pforbes.binarycalc

import android.app.ActionBar
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView


open class MainActivity : AppCompatActivity() {
    var currentValue = 0
    var operand = 0
    var didEnterOperand = false
    var entry: String? = null

    private enum class Operation {
        AND_OP, OR_OP, XOR_OP, NAND_OP, NOR_OP, NXOR_OP, NO_OP, ADD_OP, SUB_OP, MOD_OP, MULT_OP
    }

    private var mOperation: Operation? = null
    var display_binary: TextView? = null
    var display_decimal: TextView? = null

    //// GENERAL ACTIVITY CALLBACKS ////
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display_binary = findViewById(R.id.result_binary) as TextView?
        display_decimal = findViewById(R.id.result_decimal) as TextView?
        currentValue = 0
        operand = 0
        mOperation = Operation.NO_OP
        didEnterOperand = false
    }

    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    //// KEYPAD CALLBACKS ////
    fun onEnterZero(view: View?) {
        currentValue = currentValue shl 1
        updateDisplay()
    }

    fun onEnterOne(view: View?) {
        currentValue = (currentValue shl 1) + 1
        updateDisplay()
    }

    fun onClear(view: View?) {
        currentValue = 0
        didEnterOperand = false
        entry = null
        updateDisplay()
    }

    fun onAllClear(view: View?) {
        currentValue = 0
        didEnterOperand = false
        entry = null
        updateDisplay()
    }

    fun computeAnd(view: View?) {
        mOperation = Operation.AND_OP
        display_decimal.setText(display_decimal.getText().toString() + " & ")
        exchange()
    }

    fun computeOr(view: View?) {
        mOperation = Operation.OR_OP
        display_decimal.setText(display_decimal.getText().toString() + " | ")
        exchange()
    }

    fun computeNot(view: View?) {
        currentValue = invertInPlace(currentValue)
        updateDisplay()
    }

    fun computeXor(view: View?) {
        mOperation = Operation.XOR_OP
        display_decimal.setText(display_decimal.getText().toString() + " ^ ")
        exchange()
    }

    fun computeNand(view: View?) {
        mOperation = Operation.NAND_OP
        display_decimal.setText(display_decimal.getText().toString() + " &! ")
        exchange()
    }

    fun computeNor(view: View?) {
        mOperation = Operation.NOR_OP
        display_decimal.setText(display_decimal.getText().toString() + " |! ")
        exchange()
    }

    fun computeNxor(view: View?) {
        mOperation = Operation.NXOR_OP
        display_decimal.setText(display_decimal.getText().toString() + " ^! ")
        exchange()
    }

    fun computeAdd(view: View?) {
        mOperation = Operation.ADD_OP
        display_decimal.setText(display_decimal.getText().toString() + " + ")
        exchange()
    }

    fun computeSub(view: View?) {
        mOperation = Operation.SUB_OP
        display_decimal.setText(display_decimal.getText().toString() + " - ")
        exchange()
    }

    fun computeMod(view: View?) {
        mOperation = Operation.MOD_OP
        display_decimal.setText(display_decimal.getText().toString() + " mod ")
        exchange()
    }

    fun computeMult(view: View?) {
        mOperation = Operation.MULT_OP
        display_decimal.setText(display_decimal.getText().toString() + " * ")
        exchange()
    }

    fun calculate(view: View?) {
        if (didEnterOperand) {
            when (mOperation) {
                Operation.AND_OP -> currentValue = currentValue and operand
                Operation.OR_OP -> currentValue = currentValue or operand
                Operation.XOR_OP -> currentValue = currentValue xor operand
                Operation.NAND_OP -> currentValue = operand and invertInPlace(currentValue)
                Operation.NOR_OP -> currentValue = operand or invertInPlace(currentValue)
                Operation.NXOR_OP -> currentValue = operand xor invertInPlace(currentValue)
                Operation.ADD_OP -> currentValue += operand
                Operation.SUB_OP -> currentValue = operand - currentValue
                Operation.MULT_OP -> currentValue *= operand
                Operation.MOD_OP -> currentValue = operand % currentValue
            }
            display_binary.setText(Integer.toBinaryString(currentValue))
            display_decimal.setText(currentValue.toString() + "")
            didEnterOperand = false
            currentValue = 0
            operand = 0
        }
    }

    //// PRIVATE METHODS ////
    private fun updateDisplay() {
        display_binary.setText(Integer.toBinaryString(currentValue))
        if (!didEnterOperand) {
            display_decimal.setText(currentValue.toString() + "")
        } else {
            display_decimal.setText(entry + currentValue)
        }
    }

    private fun exchange() {
        operand = currentValue
        currentValue = 0
        didEnterOperand = true
        entry = display_decimal.getText().toString()
    }

    private fun invertInPlace(x: Int): Int {
        val k = Integer.toBinaryString(x).length
        return x.inv() and (1 shl k) - 1
    }
}