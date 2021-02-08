package com.personal.porti.aplicacionspinner

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val RESULT_LOAD_IMAGE = 1
    lateinit var campoNombre: TextInputEditText
    lateinit var campoApellidos: TextInputEditText
    lateinit var campoEdad: TextInputEditText
    lateinit var campoEmail: TextInputEditText
    lateinit var parentMenu: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCarga = findViewById<Button>(R.id.CARGAR);
        campoNombre = findViewById(R.id.campoNombre)
        campoApellidos = findViewById(R.id.campoApellidos)
        campoEdad = findViewById(R.id.campoEdad)
        campoEmail = findViewById(R.id.campoEmail)
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        val dMenu = findViewById<AutoCompleteTextView>(R.id.menuReal)
        parentMenu = findViewById(R.id.menuDrop)
        val type = arrayOf("Primaria", "Secundaria", "Bachillerato", "Formación Profesional")
        val mPickDateButton = findViewById<Button>(R.id.pick_date_button)
        val materialDateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText("SELECCIONA UNA FECHA")
        materialDateBuilder.build()
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar_layout)
        mPickDateButton.setOnClickListener {

            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]
            val dateDialog = DatePickerDialog(this, datePickerListener, mYear, mMonth, mDay)
            dateDialog.datePicker.maxDate = Date().time
            dateDialog.show()
        }
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            type,
        )
        dMenu.setAdapter(adapter)

        campoEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                validarCampos()
                if (campoEmail.text.toString().isEmpty()) {
                    campoEmail.error = "Introduce un email"
                } else {
                    if (!campoEmail.text.toString().trim().matches(emailPattern)) {
                        campoEmail.error = "El email introducido es inválido"
                    } else {
                        if (campoEmail.text.toString().trim().matches(emailPattern)) {
                            Toast.makeText(
                                applicationContext,
                                "La dirección de correo es correcta",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        campoNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                validarCampos()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        campoApellidos.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                validarCampos()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        campoEdad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                validarCampos()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        dMenu?.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@MainActivity, vistaFormulario::class.java).apply {
                putExtra("Nombre", campoNombre.text.toString())
                putExtra("Apellidos", campoApellidos.text.toString())
                putExtra("Edad", campoEdad.text.toString())
                putExtra("EtapaEd", type[position]).toString()
                putExtra("Email", campoEmail.text.toString())
            }
            startActivity(intent)
        }
        botonCarga.setOnClickListener {
            val i = Intent(
                Intent.ACTION_PICK,
            )
            i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }
    }

    fun validarCampos() {
        if (campoNombre.text.toString() != "" && campoApellidos.text.toString() != "" && campoEdad.text.toString() != "" && campoEmail.text.toString() != "" && campoEmail.error == null) {

            parentMenu.isEnabled = true
        }
    }
    private val datePickerListener = OnDateSetListener { _, year, month, day ->
        val c: Calendar = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        SimpleDateFormat("dd MMM YYYY").format(c.time)
        campoEdad.setText(calcularEdad(c.timeInMillis).toString())
    }

    private fun calcularEdad(date: Long): Int {
        val dob = Calendar.getInstance() //dob = fecha de nacimiento
        dob.timeInMillis = date
        val hoy = Calendar.getInstance()
        var edad = hoy[Calendar.YEAR] - dob[Calendar.YEAR]
        if (hoy[Calendar.DAY_OF_MONTH] < dob[Calendar.DAY_OF_MONTH] && hoy[Calendar.MONTH] < dob[Calendar.MONTH]) {
            edad--
        }
        return edad
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            val selectedImage: Uri? = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = selectedImage?.let {
                contentResolver.query(
                    it,
                    filePathColumn, null, null, null
                )
            }
            cursor?.moveToFirst()
            val columnIndex: Int? = cursor?.getColumnIndex(filePathColumn[0])
            val picturePath: String = (columnIndex?.let { cursor.getString(it) } ?: cursor?.close()) as String
            val imageView = findViewById<View>(R.id.imgView) as ImageView
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }
}