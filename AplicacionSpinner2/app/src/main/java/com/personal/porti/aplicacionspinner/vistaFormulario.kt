package com.personal.porti.aplicacionspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class vistaFormulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_formulario)

        var nombre = intent.getStringExtra("Nombre")
        var apellidos = intent.getStringExtra("Apellidos")
        var edad = intent.getStringExtra("Edad")
        var etapaed = intent.getStringExtra("EtapaEd")
        var email = intent.getStringExtra("Email")
    }
}