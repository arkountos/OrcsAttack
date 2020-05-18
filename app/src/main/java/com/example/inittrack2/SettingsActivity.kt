package com.example.inittrack2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.inittrack2.GlobalsActivity.Companion.SHARED_PREFS

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val clear_saved_heroes_btn = findViewById<Button>(R.id.settings_clear_heroes_button)
        clear_saved_heroes_btn.setOnClickListener{
            val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            var heroes: Set<String>
            if (sharedPreferences.getStringSet("saved_characters", null) != null){
                heroes = sharedPreferences.getStringSet("saved_characters", null)!!
                heroes = emptySet()
                editor.putStringSet("saved_characters", heroes)
                editor.apply()
                Toast.makeText(this, "All heroes have been removed from memory.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "There are no characters to be erased.", Toast.LENGTH_LONG).show()
            }
        }

        val toggle_tile_background_colour_btn = findViewById<Button>(R.id.settings_background_color)
        toggle_tile_background_colour_btn.setOnClickListener{
            if (GlobalsActivity.global_toggle_background_colours_friend_enemy == "On"){
                GlobalsActivity.global_toggle_background_colours_friend_enemy = "Off"
                Toast.makeText(this, "Tile background colour has been set Off", Toast.LENGTH_LONG).show()

            }
            else{
                GlobalsActivity.global_toggle_background_colours_friend_enemy = "On"
                Toast.makeText(this, "Tile background colour has been set On", Toast.LENGTH_LONG).show()

            }
        }
    }
}
