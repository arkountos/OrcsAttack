package com.arkountos.orcsattack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkountos.orcsattack.GlobalsActivity.Companion.SHARED_PREFS
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.net.Proxy
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnInitiativeSetListener{
    private val TAG = "MAIN"

    private val characters: ArrayList<Character> = ArrayList()
    private val characters_sorted: ArrayList<Character> = ArrayList()

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

        val ename = "Encounter_" + (1..10000).random().toString()
        editor.putStringSet(ename, encounter)

        // Add the ename to enames list on sharedPrefs
        encounter_names.add(ename)
        editor.putStringSet("saved_encounters_names", null)
        editor.apply()

        // End of Serialization
        Toast.makeText(this, "Encounter Saved!", Toast.LENGTH_LONG).show()
//        // Deserialization for testing
//
//        var encounter_character_set = sharedPreferences.getStringSet(ename, null)
//        var encounter_character_list : MutableList<Character> = mutableListOf()
//        if (encounter_character_set != null) {
//            for (json_string in encounter_character_set){
//                var de_jsoned_char = gson.fromJson(json_string, Character::class.java)
//                encounter_character_list.add(de_jsoned_char)
//            }
//        }
//        Log.d("List", encounter_character_list.toString())
    }

    fun loadEncounter(){
        var intent = Intent(this, LoadEncounterActivity::class.java)
        startActivityForResult(intent, 1)
    }

}
