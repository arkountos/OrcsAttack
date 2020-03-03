package com.example.inittrack2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddCharActivity : AppCompatActivity() {
    private val TAG = "AddCharActivity"
//    public val EXTRA_NAME = "com.example.inittrack2.EXTRA_NAME"
//    public val EXTRA_INITIATIVE = "com.example.inittrack.EXTRA_INITIATIVE"




    lateinit var class_option : Spinner
    lateinit var amount_option : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.example.inittrack2.R.layout.activity_add_char)

        title = "Add new Character"

        var editTextName = findViewById<EditText>(com.example.inittrack2.R.id.editTextName)
        var editTextInitiative = findViewById<EditText>(com.example.inittrack2.R.id.editTextInitiative)
        var class_result: String = "Default"
        var amount_result: String = "1"
        val classes = arrayOf(
            "Monster",
            "Barbarian",
            "Bard",
            "Cleric",
            "Druid",
            "Fighter",
            "Monk",
            "Paladin",
            "Ranger",
            "Rogue",
            "Sorcerer",
            "Warlock",
            "Wizard"
        )
        val quantities = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")



        // Class choosing Spinner
        class_option = findViewById(com.example.inittrack2.R.id.spinner)
        // Amount of characters choosing Spinner
        amount_option = findViewById(com.example.inittrack2.R.id.spinner2)


        class_option.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, classes)
        amount_option.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, quantities)

        class_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                class_result = "Default"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                class_result = classes[position]

            }
        }
        amount_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                amount_result = "1"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                amount_result = quantities[position]
            }

        }





        println("##############")
        println(class_result)
        println(editTextName)

        val btn_done = findViewById<FloatingActionButton>(com.example.inittrack2.R.id.doneButton)
        btn_done.setOnClickListener {

            var editTextNameValue = editTextName.text.toString()
            var editTextInitiativeValue = Integer.valueOf(editTextInitiative.text.toString())
            var spinnerValue = class_result

            println("HELLO!!!!!!")
            println("Name is")
            println(editTextNameValue)
            println("And inititative is:")
            println(editTextInitiativeValue)
            println("And result is:")
            println(class_result)

            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_NAME", editTextNameValue)
            resultIntent.putExtra("EXTRA_INITIATIVE", editTextInitiativeValue)
            resultIntent.putExtra("EXTRA_CLASS", class_result)
            resultIntent.putExtra("EXTRA_AMOUNT", amount_result)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

}


