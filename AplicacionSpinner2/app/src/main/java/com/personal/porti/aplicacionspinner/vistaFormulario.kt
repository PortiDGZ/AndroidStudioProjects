package com.personal.porti.aplicacionspinner

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
        val campoImagen = findViewById<ImageView>(R.id.imagen)
        val bitmapString = intent.getStringExtra("imagen")
        campoImagen.setImageBitmap(BitmapFactory.decodeFile(bitmapString))

        campoNombre.text = "Nombre: " + nombre
        campoApellidos.text = "Apellidos: " + apellidos
        campoEdad.text = "Edad: " + edad
        campoEtapa.text = "Etapa Educativa: " + etapaed
        campoEmail.text = "Email: " + email


    }
}