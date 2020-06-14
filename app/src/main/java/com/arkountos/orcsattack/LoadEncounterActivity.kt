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

        for (name in encounter_names){
            // For every encounter
        }
    }

    private fun initRecyclerView(){
//        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
//        val adapter = RecyclerViewAdapter(this, encounters, this.supportFragmentManager)
//        recyclerView.adapter = adapter;
//        recyclerView.layoutManager = LinearLayoutManager(this);
    }

}
