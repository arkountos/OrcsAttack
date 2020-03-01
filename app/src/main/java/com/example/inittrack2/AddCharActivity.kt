package com.example.inittrack2

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddCharActivity : AppCompatActivity() {
    private val TAG = "AddCharActivity"
//    public val EXTRA_NAME = "com.example.inittrack2.EXTRA_NAME"
//    public val EXTRA_INITIATIVE = "com.example.inittrack.EXTRA_INITIATIVE"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.example.inittrack2.R.layout.activity_add_char)

        setTitle("Add new Character")

        var editTextName = findViewById<EditText>(com.example.inittrack2.R.id.editTextName)
        var editTextInitiative = findViewById<EditText>(com.example.inittrack2.R.id.editTextInitiative)

        println(editTextName)

        val btn_done = findViewById<FloatingActionButton>(com.example.inittrack2.R.id.doneButton)
        btn_done.setOnClickListener {

            var editTextNameValue = editTextName.text.toString()
            var editTextInitiativeValue = Integer.valueOf(editTextInitiative.text.toString())

            println("HELLO!!!!!!")
            println("Name is")
            println(editTextNameValue)
            println("And inititative is:")
            println(editTextInitiativeValue)

            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_NAME", editTextNameValue)
            resultIntent.putExtra("EXTRA_INITIATIVE", editTextInitiativeValue)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

}
