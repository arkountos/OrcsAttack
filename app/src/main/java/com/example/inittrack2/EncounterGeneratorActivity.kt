package com.example.inittrack2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EncounterGeneratorActivity : AppCompatActivity() {

    private val tiles: ArrayList<Tile> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_test)
        tiles.add(Tile(Character("Fivos", 2, "Cleric", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Rogue", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Cleric", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Monster", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Paladin", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Monster", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Cleric", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Wizard", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Druid", 5)));

        tiles.add(Tile(Character("Fivos", 2, "Paladin", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Paladin", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Monster", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Cleric", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Sorcerer", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Paladin", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Monster", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Ranger", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Wizard", 5)));
        tiles.add(Tile(Character("Fivos", 2, "Paladin", 5)));


        val mRecyclerView : RecyclerView = findViewById(R.id.recyclerview_id)
//        mRecyclerView.setHasFixedSize(true)
        val mAdapter = GridAdapter(tiles, this)
        val mLayoutManager = GridLayoutManager(this, 5)

        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
    }
}
