package com.example.cursoKotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun sentenciaWhen(){

        val country = "Spain"

        when(country){

            "España" -> {
                println("El idioma es español")
            } "Mexico" -> {
            println("El idioma es español")
        } "Perú" -> {
            println("El idioma es Español")
        } "Estados Unidos" -> {
            println("El idioma es inglés")
        } else -> {
            println("No conocemos el idioma")
        }

        }

        val age = 100

        when(age){

            0, 1, 2 -> {
                println("Eres un bebé")
            }in 3..10 -> {
            println("Eres un niño")
            }in 11..17 -> {
            println("Eres un adolescente")
            }in 18..69 -> {
            println("Eres un adulto")
            }in 70..99 -> {
            println("Eres un anciano")
            }else -> {
            println("")
        }
        }

        fun arrays() {
            val name = "Sergio"
            val surname = "Portillo"
            val company = "Pegasus Fitness"
            val edad = "20"
            val myArray = arrayListOf<String>()

            myArray.add(name)
            myArray.add(surname)
            myArray.add(company)
            myArray.add(edad)

            myArray.addAll(listOf("Esto es", "para añadir todos los elementos que quieras"))

            //Eliminar datos

            myArray.removeAt(0)

            //Recorrer

            myArray.forEach() {

                println(it)

            }

            //Contar

            myArray.count()

            //Vaciar

            myArray.clear()

            //Acceder al primer y ultimo elemento

            myArray.first()

            myArray.last()

            //Ordenar array

            myArray.sort()

            //myArray.sortBy {  }

            //myArray.sortByDescending {  }

        }

        //Mapas/Diccionarios

        fun maps(){

            var myMap: Map<String, Int> = mapOf()

            //Añadir datos a un mapa

            myMap = mutableMapOf("Brais" to  1, "Sergio" to 2, "Sara" to 5)

            //Añadir datos uno a uno

            myMap["Ana"] = 7

            myMap.put("Maria", 8)

            myMap.put("Marcos",1)

            //Acceder a un dato

            println(myMap["Sergio"])

            //Borrar datos

            myMap.remove("Sergio")
        }

        





    }

}