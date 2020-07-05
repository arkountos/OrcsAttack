package com.arkountos.orcsattack

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

import com.arkountos.orcsattack.GlobalsActivity.Companion.SHARED_PREFS


class AddCharActivity : AppCompatActivity() {
    private val TAG = "AddCharActivity"

    val gson = Gson()

//    public val EXTRA_NAME = "com.example.inittrack2.EXTRA_NAME"
//    public val EXTRA_INITIATIVE = "com.example.inittrack.EXTRA_INITIATIVE"

    private lateinit var class_option : Spinner
    private lateinit var amount_option : Spinner
    private lateinit var char_option : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.arkountos.orcsattack.R.layout.activity_add_char)

        title = "Add new Character"

        var editTextName = findViewById<EditText>(com.arkountos.orcsattack.R.id.editTextName)
        var editTextInitiative = findViewById<EditText>(com.arkountos.orcsattack.R.id.editTextInitiative)
        var class_result: String = "Default"
        var amount_result: String = "1"
        var char_result: String = "Default"
        var editTextHitpoints = findViewById<EditText>(com.arkountos.orcsattack.R.id.editTextHitpoints)
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
//        var characterArray = arrayOf("Char1", "Char2", "Char3")
        val ΝΑΜΕ : String = "text"

        // For character array
        val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        var characterSet = if (sharedPreferences.getStringSet("saved_characters", null) != null){
            sharedPreferences.getStringSet("saved_characters", null)!!
        } else{
            mutableSetOf()
        }


        // characterList is a list tha has all the Character objects saved in shared prefs
        var characterList : MutableList<Character> = mutableListOf()
        for (character in characterSet){ // character is a jsonified string
            var de_jsonchar = gson.fromJson(character, Character::class.java)
            characterList.add(de_jsonchar)
        }

        // characterArray is an array with the names of all Character objects on characterList, it populates the spinner
        var characterArray = arrayOfNulls<String>(characterList.size)
        for ((i, character) in characterList.withIndex()){
            characterArray[i] = character.name
        }


        // Class choosing Spinner
        class_option = findViewById(com.arkountos.orcsattack.R.id.spinner)
        // Amount of characters choosing Spinner
        amount_option = findViewById(com.arkountos.orcsattack.R.id.spinner2)
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
                char_result = characterArray[position].toString()
            }

        }

        fun save(name: String, initiative: Int, hitpoints: String, myclass: String){
            val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()

            // Jsonify
            val char = Character(name, initiative, myclass, hitpoints.toInt())
            val jsonchar = gson.toJson(char)

            // Get string set with chars
            var heroes: Set<String>
            if (sharedPreferences.getStringSet("saved_characters", null) != null){
                heroes = sharedPreferences.getStringSet("saved_characters", null)!!
            }
            else{
                heroes = mutableSetOf()
                editor.putStringSet("saved_characters", heroes)
                editor.apply()
                heroes = sharedPreferences.getStringSet("saved_characters", null)!!
            }

            // Put new hero to heroes
            heroes.add(jsonchar)

            // Put new heroes to shared prefs
            editor.putStringSet("save_characters", heroes)
            editor.apply()
        }

        fun load(key: String, heroes: MutableList<Character>){
            for (character in heroes){
                if (character.name == key){
                    // Found the char we are looking
                    val resultIntent = Intent()
                    Log.d("Hero!:", character.name + character.initiative_modifier + character.myclass + character.hitpoints
                    )
                    resultIntent.putExtra("EXTRA_NAME", character.name)
                    resultIntent.putExtra("EXTRA_INITIATIVE", character.initiative_modifier)
                    resultIntent.putExtra("EXTRA_CLASS", character.myclass)
                    resultIntent.putExtra("EXTRA_HITPOINTS", character.hitpoints)
                    resultIntent.putExtra("EXTRA_AMOUNT", "1")

                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }
//            Toast.makeText(this, "There are no saved characters in memory! Try adding some first.", Toast.LENGTH_LONG).show()
        }

        println("##############")
        println(class_result)
        println(editTextName)

        val save_btn = findViewById<Button>(R.id.add_to_party)
        save_btn.setOnClickListener{
            val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            var editTextNameValue = editTextName.text.toString()
            var editTextInitiativeTemp = editTextInitiative.text.toString()
            var editTextInitiativeValue: Int? = null
            if (editTextInitiativeTemp != ""){
                editTextInitiativeValue = editTextInitiativeTemp.toInt()
            }
            var editTextHitpointsValue = editTextHitpoints.text.toString()
            var spinnerValue = class_result
            if (editTextNameValue == ""){
                Toast.makeText(this, "Please fill the name!", Toast.LENGTH_SHORT).show()
            }
            else if(editTextInitiativeValue == null){
                Toast.makeText(this, "Please fill the initiative modifier!", Toast.LENGTH_SHORT).show()
            }
            else{
                if(editTextHitpointsValue == "") {
                    editTextHitpointsValue = "1"
                }
                save(editTextNameValue, editTextInitiativeValue, editTextHitpointsValue, spinnerValue)
                Toast.makeText(this, "Saved! Next time you open this screen the character will appear in the load list", Toast.LENGTH_LONG).show()
            }
        }

        val load_btn = findViewById<Button>(R.id.load_char_button)
        load_btn.setOnClickListener{
            val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            var selectedCharacterName = char_result
            load(selectedCharacterName, characterList)
        }



        val btn_done = findViewById<FloatingActionButton>(com.arkountos.orcsattack.R.id.doneButton)
        btn_done.setOnClickListener {
            val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
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


