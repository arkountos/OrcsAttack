package com.example.inittrack2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN"

    private val characters: ArrayList<Character> = ArrayList()
    private val characters_sorted: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList();

        Log.d(TAG, "onCreate called from onCreate")
        val btn_add_char = findViewById<FloatingActionButton>(R.id.AddCharButton)
        btn_add_char.setOnClickListener {
            val intent :Intent = Intent(this, AddCharActivity::class.java)
            startActivityForResult(intent, 1)
        }

        val btn_reroll = findViewById<Button>(R.id.reroll)
        btn_reroll.setOnClickListener {
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

                // With the returned data create a new Character and add him to the list!
                if (result_name == null || result_initiative == null) {
                    throw Exception("Oh no! Intent returned a null value.")
                }
                addToCharacters(result_name, result_initiative)
                initList()
                Log.d(TAG, "on ActivityResult called")
                Log.d(TAG, "Initiative is $result_initiative")

            }
        }
    }

    private fun addToCharacters(result_name: String, result_initiative: Int){
        var new_char: Character = Character(result_name, result_initiative)
        characters.add(new_char)
    }

    private fun initList(){
        // Clear list and add all chars in it
        characters_sorted.clear()
        for (character in characters){
            characters_sorted.add(character)
        }
        initRecyclerView();
    }

    private fun rollInitiatives(){
        for (character in characters){
            character.rollInitiative()
        }
    }

    private fun sortByRolledInitiative(){
//        characters_sorted.clear()
//        for (character in characters){
//            characters_sorted.add(character)
//        }
        Collections.sort(characters, CharComparator())
        for (i in characters){
            println("########################### In sortByRolledInititative and :${i.name}")

        }
    }

    private fun initRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
        val adapter = RecyclerViewAdapter(this, characters_sorted)
        recyclerView.setAdapter(adapter);
        recyclerView.layoutManager = LinearLayoutManager(this);
    }
}
