package com.example.inittrack2

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson


class AddCharActivity : AppCompatActivity() {
    private val TAG = "AddCharActivity"
    val SHARED_PREFS : String = "sharedPrefs"

    val gson = Gson()

//    public val EXTRA_NAME = "com.example.inittrack2.EXTRA_NAME"
//    public val EXTRA_INITIATIVE = "com.example.inittrack.EXTRA_INITIATIVE"

    private lateinit var class_option : Spinner
    private lateinit var amount_option : Spinner
    private lateinit var char_option : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.example.inittrack2.R.layout.activity_add_char)

        title = "Add new Character"

        var editTextName = findViewById<EditText>(com.example.inittrack2.R.id.editTextName)
        var editTextInitiative = findViewById<EditText>(com.example.inittrack2.R.id.editTextInitiative)
        var class_result: String = "Default"
        var amount_result: String = "1"
        var char_result: String = "Default"
        var editTextHitpoints = findViewById<EditText>(com.example.inittrack2.R.id.editTextHitpoints)
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
        var characterArray = arrayOf("Char1", "Char2", "Char3")
        val ΝΑΜΕ : String = "text"


        // Class choosing Spinner
        class_option = findViewById(com.example.inittrack2.R.id.spinner)
        // Amount of characters choosing Spinner
        amount_option = findViewById(com.example.inittrack2.R.id.spinner2)
        // Load char Spinner
        char_option = findViewById(R.id.load_char_spinner)


        class_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, classes)
        amount_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, quantities)
        char_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, characterArray)

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

        char_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                char_result = "Default"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                char_result = characterArray[position]
            }

        }

        fun save(name: String, initiative: Int, hitpoints: String, myclass: String){
            var sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            var editor : SharedPreferences.Editor = sharedPreferences.edit()

            // Jsonify
            var char = Character(name, initiative, myclass, hitpoints.toInt())
            var jsonchar = gson.toJson(char)

            // Put to sharedpreferences
            editor.putString(name, jsonchar)

            editor.apply()
        }

        fun load(key: String){

        }



        println("##############")
        println(class_result)
        println(editTextName)

        val save_btn = findViewById<Button>(R.id.add_to_party)
        save_btn.setOnClickListener{
            var editTextNameValue = editTextName.text.toString()
            var editTextInitiativeValue = Integer.valueOf(editTextInitiative.text.toString())
            var editTextHitpointsValue = editTextHitpoints.text.toString()
            var spinnerValue = class_result

            save(editTextNameValue, editTextInitiativeValue, editTextHitpointsValue, spinnerValue)
        }

        val load_btn = findViewById<Button>(R.id.load_char_button)


        val btn_done = findViewById<FloatingActionButton>(com.example.inittrack2.R.id.doneButton)
        btn_done.setOnClickListener {
            var editTextNameValue = editTextName.text.toString()
            var editTextInitiativeValue = editTextInitiative.text.toString()
            var editTextHitpointsValue = editTextHitpoints.text.toString()

            // Input sanitation
            if (editTextInitiativeValue == ""){
                editTextInitiativeValue = "0"
            }
            if (editTextHitpointsValue == ""){
                editTextHitpointsValue = "1"
            }

            if (editTextNameValue == ""){
                println("In here")
                Toast.makeText(this, "Please fill out the Name!",
                    Toast.LENGTH_SHORT).show();

            }

            else {
                println("HELLO!!!!!!")
                println("Name is")
                println(editTextNameValue)
                println("And inititative is:")
                println(editTextInitiativeValue)
                println("And result is:")
                println(class_result)
                println(editTextHitpointsValue)

                val resultIntent = Intent()
                resultIntent.putExtra("EXTRA_NAME", editTextNameValue)
                resultIntent.putExtra("EXTRA_INITIATIVE", editTextInitiativeValue.toInt())
                resultIntent.putExtra("EXTRA_CLASS", class_result)
                resultIntent.putExtra("EXTRA_HITPOINTS", editTextHitpointsValue)
                resultIntent.putExtra("EXTRA_AMOUNT", amount_result)

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}


