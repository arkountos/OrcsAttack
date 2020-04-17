//package com.example.inittrack2
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class GridTestActivity : AppCompatActivity() {
//
//
//    private val tiles: ArrayList<Tile> = ArrayList()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_grid_test)
//        tiles.add(Tile(Character("Fivos", 2, "Cleric", 5)));
//
//        val mRecyclerView : RecyclerView = findViewById(R.id.recyclerview_id)
////        mRecyclerView.setHasFixedSize(true)
//        val mAdapter = GridAdapter(tiles)
//        val mLayoutManager = LinearLayoutManager(this)
//
//        mRecyclerView.layoutManager = mLayoutManager
//        mRecyclerView.adapter = mAdapter
//    }
//}
