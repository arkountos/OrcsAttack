package com.arkountos.orcsattack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class LoadEncounterActivity : AppCompatActivity() {
    private var encounters: ArrayList<ArrayList<Character>> = ArrayList()

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.arkountos.orcsattack.R.layout.activity_load_encounter)


        // Get Encounters list from sharedPrefs
        val sharedPreferences : SharedPreferences = getSharedPreferences(GlobalsActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        Log.d("Test", "OK")
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
        Log.d("Encounter_names size", encounter_names.size.toString())

        for (name in encounter_names){
            Log.d("Name", name)
        }

        // A (all encounters) set of Pairs of String (encounter name) and Set of Strings (encounter members).
        // I know I will not be able to read this in a few days. Sorry future fiv :(
        var encounters: Map<String, Set<String>>
        encounters= mutableMapOf()
        Log.d("encounters", encounters.toString() + encounters?.javaClass?.canonicalName)

        for (name in encounter_names){
            // For every encounter
            Log.d("NAME:", name)
            var temp_encounter = sharedPreferences.getStringSet(name, null)
            Log.d("temp_encounter", temp_encounter.toString() + temp_encounter?.javaClass?.canonicalName)
            if (temp_encounter != null) {
                for (character in temp_encounter){
                    Log.d("Char Name:", gson.fromJson(character, Character::class.java).name)
                }
            }
            if (temp_encounter != null) {
                encounters[name] = temp_encounter
                Log.d("Print the mapping", "from activity: $encounters but name is: $name")
            }
        }

        initRecyclerView(encounters)
    }

    private fun initRecyclerView(encounters: Map<String, Set<String>>){
        val recyclerView: RecyclerView = findViewById(R.id.load_encounter_recyclerView)
        val adapter = LoadEncounterAdapter(this, encounters, this.supportFragmentManager)
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(this);
    }

}
