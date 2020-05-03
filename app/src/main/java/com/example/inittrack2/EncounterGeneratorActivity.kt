package com.example.inittrack2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.lang.Math.pow
import java.lang.Math.sqrt
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

// TODO: Add Characters to the map

class EncounterGeneratorActivity : AppCompatActivity() {
    // Gson() object used for serialization and deserialization with JSON strings
    val gson = Gson()

    private val STEP_COST = 1.0
    private val DIAGONAL_STEP_COST = 1.5

    private var tiles_map = HashMap<Pair<Int, Int>, Tile>()
    private val tiles: ArrayList<Tile> = ArrayList()
    private var stream_queue: Queue<Tile> = LinkedList<Tile>()

    // Returns the 4 blocks around the input tile.
    // Return may contain nulls.
    fun return_near_tiles(tile: Tile): List<Tile?>{
        var up_tile: Tile? = tiles_map[Pair(tile.x, tile.y+1)]
        var down_tile: Tile? = tiles_map[Pair(tile.x, tile.y-1)]
        var left_tile: Tile? = tiles_map[Pair(tile.x-1, tile.y)]
        var right_tile: Tile? = tiles_map[Pair(tile.x+1, tile.y)]
        return(listOf<Tile?>(up_tile, down_tile, left_tile, right_tile))
    }

    fun distance(start: Tile, finish: Tile): Double{
        return (sqrt( pow(start.x.toDouble() - finish.x.toDouble(), 2.0) + pow(start.y.toDouble() - finish.y.toDouble(), 2.0)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

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
        val rocks_input = intent.getStringExtra("EXTRA_ROCKS").toInt()
        val rock_probability: String
        if (intent.getStringExtra("EXTRA_ROCKS").toInt() == 1) {
            rock_probability = intent.getStringExtra("EXTRA_ROCKS_PROBABILITY").dropLast(1)
        }
        else{
            rock_probability = "0"
        }

//        val test = Tile(Character("Hero", 3, "Paladin", 50), Ground("grass"))
//        println(test)
//        val jsontest = gson.toJson(test)
//        println(jsontest)
//
//        val resultjson = gson.fromJson(jsontest, Tile::class.java)
//        println(resultjson)

        generateMap(height.toInt(), width.toInt(), tree_probability = tree_probability.toInt(),
            campfire = campfire_input, stream = stream_input, rock_probability = rock_probability.toInt());

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
                            stream: Int = 0, rock_probability: Int = 0){
        for (i in 0 until (height * width)){
            var rndm_tree = (0..100).random()
            var rndm_rock = (0..100).random()
            if (rndm_tree < tree_probability) {
                val mytile = Tile(null, Ground("tree"), (i % width), (i / height))
                tiles.add(mytile)
                tiles_map[Pair(i % width, i / height)] = mytile
            }
            else if (rndm_rock < rock_probability){
                val mytile = Tile(null, Ground("rock"), (i % width), (i / height))
                tiles.add(mytile)
                tiles_map[Pair(i % width, i / height)] = mytile
            }
            else {
                val mytile = Tile(null, Ground("grass"), (i % width), (i / height))
                tiles.add(mytile)
                tiles_map[Pair(i % width, i / height)] = mytile
            }
            // Stream
//            val list =

        }
        if (campfire == 1){
            // Choose a random square in the "center" of the board to place campfire
            var rand_x = (((width - 1) / 4)..((width - 1) * 3 / 4)).random()
            var rand_y = (((height - 1) / 4)..((height - 1) * 3 / 4)).random()
            // Find tile with said coordinates
            Log.d("CAMP", "rand_x and rand_y: " + rand_x + rand_y + "the rest expression " + height/4 + height * 3 / 4 + 7/2)
//            for (tile in tiles){
//                if (tile.x == rand_x && tile.y == rand_y){
//                    tile.content = Ground("campfire")
//                }
//            }
            tiles_map[Pair(rand_x, rand_y)]!!.content = Ground("campfire")
        }

        if (stream == 1){
            Log.d("STREAM", "1")
            // From top to bottom river
            var start_tile: Tile = tiles_map[Pair(0,(0..width).random())]!!
            var end_tile: Tile = tiles_map[Pair(height - 1,(0..width).random())]!!

            Log.d("Distance(1,1) and (2,3)", distance(tiles_map[Pair(1,1)]!!, tiles_map[Pair(2,3)]!!).toString())

            var open_set: MutableList<Tile> = mutableListOf(start_tile)

            // A star algorithm
            while (open_set.isNotEmpty()){
                var tile : Tile = open_set.first()
                tile.parent = start_tile
                tile.hCost = distance(tile, end_tile)
                tile.gCost = tile.parent!!.gCost + STEP_COST + tile.randomNoise
                for (newtile in return_near_tiles(tile)){

                }

                open_set.add(tile)
                Collections.sort(open_set, TileComparator())

            }
            for (tile in return_near_tiles(start_tile)){
                if (tile != null) {
                    tile.parent = start_tile
                    tile.hCost = distance(tile, end_tile)
                    tile.gCost = tile.parent!!.gCost + STEP_COST + tile.randomNoise
                }
                else{
                    Log.d("Null!", "A null tile was returned in A* loop")
                }
            }

//            var fountain_tile: Tile = tiles_map[Pair((0..height).random(),(0..width).random())] ?: throw Exception("Fountain tile is null")
//            for (tile in return_near_tiles(fountain_tile)){
//                stream_queue.add(tile)
//            }
//            Log.d("STREAM", "2")
//            var to_be_river: Tile?
//            var tile: Tile
//            while (stream_queue.peek() != null){
//                // One of my neighbors becomes water block
//                tile = stream_queue.poll()
//                to_be_river = return_near_tiles(tile).shuffled().take(1)[0]
//                if (to_be_river == null || to_be_river.content == Ground("campfire")){
//                    continue
//                }
//                if ()
//                to_be_river.content = Ground("river")
//                stream_queue.add(to_be_river)
//            }

//            for (tile in stream_queue){
//                // One of my neighbors becomes water block
//                to_be_river = return_near_tiles(tile).shuffled().take(1)[0]
//                to_be_river!!.content = Ground("river")
//                stream_queue.add(to_be_river)
//            }
        }
    }
}
