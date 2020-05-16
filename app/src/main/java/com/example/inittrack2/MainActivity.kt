package com.example.inittrack2

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*;
import kotlinx.android.synthetic.main.item_on_recyclerlist.*;
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

// TODO: Save characters for quick selection when adding
// TODO: Button to clear current Battle on top right

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN"

    private val characters: ArrayList<Character> = ArrayList()
    private val characters_sorted: ArrayList<Character> = ArrayList()

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
}
