package com.mapasgoogle.javi.calculator

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var textViewBox: TextView? = null
    private var buttonDel: Button? = null
    private var button0: Button? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null
    private var buttonAC: Button? = null
    private var buttonMM: Button? = null
    private var buttonPorc: Button? = null
    private var buttonDiv: Button? = null
    private var buttonPro: Button? = null
    private var buttonRes: Button? = null
    private var buttonSum: Button? = null
    private var buttonEqu: Button? = null
    private var buttonDec: Button? = null
    private var num1: String? = null
    private var num2: String? = null
    private var stringResult: String? = "0"
    private var nextMark = true
    private var markNum2 = false
    private var lastPressedKey: String? = null
    private var operation: Operation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializo el objeto que llevara toda la operacion
        operation = Operation(0, 0, null)
        textViewBox = findViewById<View>(R.id.tvBox) as TextView
        buttonDel = findViewById<View>(R.id.btDel) as Button
        buttonAC = findViewById<View>(R.id.btAC) as Button
        buttonMM = findViewById<View>(R.id.btMM) as Button
        buttonPorc = findViewById<View>(R.id.btPorc) as Button
        buttonDiv = findViewById<View>(R.id.btDiv) as Button
        buttonPro = findViewById<View>(R.id.btPro) as Button
        buttonRes = findViewById<View>(R.id.btRes) as Button
        buttonSum = findViewById<View>(R.id.btSum) as Button
        button0 = findViewById<View>(R.id.bt0) as Button
        button1 = findViewById<View>(R.id.bt1) as Button
        button2 = findViewById<View>(R.id.bt2) as Button
        button3 = findViewById<View>(R.id.bt3) as Button
        button4 = findViewById<View>(R.id.bt4) as Button
        button5 = findViewById<View>(R.id.bt5) as Button
        button6 = findViewById<View>(R.id.bt6) as Button
        button7 = findViewById<View>(R.id.bt7) as Button
        button8 = findViewById<View>(R.id.bt8) as Button
        button9 = findViewById<View>(R.id.bt9) as Button
        buttonDec = findViewById<View>(R.id.btDec) as Button
        buttonEqu = findViewById<View>(R.id.btEqu) as Button
        buttonAC!!.setOnClickListener(this)
        buttonDel!!.setOnClickListener(this)
        buttonMM!!.setOnClickListener(this)
        buttonPorc!!.setOnClickListener(this)
        buttonDiv!!.setOnClickListener(this)
        buttonPro!!.setOnClickListener(this)
        buttonRes!!.setOnClickListener(this)
        buttonSum!!.setOnClickListener(this)
        button0!!.setOnClickListener(this)
        button1!!.setOnClickListener(this)
        button2!!.setOnClickListener(this)
        button3!!.setOnClickListener(this)
        button4!!.setOnClickListener(this)
        button5!!.setOnClickListener(this)
        button6!!.setOnClickListener(this)
        button7!!.setOnClickListener(this)
        button8!!.setOnClickListener(this)
        button9!!.setOnClickListener(this)
        buttonDec!!.setOnClickListener(this)
        buttonEqu!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btAC -> cleanAll()
            R.id.btDel -> cleanCharByChar()
            R.id.btMM -> changeSimbol()
            R.id.btPorc -> configureOperation("porc", buttonPorc)
            R.id.bt7 -> writeNumberOperation("7")
            R.id.bt8 -> writeNumberOperation("8")
            R.id.bt9 -> writeNumberOperation("9")
            R.id.bt6 -> writeNumberOperation("6")
            R.id.bt5 -> writeNumberOperation("5")
            R.id.bt4 -> writeNumberOperation("4")
            R.id.bt3 -> writeNumberOperation("3")
            R.id.bt2 -> writeNumberOperation("2")
            R.id.bt1 -> writeNumberOperation("1")
            R.id.bt0 -> writeNumberOperation("0")
            R.id.btDec -> {
                if (stringResult == "") stringResult = "0"
                writeNumberOperation(",")
            }
            R.id.btDiv -> configureOperation("div", buttonDiv)
            R.id.btPro -> configureOperation("prod", buttonPro)
            R.id.btRes -> configureOperation("res", buttonRes)
            R.id.btSum -> configureOperation("sum", buttonSum)
            else -> {
                if (operation!!.operation1 != null) {
                    checkOperation()
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    buttonDiv!!.background = getDrawable(R.drawable.button_secundary_no_pressed)
                    buttonPorc!!.background = getDrawable(R.drawable.button_secundary_no_pressed)
                    buttonPro!!.background = getDrawable(R.drawable.button_secundary_no_pressed)
                    buttonRes!!.background = getDrawable(R.drawable.button_secundary_no_pressed)
                    buttonSum!!.background = getDrawable(R.drawable.button_secundary_no_pressed)
                }
                markNum2 = false
                lastPressedKey = "="
            }
        }
        textViewBox!!.text = stringResult
    }

    /**
     * Convierte a binario el resultado
     */

    fun baseChange(view: View){

        val intent = Intent(this, BinarayCalc::class.java)

        startActivity(intent)


    }

    /**
     * Establece la operacion segun el estado de la calculadora
     */
    private fun configureOperation(ope: String, button: Button?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button!!.background = getDrawable(R.drawable.button_secundary_pressed)
        }
        if (lastPressedKey !== ope) {
            if (lastPressedKey === "=") {
                nextMark = false
                markNum2 = true
                operation!!.operation1 = ope
            } else {
                checkOperation()
                operation!!.operation1 = ope
                num2 = null
                operation!!.numberTwo = 0.0
                lastPressedKey = ope
            }
        }
    }

    /**
     * Realiza la operacion
     */
    private fun checkOperation() {
        if (operation!!.operation1 != null && operation!!.numberTwo != 0.0) {
            // Realizo operacion
            when (operation!!.operation1) {
                "sum" -> stringResult = operation!!.operationSum(operation!!.numberOne, operation!!.numberTwo).toString()
                "res" -> stringResult = operation!!.operationDeduct(operation!!.numberOne, operation!!.numberTwo).toString()
                "prod" -> stringResult = operation!!.operationProduct(operation!!.numberOne, operation!!.numberTwo).toString()
                "div" -> stringResult = operation!!.operationDivision(operation!!.numberOne, operation!!.numberTwo).toString()
                "porc" -> stringResult = operation!!.operationPorcentage(operation!!.numberOne, operation!!.numberTwo).toString()
            }
            if (stringResult!!.substring(stringResult!!.length - 1, stringResult!!.length) == "0" && stringResult!!.substring(stringResult!!.length - 2, stringResult!!.length - 1) == ".") {
                stringResult = stringResult!!.replace(".0", "")
            }
        }
        if (stringResult!!.indexOf(".") > -1 && stringResult!!.length > 1) {
            deleteCeros()
        }
        num1 = stringResult
        operation!!.numberOne = num1!!.toDouble()
        nextMark = false
        markNum2 = true
        if (lastPressedKey != null) {
            markNum2 = false
            if (lastPressedKey != "=") {
                num2 = null
            }
        }
    }

    /**
     * Elimina ceros que puede dar el double a partir de la coma
     */
    private fun deleteCeros() {
        var i = stringResult!!.length - 1
        while (i > stringResult!!.indexOf(".")) {
            val n = stringResult!![i].toString()
            if (n == "0") {
                stringResult = stringResult!!.substring(0, i)
                i--
            }
            i--
        }
        if (stringResult!!.length > 1 && stringResult!!.substring(stringResult!!.length - 1, stringResult!!.length) == ".") {
            stringResult = stringResult!!.replace(".", "")
        }
    }

    /**
     * Borra caracter a caracter
     */
    private fun cleanCharByChar() {
        stringResult = if (stringResult!!.length <= 1) {
            ""
        } else {
            stringResult!!.substring(0, stringResult!!.length - 1)
        }
        if (stringResult!!.length > 1 && stringResult!!.substring(stringResult!!.length - 1, stringResult!!.length) === ",") {
            stringResult = stringResult!!.replace(",", "")
        }
        if (stringResult != "") {
            insertNumberOperation(stringResult!!.toDouble())
        }
        nextMark = true
    }

    /**
     * Escribe la tecla pulsada
     * @param s
     */
    private fun writeNumberOperation(s: String) {
        if (stringResult == "0" && s != "," || !nextMark) stringResult = ""
        if (s == ",") { // Si pulsamos la coma de decimal...
            if (stringResult!!.indexOf(".") > -1) { // Comprobamos que no hay una coma ya
                return
            } else { // Si no la hay, la ponemos y concatenamos un 0 para que no falle al insertar el dato en el objeto, ya que es un double
                if (markNum2) {
                    num2 = if (operation!!.numberTwo != 0.0) {
                        ".0"
                    } else {
                        "0.0"
                    }
                } else {
                    num1 = ".0"
                }
            }
        } else { // Si pulsamos cualquier numero...
            if (markNum2) {
                num2 = if (num2 != null && num2!!.indexOf(",") > -1) { // Comprobamos si hay coma de decimal, si la hay debemos borrar el 0 que le precede
                    num2!!.replace(".0", "")
                } else {
                    s
                }
            } else {
                num1 = if (num1 != null && num1!!.indexOf(",") > -1) { // Comprobamos si hay coma de decimal, si la hay debemos borrar el 0 que le precede
                    num1!!.replace(".0", "")
                } else {
                    s
                }
            }
        }
        if (markNum2) {
            stringResult += num2
            insertNumberOperation(stringResult!!.toDouble())
            if (s == ",") stringResult = stringResult!!.substring(0, stringResult!!.length - 1)
        } else {
            stringResult += num1
            insertNumberOperation(stringResult!!.toDouble())
            if (s == ",") stringResult = stringResult!!.substring(0, stringResult!!.length - 1)
        }
        nextMark = true
        lastPressedKey = null
    }

    /**
     * Inserta el numero en el objeto Operation
     * @param num
     */
    private fun insertNumberOperation(num: Double) {
        if (markNum2) {
            operation!!.numberTwo = num
        } else {
            operation!!.numberOne = num
        }
    }

    /**
     * Pulsa el boton AC, se ponen todas las variables como al principio
     */
    private fun cleanAll() {
        num1 = null
        num2 = null
        stringResult = "0"
        nextMark = true
        markNum2 = false
        lastPressedKey = null
        operation!!.numberOne = 0.0
        operation!!.numberTwo = 0.0
        operation!!.operation1 = null
    }

    /**
     * Cambia el simbolo a negativo o positivo
     */
    private fun changeSimbol() {
        if (stringResult == "0" || markNum2 && num2 == null) stringResult = ""
        // Compruebo que signo tiene el texto de la caja
        stringResult = if (stringResult!!.length >= 1 && stringResult!!.substring(0, 1) == "-") {
            stringResult!!.replace("-", "")
        } else {
            "-$stringResult"
        }
        if (stringResult != "" && stringResult != "-") {
            if (operation!!.numberTwo != 0.0) {
                operation!!.numberTwo = stringResult!!.toDouble()
            } else {
                operation!!.numberOne = stringResult!!.toDouble()
            }
        }
    }
}