package com.personal.porti.aplicacionspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class vistaFormulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_formulario)

        val nombre = intent.getStringExtra("Nombre")
        val apellidos = intent.getStringExtra("Apellidos")
        val edad = intent.getStringExtra("Edad")
        val etapaed = intent.getStringExtra("EtapaEd")
        val email = intent.getStringExtra("Email")

        val campoNombre =findViewById<TextView>(R.id.nombreCard)
        val campoApellidos = findViewById<TextView>(R.id.apellidosCard)
        val campoEdad = findViewById<TextView>(R.id.edadCard)
        val campoEtapa = findViewById<TextView>(R.id.etapaCard)
        val campoEmail = findViewById<TextView>(R.id.emailCard)

        campoNombre.text = nombre
        campoApellidos.text = apellidos
        campoEdad.text = edad
        campoEtapa.text = etapaed
        campoEmail.text = email


    }
}