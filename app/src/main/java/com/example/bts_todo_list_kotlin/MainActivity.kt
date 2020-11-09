package com.example.bts_todo_list_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var listView: ListView
    private lateinit var dataList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find list view
        listView = findViewById(R.id.list_view)
        //create list data
        dataList = listOf("This", "Is", "A", "List", "Of", "Strings", "This", "Is", "A", "List", "Of", "Strings", "This", "Is", "A", "List", "Of", "Strings", "This", "Is", "A", "List", "Of", "Strings", "This", "Is", "A", "List", "Of", "Strings")
        dataList.forEach{
                item -> Log.d(TAG, item)
        }
        //hook list data up to our list using array adapter
        var listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList.toTypedArray())
        listView.adapter = listAdapter
    }
}