package com.arkountos.orcsattack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadEncounterActivity : AppCompatActivity() {
    private var encounters: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.arkountos.orcsattack.R.layout.activity_load_encounter)

    }

    private fun initRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
        val adapter = RecyclerViewAdapter(this, encounters, this.supportFragmentManager)
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(this);
    }

}
