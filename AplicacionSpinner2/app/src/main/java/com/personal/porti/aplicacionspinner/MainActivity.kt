package com.personal.porti.aplicacionspinner

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dMenu = findViewById<AutoCompleteTextView>(R.id.menuReal)
        ArrayAdapter.createFromResource(
                this,
                R.array.etapaEducativa,
                android.R.layout.list_content
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            dMenu.setAdapter(adapter)
        }
        /*val items = arrayOf("NM", "NY", "NC", "ND")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)*/
    }
}