package com.personal.porti.aplicacionspinner

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContentProviderCompat.requireContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = arrayOf("Primaria", "Secundaria", "Bachillerato", "Ciclos Formativos")
        val adapter = ArrayAdapter(requireContext(Context!!), R.layout.list_item, items)
        (R.id.menuDrop as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}