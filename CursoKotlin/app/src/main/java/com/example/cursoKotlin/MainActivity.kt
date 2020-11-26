package com.example.cursoKotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contador.text = "0"

        botonsumar.setOnClickListener(object : View.OnClickListener){

                fun onClick(v: View?) {

                }
        }

        botonrestar.setOnClickListener(object : View.OnClickListener){
            fun onClick(v: View?) {

            }
        }

    }
}
