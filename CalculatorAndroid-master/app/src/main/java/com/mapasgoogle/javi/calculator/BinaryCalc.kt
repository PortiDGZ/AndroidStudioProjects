package com.mapasgoogle.javi.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.textclassifier.TextClassifier.NO_OP
import android.widget.TextView

open class BinaryCalc : AppCompatActivity() {
    var currentValue = 0
    var operand = 0
    var didEnterOperand = false
    var entry: String? = null

    private enum class BinOperation {
        AND_OP, OR_OP, XOR_OP, NAND_OP, NOR_OP, NXOR_OP, NO_OP, ADD_OP, SUB_OP, MOD_OP, MULT_OP
    }

    private var mOperation: BinOperation? = null
    private var displayBinary: TextView? = null
    private var displayDecimal: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayBinary = findViewById<TextView>(R.id.result_binary)
        displayDecimal = findViewById<TextView>(R.id.result_decimal)
        currentValue = 0
        operand = 0
        mOperation = BinOperation.NO_OP
        didEnterOperand = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    //// KEYPAD CALLBACKS ////
    fun onEnterZero(view: View) {
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
        mOperation = BinOperation.AND_OP
        (displayDecimal!!.text.toString() + " & ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeOr(view: View?) {
        mOperation = BinOperation.OR_OP
        (displayDecimal!!.text.toString() + " | ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeNot(view: View?) {
        currentValue = invertInPlace(currentValue)
        updateDisplay()
    }

    fun computeXor(view: View?) {
        mOperation = BinOperation.XOR_OP
        (displayDecimal!!.text.toString() + " ^ ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeNand(view: View?) {
        mOperation = BinOperation.NAND_OP
        (displayDecimal!!.text.toString() + " &! ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeNor(view: View?) {
        mOperation = BinOperation.NOR_OP
        (displayDecimal!!.text.toString() + " |! ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeNxor(view: View?) {
        mOperation = BinOperation.NXOR_OP
        (displayDecimal!!.text.toString() + " ^! ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeAdd(view: View?) {
        mOperation = BinOperation.ADD_OP
        (displayDecimal!!.text.toString() + " + ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeSub(view: View?) {
        mOperation = BinOperation.SUB_OP
        (displayDecimal!!.text.toString() + " - ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeMod(view: View?) {
        mOperation = BinOperation.MOD_OP
        (displayDecimal!!.text.toString() + " mod ").also { displayDecimal?.text = it }
        exchange()
    }

    fun computeMult(view: View?) {
        mOperation = BinOperation.MULT_OP
        "${displayDecimal!!.text} * ".also { displayDecimal?.text = it }
        exchange()
    }

    fun calculate(view: View?) {
        if (didEnterOperand) {
            when (mOperation) {
                BinOperation.AND_OP -> currentValue = currentValue and operand
                BinOperation.OR_OP -> currentValue = currentValue or operand
                BinOperation.XOR_OP -> currentValue = currentValue xor operand
                BinOperation.NAND_OP -> currentValue = operand and invertInPlace(currentValue)
                BinOperation.NOR_OP -> currentValue = operand or invertInPlace(currentValue)
                BinOperation.NXOR_OP -> currentValue = operand xor invertInPlace(currentValue)
                BinOperation.ADD_OP -> currentValue += operand
                BinOperation.SUB_OP -> currentValue = operand - currentValue
                BinOperation.MULT_OP -> currentValue *= operand
                BinOperation.MOD_OP -> currentValue = operand % currentValue
                else -> if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NO_OP
                }

            }
            displayBinary?.text = Integer.toBinaryString(currentValue)
            (currentValue.toString() + "").also { displayDecimal?.text = it }
            didEnterOperand = false
            currentValue = 0
            operand = 0
        }
    }

    //// PRIVATE METHODS ////
    private fun updateDisplay() {
        displayBinary?.text = Integer.toBinaryString(currentValue)
        if (!didEnterOperand) {
            displayDecimal?.text = """$currentValue"""
        } else "${entry}$currentValue".also { displayDecimal?.text = it }
    }

    private fun exchange() {
        operand = currentValue
        currentValue = 0
        didEnterOperand = true
        entry = displayDecimal?.text.toString()
    }

    private fun invertInPlace(x: Int): Int {
        val k = Integer.toBinaryString(x).length
        return x.inv() and (1 shl k) - 1
    }
}