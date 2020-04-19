package com.example.inittrack2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class EncounterGeneratorActivity : AppCompatActivity() {
    // Gson() object used for serialization and deserialization with JSON strings
    val gson = Gson()

    private val tiles: ArrayList<Tile> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_test)

        //Read height and width from spinners
        val height : String = intent.getStringExtra("EXTRA_HEIGHT")
        val width : String = intent.getStringExtra("EXTRA_WIDTH")


        // Read tree checkbox and tree probability setting
        val tree_probability: String
        if (intent.getStringExtra("EXTRA_TREES").toInt() == 1) {
            tree_probability = intent.getStringExtra("EXTRA_TREES_PROBABILITY").dropLast(1)
        }
        else{
            tree_probability = "0"
        }
        // Read campfire checkbox
        val campfire_input = intent.getStringExtra("EXTRA_CAMPFIRE").toInt()
        val stream_input = intent.getStringExtra("EXTRA_STREAM").toInt()


//        val test = Tile(Character("Hero", 3, "Paladin", 50), Ground("grass"))
//        println(test)
//        val jsontest = gson.toJson(test)
//        println(jsontest)
//
//        val resultjson = gson.fromJson(jsontest, Tile::class.java)
//        println(resultjson)

        generateMap(height.toInt(), width.toInt(), tree_probability = tree_probability.toInt(), campfire = campfire_input, stream = stream_input);

//        for (e in tiles){
//            if (e.x == 1 && e.y == 1){
//                e.character = Character("Test", 3, "Paladin", 3)
//            }
//        }



        val mRecyclerView : RecyclerView = findViewById(R.id.recyclerview_id)
//        mRecyclerView.setHasFixedSize(true)
        val mAdapter = GridAdapter(tiles, this)
        val mLayoutManager = GridLayoutManager(this, width.toInt())

        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    private fun generateMap(height: Int, width: Int, campfire: Int = 0,
                            tree_probability: Int = 0, hill_probability: Int = 0,
                            stream: Int = 0){
        for (i in 0 until (height * width)){
            var rndm = (0..100).random()
            if (rndm < tree_probability) {
                tiles.add(Tile(null, Ground("tree"), (i % width), (i / height)))
            }
            else{
                tiles.add(Tile(null, Ground("grass"), (i % width), (i / height)))
            }
        }
        if (campfire == 1){
            // Choose a random square in the "center" of the board to place campfire
            var rand_x = (((width - 1) / 4)..((width - 1) * 3 / 4)).random()
            var rand_y = (((height - 1) / 4)..((height - 1) * 3 / 4)).random()
            // Find tile with said coordinates
            Log.d("CAMP", "rand_x and rand_y: " + rand_x + rand_y + "the rest expression " + height/4 + height * 3 / 4 + 7/2)
            for (tile in tiles){
                if (tile.x == rand_x && tile.y == rand_y){
                    tile.content = Ground("campfire")
                }
            }
        }

        if (stream == 1){

        }
    }
}
