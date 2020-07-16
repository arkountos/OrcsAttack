package com.arkountos.orcsattack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class LoadNPCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_n_p_c)

        // Get Encounters list from sharedPrefs
        val sharedPreferences : SharedPreferences = getSharedPreferences(GlobalsActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
//        Log.d("Test", "OK")
        var NPCs: Set<String>
        if (sharedPreferences.getStringSet("saved_encounters_names", null) == null){
            NPCs = mutableSetOf()
            editor.putStringSet("saved_encounters_names", NPCs)
            editor.apply()
            NPCs = sharedPreferences.getStringSet("saved_encounters_names", null)!!
        }
        else{
            NPCs = sharedPreferences.getStringSet("saved_encounters_names", null)!!
        }
        Log.d("Encounter_names size", NPCs.size.toString())

        for (name in NPCs){
            Log.d("Name", name)
        }

    }
}