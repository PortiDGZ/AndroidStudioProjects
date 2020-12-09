package com.mapasgoogle.javi.calculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView

class MenuSeleccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_seleccion)


    }
    var r_group = findViewById<RadioGroup>(R.id.radioGroup)

     fun calcSelector(view: View){

         val intent2: Intent
    val selected = r_group.checkedRadioButtonId.toString()


         if(selected == "decimal"){

             intent2 = Intent(this, MainActivity::class.java)

             startActivity(intent2)

         }else if (selected == "binario"){

             intent2 = Intent(this, BinaryCalc::class.java)

         }

    }
}