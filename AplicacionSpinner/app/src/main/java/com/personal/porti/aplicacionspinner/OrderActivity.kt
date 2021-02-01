package com.personal.porti.aplicacionspinner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * This activity shows the order chosen.  The order is sent as data
 * with the intent to launch this activity.
 */
class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Get the intent and its data.
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.order_textview)
        textView.text = message
    }
}
