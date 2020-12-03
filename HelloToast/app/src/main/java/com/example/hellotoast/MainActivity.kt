package com.example.hellotoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var mCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun showToast(view: View) {

        val makeText = Toast.makeText(this, "Hola mundo!", Toast.LENGTH_SHORT)

        makeText.show()
    }
    fun countUp(view: View) {
        mCount++

    val count = findViewById<TextView>(R.id.show_count)

        count.text = mCount.toString()

        

    }
}