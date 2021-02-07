package com.personal.porti.aplicacionspinner

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    lateinit var campoNombre: TextInputEditText
    lateinit var campoApellidos : TextInputEditText
    lateinit var campoEdad : TextInputEditText
    lateinit var campoEmail : TextInputEditText
    lateinit var parentMenu : TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        campoNombre = findViewById(R.id.campoNombre)
        campoApellidos = findViewById(R.id.campoApellidos)
        campoEdad = findViewById(R.id.campoEdad)
        campoEmail = findViewById(R.id.campoEmail)
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        val dMenu = findViewById<AutoCompleteTextView>(R.id.menuReal)
        parentMenu = findViewById(R.id.menuDrop)
        val type = arrayOf("Primaria", "Secundaria", "Bachillerato", "Formación Profesional")

        val adapter = ArrayAdapter(
                this,
                R.layout.dropdown_menu_popup_item,
                type)
        dMenu.setAdapter(adapter)

        campoEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                validarCampos()
                if(campoEmail.text.toString().isEmpty()) {
                    campoEmail.error = "Introduce un email"
                }else {
                    if (!campoEmail.text.toString().trim().matches(emailPattern)) {
                        campoEmail.error = "El email introducido es inválido"
                    } else {
                        if (campoEmail.text.toString().trim().matches(emailPattern)) {
                            Toast.makeText(applicationContext, "La dirección de correo es correcta", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        campoNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                validarCampos()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        campoApellidos.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                validarCampos()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        campoEdad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                validarCampos()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        dMenu?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val intent = Intent(this@MainActivity, vistaFormulario::class.java).apply {

                    putExtra("Nombre", campoNombre.text.toString())
                    putExtra("Apellidos", campoApellidos.text.toString())
                    putExtra("Edad", campoEdad.text.toString())
                    putExtra("EtapaEd", type[position])
                    putExtra("Email", campoEmail.text.toString())
                }
                startActivity(intent)
            }

        }

        }

    fun validarCampos(){
        if (campoNombre.text.toString() != "" && campoApellidos.text.toString() != "" && campoEdad.text.toString() != "" && campoEmail.text.toString() != "" && campoEmail.error == null ){

            parentMenu.isEnabled = true
        }
    }
    }