package com.mapasgoogle.javi.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.textclassifier.TextClassifier.NO_OP
import android.widget.TextView

open class BinaryCalc : AppCompatActivity() {
    private var valorActual = 0
    private var operando = 0
    private var hayOperando = false
    private var entrada: String? = null
    /*Para evitar los NullPointerExcpetion Kotlin implementa el null safety
     es decir, la interrogación al final te permite introducir nulos como valores sin provocar un NullPointerException
     */

    private enum class BinOperation {
        AND_OP, OR_OP, XOR_OP, NAND_OP, NOR_OP, NXOR_OP, NO_OP, ADD_OP, SUB_OP, MOD_OP, MULT_OP
    } //Enumaración para almacenar las distintas operaciones

    private var mOperation: BinOperation? = null
    private var mostrarBinario: TextView? = null
    private var mostrarDecimal: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_calc)
        mostrarBinario = findViewById(R.id.result_binary)
        mostrarDecimal = findViewById(R.id.result_decimal)
        valorActual = 0
        operando = 0
        mOperation = BinOperation.NO_OP
        hayOperando = false
    }



    //// Llamadas al teclado ////
    fun pulsarCero(view: View) {
        valorActual = valorActual shl 1
        actualizarVista()
    }

    fun pulsarUno(view: View?) {
        valorActual = (valorActual shl 1) + 1
        actualizarVista()
    }

    fun pulsarBorrar(view: View?) {
        valorActual = 0
        hayOperando = false
        entrada = null
        actualizarVista()
    }

    fun pulsarBorrarTodo(view: View?) {
        valorActual = 0
        hayOperando = false
        entrada = null
        actualizarVista()
    }

    fun calcularAnd(view: View?) {
        mOperation = BinOperation.AND_OP
        (mostrarDecimal!!.text.toString() + " & ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeOr(view: View?) {
        mOperation = BinOperation.OR_OP
        (mostrarDecimal!!.text.toString() + " | ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeNot(view: View?) {
        valorActual = invertir(valorActual)
        actualizarVista()
    }

    fun computeXor(view: View?) {
        mOperation = BinOperation.XOR_OP
        (mostrarDecimal!!.text.toString() + " ^ ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeNand(view: View?) {
        mOperation = BinOperation.NAND_OP
        (mostrarDecimal!!.text.toString() + " &! ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeNor(view: View?) {
        mOperation = BinOperation.NOR_OP
        (mostrarDecimal!!.text.toString() + " |! ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeNxor(view: View?) {
        mOperation = BinOperation.NXOR_OP
        (mostrarDecimal!!.text.toString() + " ^! ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeAdd(view: View?) {
        mOperation = BinOperation.ADD_OP
        (mostrarDecimal!!.text.toString() + " + ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeSub(view: View?) {
        mOperation = BinOperation.SUB_OP
        (mostrarDecimal!!.text.toString() + " - ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeMod(view: View?) {
        mOperation = BinOperation.MOD_OP
        (mostrarDecimal!!.text.toString() + " mod ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun computeMult(view: View?) {
        mOperation = BinOperation.MULT_OP
        "${mostrarDecimal!!.text} * ".also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcular(view: View?) {
        if (hayOperando) {
            when (mOperation) {
                BinOperation.AND_OP -> valorActual = valorActual and operando
                BinOperation.OR_OP -> valorActual = valorActual or operando
                BinOperation.XOR_OP -> valorActual = valorActual xor operando
                BinOperation.NAND_OP -> valorActual = operando and invertir(valorActual)
                BinOperation.NOR_OP -> valorActual = operando or invertir(valorActual)
                BinOperation.NXOR_OP -> valorActual = operando xor invertir(valorActual)
                BinOperation.ADD_OP -> valorActual += operando
                BinOperation.SUB_OP -> valorActual = operando - valorActual
                BinOperation.MULT_OP -> valorActual *= operando
                BinOperation.MOD_OP -> valorActual = operando % valorActual
                else -> if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NO_OP
                }

            }
            mostrarBinario?.text = Integer.toBinaryString(valorActual)
            (valorActual.toString() + "").also { mostrarDecimal?.text = it }
            hayOperando = false
            valorActual = 0
            operando = 0
        }
    }

    //// Métodos privados ////
    //Muestra los números conforme se van escribiendo
    private fun actualizarVista() {
        mostrarBinario?.text = Integer.toBinaryString(valorActual)
        if (!hayOperando) {
            mostrarDecimal?.text = """$valorActual"""
        } else "${entrada}$valorActual".also { mostrarDecimal?.text = it }
    }
    // Muestra los números decimales
    private fun intercambio() {
        operando = valorActual
        valorActual = 0
        hayOperando = true
        entrada = mostrarDecimal?.text.toString()
    }
    // Invierte el número
    private fun invertir(x: Int): Int {
        val k = Integer.toBinaryString(x).length
        return x.inv() and (1 shl k) - 1
    }
}