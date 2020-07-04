package com.arkountos.orcsattack

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkountos.orcsattack.GlobalsActivity.Companion.SHARED_PREFS
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnInitiativeSetListener{
    private val TAG = "MAIN"

    private val characters: ArrayList<Character> = ArrayList()
    private val characters_sorted: ArrayList<Character> = ArrayList()

    private lateinit var encounter_name_edittext : EditText

    val gson = Gson()



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList();

        Log.d(TAG, "onCreate called from onCreate")
        val btn_add_char = findViewById<FloatingActionButton>(R.id.AddCharButton)
        btn_add_char.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            val intent :Intent = Intent(this, AddCharActivity::class.java)
            startActivityForResult(intent, 1)
        }

        val btn_settings = findViewById<FloatingActionButton>(R.id.SettingsButton)
        btn_settings.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            val popup = PopupMenu(this, btn_settings) //?
            popup.inflate(R.menu.settings_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu1 -> {
                        characters.clear()
                        initList()
                    }
                    R.id.menu2 -> {
                        sortByRolledInitiative()
                        initList()
                    }
                    R.id.menu3 -> {
                        saveEncounter(characters)
                    }
                    R.id.menu4 -> {
                        loadEncounter()
                    }
                }
                false
            }
            //displaying the popup
            //displaying the popup
            popup.show()
        }

        val btn_reroll = findViewById<Button>(R.id.reroll)
        btn_reroll.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            Log.d(TAG, "characters has " + characters.size + " and characters_sorted has " + characters_sorted.size)
            rollInitiatives()
            sortByRolledInitiative()
            initList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                var result_name = data?.getStringExtra("EXTRA_NAME")
                var result_initiative = data?.getIntExtra("EXTRA_INITIATIVE", 0)
                var result_class = data?.getStringExtra("EXTRA_CLASS")
                var result_amount = data?.getStringExtra("EXTRA_AMOUNT") // Is a string!
                var result_hitpoints = data?.getStringExtra("EXTRA_HITPOINTS")


                // With the returned data create a new Character and add him to the list!
                if (result_name == null || result_initiative == null || result_class == null || result_amount == null) {
                    throw Exception("Oh no! Intent returned a null value.")
                }
                if (result_hitpoints == null){
                    println("In resulthit")
                    result_hitpoints = "1";
                }
                println("result_hitpoints is $result_hitpoints")
                for (i in 0 until result_amount.toInt()) {
                    if (i >= 1){
                        var newName : String = "$result_name ${i+1}"
                        addToCharacters(newName, result_initiative, result_class, result_hitpoints.toInt())
                    }
                    else {
                        addToCharacters(result_name, result_initiative, result_class, result_hitpoints.toInt())
                    }
                }
                initList()
                Log.d(TAG, "on ActivityResult called")
                Log.d(TAG, "Initiative is $result_initiative")

            }
        }
        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                Log.d("MainResult", "Correct!")
                var result_character_info = data?.getStringExtra("EXTRA_ENCOUNTER_STRING")
                Log.d("Encounter Info: ", result_character_info)

                var result_character_info_split = result_character_info?.split( "\n")
                Log.d("split", result_character_info_split.toString())
                if (result_character_info_split != null) {
                    for (character in result_character_info_split){
                        var character_split = character.split(":", ",")
                        Log.d("char split", character_split.toString() + " size: " + character_split.size)
                        if (character_split.size != 1) {
                            var result_name = character_split[0]
                            var result_initiative = character_split[7].toInt()
                            var result_class = character_split[1]
                            var result_amount = 1
                            var result_hitpoints = character_split[3]
                            addToCharacters(result_name, result_initiative, result_class, result_hitpoints.toInt())
                            initList()
                        }
                    }
                }
            }
        }
    }

    private fun addToCharacters(result_name: String, result_initiative: Int, result_class: String, result_hitpoints: Int){
        var new_char: Character = Character(result_name, result_initiative, result_class, result_hitpoints)
        characters.add(new_char)
    }

    private fun initList(){
        initRecyclerView();
    }

    private fun rollInitiatives(){
        for (character in characters){
            character.rollInitiative()
        }
    }

    private fun sortByRolledInitiative(){
        Collections.sort(characters, CharComparator())
        for (i in characters){
            println("########################### In sortByRolledInititative and :${i.name}")

        }
    }

    private fun initRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
        val adapter = RecyclerViewAdapter(this, characters, this.supportFragmentManager)
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(this);
    }

    override fun onInitiativeSet() {
        Log.d("Main", "Called me!!!")
        sortByRolledInitiative()
    }

    //
    // Save and Load Encounter Functions
    //

    fun saveEncounter(characters: ArrayList<Character>){
        val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()

        var encounter_names: Set<String>
        if (sharedPreferences.getStringSet("saved_encounters_names", null) == null){
            encounter_names = mutableSetOf()
            editor.putStringSet("saved_encounters_names", encounter_names)
            editor.apply()
            encounter_names = sharedPreferences.getStringSet("saved_encounters_names", null)!!
        }
        else{
            encounter_names = sharedPreferences.getStringSet("saved_encounters_names", null)!!
        }

        var encounter: Set<String>
        encounter = mutableSetOf()
        for (character in characters){
            // Jsonify each character as a string
            val char = Character(character.name, character.initiative, character.myclass, character.hitpoints.toInt())
            val jsonchar = gson.toJson(char)

            // Add each character to a set of strings, that will be the encounter
            encounter.add(jsonchar)
        }

        var encounter_name = ""
//        var inflater: LayoutInflater? = null
        val alertDialog = Dialog(this)
        var inflater = layoutInflater
        val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.encounter_naming_dialog, null)
        alertDialog.setContentView(view)
        alertDialog.show()


        encounter_name_edittext = alertDialog.findViewById<EditText>(R.id.editTextTextPersonName)
        Log.d("encounter", encounter_name_edittext.toString())
        val okButton = view.findViewById<Button>(R.id.okButtonEncounter)
        okButton.setOnClickListener {
//            encounter_name = encounter_name_edittext.text.toString()
            Log.d("Btn", "Clicked, encounter_name is $encounter_name")
            alertDialog.dismiss()
            Toast.makeText(this, "Encounter Saved!", Toast.LENGTH_LONG).show()
        }
        val cancelButton =
            view.findViewById<Button>(R.id.cancelButtonEncounter)
        cancelButton.setOnClickListener {
            Log.d("Btn", "Clicked Cancel!")
            alertDialog.dismiss()
        }


        val ename = encounter_name
        editor.putStringSet(ename, encounter)

        // Add the ename to enames list on sharedPrefs
        encounter_names.add(ename)
        Log.d("Saved", "Encounter name")
        editor.putStringSet("saved_encounters_names", encounter_names)
        editor.apply()
        Log.d("DONE", "COOLCOOLCOOL")
        // End of Serialization
    }

    private fun loadEncounter(){
        var intent = Intent(this, LoadEncounterActivity::class.java)
        startActivityForResult(intent, 2)
    }

}
