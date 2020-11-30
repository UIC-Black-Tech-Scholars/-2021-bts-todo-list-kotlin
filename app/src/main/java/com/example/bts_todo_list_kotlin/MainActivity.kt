package com.example.bts_todo_list_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var listView: ListView
    private lateinit var dataList: ArrayList<ListItem>
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var customArrayAdapter:CustomListItemAdapter
    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resources.openRawResource(R.raw.test).bufferedReader().use {
            val stype = object : TypeToken<ArrayList<ListItem>>() {}.type
            dataList = gson.fromJson(it.readText(), stype)
        }
        //find view objects
        listView = findViewById(R.id.list_view)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        //create list data
        dataList.forEach { item ->
            Log.d(TAG, item.toString())
        }
        //hook list data up to our list using array adapter
        customArrayAdapter = CustomListItemAdapter(this, dataList)
        listView.adapter = customArrayAdapter

        //set up adding and removing data.
        floatingActionButton.setOnClickListener { view -> addNewNote(view) }
        listView.isLongClickable = true
        listView.setOnItemLongClickListener { parent, view, position, id ->
            removeItemOnLongClick(parent, view, position, id)
        }
    }


    private fun removeItemOnLongClick(
        parent: ViewParent,
        view: View,
        position: Int,
        id: Long
    ): Boolean {
        Log.i(TAG, "GETTING ITEM NUMBER - $position")
        customArrayAdapter.remove(customArrayAdapter.getItem(position))
        customArrayAdapter.notifyDataSetChanged()
        return true
    }


    fun addNewNote(view: View) {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Create New Item")
        val customLayout: View = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
        builder.setView(customLayout)
        builder.setPositiveButton("OK") { _, _ ->
            // AlertDialog to the Activity
            val number = customLayout.findViewById<EditText>(R.id.number).text.toString()
                val title = customLayout.findViewById<EditText>(R.id.title).text.toString()
                val description = customLayout.findViewById<EditText>(R.id.description).text.toString()
                dataList.add(ListItem(title, description, number.toInt()))
                customArrayAdapter.notifyDataSetChanged()
            }
        val dialog: android.app.AlertDialog? = builder.create()
        dialog?.show()
    }

}