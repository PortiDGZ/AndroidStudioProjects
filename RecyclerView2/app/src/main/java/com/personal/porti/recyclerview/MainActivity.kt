package com.personal.porti.recyclerview

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        data class Item(val id: Long, val title: String, val url: String){


        }

        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = MyAdapter()


    }

    class MyAdapter (private val items: List, private val listener: (ClipData.Item) -> Unit) {

        fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RecyclerView.ViewHolder(parent.inflate(R.layout.view_item))
        fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.bind(items[position], listener)
        fun getItemCount() = items.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ClipData.Item, listener: (ClipData.Item) -> Unit) = with(itemView) {

        }
    }
}