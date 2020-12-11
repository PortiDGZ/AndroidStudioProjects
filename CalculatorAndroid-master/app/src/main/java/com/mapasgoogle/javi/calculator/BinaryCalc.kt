package com.mapasgoogle.javi.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.textclassifier.TextClassifier.NO_OP
import android.widget.Button
import android.widget.TextView

open class BinaryCalc : AppCompatActivity() {
    private var valorActual = 0
    private var operando = 0
    private var hayOperando = false
    private var entrada: String? = null
    /*Para evitar los NullPointerExcpetion Kotlin implementa el null safety
     es decir, la interrogación al final te permite introducir nulos como valores sin provocar un NullPointerException
     */

    private enum class operaciones {
        OperacionAnd, OperacionOr, OperacionXor, OperacionNand, OperacionNor, OperacionNxor, OperacionNot, inicializador, suma, resta, modulo, multiplicacion, division, shiftLeft, shiftRight
    } //Enumeración para almacenar las distintas operaciones

    private var tipoOperacion: operaciones? = null
    private var mostrarBinario: TextView? = null
    private var mostrarDecimal: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binary_calc)
        mostrarBinario = findViewById(R.id.textoBinario)
        mostrarDecimal = findViewById(R.id.textoDecimal)
        valorActual = 0
        operando = 0
        tipoOperacion = operaciones.inicializador
        hayOperando = false
    }



    //Llamadas al teclado
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
        tipoOperacion = operaciones.OperacionAnd
        (mostrarDecimal!!.text.toString() + " & ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularOr(view: View?) {
        tipoOperacion = operaciones.OperacionOr
        (mostrarDecimal!!.text.toString() + " | ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularNot(view: View?) {
        valorActual = invertir(valorActual)
        actualizarVista()
    }

    fun calcularXor(view: View?) {
        tipoOperacion = operaciones.OperacionXor
        (mostrarDecimal!!.text.toString() + " ^ ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularNand(view: View?) {
        tipoOperacion = operaciones.OperacionNand
        (mostrarDecimal!!.text.toString() + " &! ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularNor(view: View?) {
        tipoOperacion = operaciones.OperacionNor
        (mostrarDecimal!!.text.toString() + " |! ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularXnor(view: View?) {
        tipoOperacion = operaciones.OperacionNxor
        (mostrarDecimal!!.text.toString() + " ^! ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularSuma(view: View?) {
        tipoOperacion = operaciones.suma
        (mostrarDecimal!!.text.toString() + " + ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularResta(view: View?) {
        tipoOperacion = operaciones.resta
        (mostrarDecimal!!.text.toString() + " - ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularModulo(view: View?) {
        tipoOperacion = operaciones.modulo
        (mostrarDecimal!!.text.toString() + " mod ").also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularMulti(view: View?) {
        tipoOperacion = operaciones.multiplicacion
        "${mostrarDecimal!!.text} * ".also { mostrarDecimal?.text = it }
        intercambio()
    }

    fun calcularDiv(view: View?){

        tipoOperacion = operaciones.division
        "${mostrarDecimal!!.text} / ".also { mostrarDecimal?.text = it }
        intercambio()

    }

    fun shiftLeft(view: View?){
        valorActual = valorActual.shl(1)

        actualizarVista()
    }

    fun shiftRight(view: View?){

        valorActual = valorActual.shr(1)

        actualizarVista()

    }

    fun calcular(view: View?) {
        if (hayOperando) {
            when (tipoOperacion) {
                operaciones.OperacionAnd -> valorActual = valorActual and operando
                operaciones.OperacionOr -> valorActual = valorActual or operando
                operaciones.OperacionXor -> valorActual = valorActual xor operando
                operaciones.OperacionNand -> valorActual = operando and valorActual.inv()
                operaciones.OperacionNor -> valorActual = operando or valorActual.inv()
                operaciones.OperacionNxor -> valorActual = operando xor valorActual.inv()
                operaciones.OperacionNot -> valorActual = invertir(valorActual)
                operaciones.suma -> valorActual += operando
                operaciones.resta -> valorActual = operando - valorActual
                operaciones.multiplicacion -> valorActual *= operando
                operaciones.modulo -> valorActual = operando % valorActual
                operaciones.division -> valorActual = operando / valorActual
                else ->
                    NO_OP

            }
            mostrarBinario?.text = Integer.toBinaryString(valorActual)
            (valorActual.toString() + "").also { mostrarDecimal?.text = it }
            hayOperando = false
            valorActual = 0
            operando = 0
        }
    }

    //// Métodos privados ////
    //Muestra los números conforme se van escribiendo, es decir, actualiza los TextView
    private fun actualizarVista() {
        mostrarBinario?.text = Integer.toBinaryString(valorActual)
        if (!hayOperando) {
            mostrarDecimal?.text = """$valorActual"""
        } else "${entrada}$valorActual".also { mostrarDecimal?.text = it }
    }
    // Guarda el primer set de números en operando para setear a cero valorActual y poder operar con ambos
    private fun intercambio() {
        operando = valorActual
        valorActual = 0
        hayOperando = true
        entrada = mostrarDecimal?.text.toString()
    }

    fun invertir(x: Int): Int{

        val k = Integer.toBinaryString(x).length

        return x.inv() and (1 shl k) -1;

    }

}