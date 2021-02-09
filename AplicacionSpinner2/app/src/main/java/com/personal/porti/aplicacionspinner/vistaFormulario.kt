package com.personal.porti.aplicacionspinner

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


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
        val botonConfirmar = findViewById<MaterialButton>(R.id.BotonConfirmar)
        val botonModificar = findViewById<MaterialButton>(R.id.BotonModificar)
        campoImagen.setImageBitmap(BitmapFactory.decodeFile(bitmapString))
        botonModificar.setTextColor(Color.RED)
        botonConfirmar.setTextColor(Color.GREEN)

        botonConfirmar.setOnClickListener {

            botonConfirmar.isEnabled = false
            botonModificar.isEnabled = false

        }

        botonModificar.setOnClickListener {

            val intent = Intent(this@vistaFormulario, MainActivity::class.java)

            startActivity(intent)

        }

        campoNombre.text = "Nombre: " + nombre
        campoApellidos.text = "Apellidos: " + apellidos
        campoEdad.text = "Edad: " + edad
        campoEtapa.text = "Etapa Educativa: " + etapaed
        campoEmail.text = "Email: " + email


    }
}