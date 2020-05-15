package com.example.inittrack2

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inittrack2.ui.ExportBitmapActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_grid.*
import kotlinx.android.synthetic.main.activity_grid.view.*
import java.io.FileOutputStream
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
    private val NOTICE_RADIUS = 3
    private val CAMPFIRE_DISTANCE = 2

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


        val encounterName = intent.getStringExtra("EXTRA_ENCOUNTER_NAME")
        val name_view = findViewById<TextView>(R.id.map_name)
        name_view.text = encounterName

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

        val enemies_quantity = intent.getStringExtra("EXTRA_ENEMIES_QUANTITY").toInt()
        var heroes_quantity = intent.getStringExtra("EXTRA_HEROES_SIZE").toInt()
        Log.d("Heroes quantity", "" + heroes_quantity)

        var heroes : MutableList<Pair<String, String>> = mutableListOf()
        var i: Int = 0
        while (i < heroes_quantity){
            var hero_name = intent.getStringExtra("EXTRA_HERO_" + i.toString() + "_NAME").toString()
            var hero_class = intent.getStringExtra("EXTRA_HERO_" + i.toString() + "_CLASS").toString()
            Log.d("heroes_quantity, i", "" + heroes_quantity + i.toString())
            Log.d("Data to hero Pair", "" + hero_name + hero_class)
            var hero : Pair<String, String> = Pair(hero_name, hero_class)
            heroes.add(hero)
            Log.d("Heroes after addition", "" + heroes)
            i++
        }
        Log.d("Arguments", "Heroes passed to generateMap: $heroes")

        generateMap(height.toInt(), width.toInt(), tree_probability = tree_probability.toInt(),
            campfire = campfire_input, stream = stream_input, rock_probability = rock_probability.toInt(),
            enemies_quantity = enemies_quantity, heroes_list = heroes);

        val mRecyclerView : RecyclerView = findViewById(R.id.recyclerview_id)
//        mRecyclerView.setHasFixedSize(true)
        val mAdapter = GridAdapter(tiles, this)
        val mLayoutManager = GridLayoutManager(this, width.toInt())

        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter

        var export_button : Button = findViewById(R.id.export_button)
        export_button.setOnClickListener{
            var map_bitmap : Bitmap? = getBitmapFromView(recyclerview_id,
                recyclerview_id.getChildAt(0).width,
                recyclerview_id.getChildAt(0).height
            )  // Pass a view as an argument
            var filename : String = "temp_bitmap.png"
            var stream : FileOutputStream = this.openFileOutput(filename, Context.MODE_PRIVATE)
            map_bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)

            // Cleanup
            stream.close();
            map_bitmap?.recycle()

            // Intent
            var export_intent: Intent = Intent(this, ExportBitmapActivity::class.java)
            export_intent.putExtra("EXTRA_IMAGE_PATH", filename)
            startActivity(export_intent)
        }
    }

    private fun generateMap(height: Int, width: Int, campfire: Int = 0,
                            tree_probability: Int = 0, hill_probability: Int = 0,
                            stream: Int = 0, rock_probability: Int = 0,
                            enemies_quantity: Int = 0,
                            heroes_list: MutableList<Pair<String, String>> = mutableListOf()){
        var campfire_tile : Tile? = null
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
            var rand_x : Int? = null
            var rand_y : Int? = null
            while (tiles_map[Pair(rand_x, rand_y)] == null) {
                rand_x = (((width - 1) / 4)..((width - 1) * 3 / 4)).random()
                rand_y = (((height - 1) / 4)..((height - 1) * 3 / 4)).random()
            }
            // Find tile with said coordinates
            Log.d("CAMP", "rand_x and rand_y: " + rand_x + rand_y + "the rest expression " + height/4 + height * 3 / 4 + 7/2)
//            for (tile in tiles){
//                if (tile.x == rand_x && tile.y == rand_y){
//                    tile.content = Ground("campfire")
//                }
//            }
            tiles_map[Pair(rand_x, rand_y)]!!.content = Ground("campfire")
            campfire_tile = tiles_map[Pair(rand_x, rand_y)]!!
        }

        if (stream == 1){
            Log.d("STREAM", "1")
            // From top to bottom river
            Log.d("HEIGHT", "h:$height w:$width")
            var start_tile: Tile = tiles_map[Pair(0,(0 until width).random())]!!
            var myheight = height-1
            var mywidth = (0 until width).random()
            var end_tile: Tile = tiles_map[Pair(myheight,mywidth)]!!

            // A star algorithm
            start_tile.parent = null
            start_tile.hCost = distance(start_tile, end_tile)
            start_tile.gCost = 0.0
            var open_set: MutableList<Tile> = mutableListOf(start_tile)
            var closed_set: MutableList<Tile> = mutableListOf()

            var this_tile: Tile
            while (open_set.isNotEmpty()){
                this_tile = open_set.take(1)[0]
                Log.d("CHECK", "To check" + this_tile.x + ", " + this_tile.y)
                open_set.removeAt(0)
                for (tile in return_near_tiles(this_tile)){
                    if (tile != null) {
                        if (tile in closed_set){
                            Log.d("UPDATE", "update the tile")
                            if (tile.fCost > this_tile.gCost + STEP_COST + tile.hCost && tile != start_tile){
                                // Must update
                                tile.parent = this_tile
                                tile.hCost = distance(tile, end_tile)
                                tile.gCost = tile.parent!!.gCost + STEP_COST /*+ tile.randomNoise*/
                            }
                        }
                        else{
                            Log.d("ADD", "add the tile")
                            tile.parent = this_tile
                            tile.hCost = distance(tile, end_tile)
                            tile.gCost = this_tile.gCost + STEP_COST /*+ tile.randomNoise*/
                            if (tile !in open_set) {
                                open_set.add(tile)
                            }
                        }
                        if (tile.x == end_tile.x && tile.y == end_tile.y){
                            // Reached the end_tile
                            Log.d("END", "Found the end-tile")
                            // Do something about the path
                            Log.d("Start tile", "" + start_tile.x + "," + start_tile.y)
                            Log.d("End tile", "" + end_tile.x + "," + end_tile.y)
                            var tempTile = tile
                            while(tempTile != null){
                                tempTile!!.content = Ground("river")
                                if(tempTile.parent != null){
                                    Log.d("tempTile", "I am " + tempTile.x + "," + tempTile.y + "and my parent is" + tempTile.parent!!.x + tempTile.parent!!.y)
                                }
                                tempTile = tempTile.parent

                            }
                            break
                        }
                    }
                    else{
                        Log.d("Null!", "A null tile was returned in A* loop")
                    }
                }
                closed_set.add(this_tile)
                Log.d("CHECK", "Checked" + this_tile.x + ", " + this_tile.y + " with parent" + (this_tile.parent?.x
                    ?: Int) + (this_tile.parent?.y ?: Int)
                )
                Collections.sort(open_set, TileComparator())
            }
        }

        if (enemies_quantity > 0){
            // Enemies appear NOTICE_RADIUS tiles away from campfire
            // TODO: Make NOTICE_RADIUS changeable by user
            var count_enemies : Int = enemies_quantity
            while (count_enemies > 0) {
                var rand_x: Int
                var rand_y: Int
                if (campfire_tile != null) {
                    //Choosing random x away from campfire
                    if (campfire_tile?.x - NOTICE_RADIUS <=0){
                        rand_x = (campfire_tile?.x + NOTICE_RADIUS until width).random()
                    }
                    else if (campfire_tile?.x + NOTICE_RADIUS >= width){
                        rand_x = (0 until campfire_tile?.x - NOTICE_RADIUS).random()
                    }
                    else{
                        rand_x =
                            if ((0..1).random() == 1)
                                (0 until campfire_tile?.x - NOTICE_RADIUS).random()
                            else
                                (campfire_tile?.x + NOTICE_RADIUS until width).random()
                    }
                    //Choosing random y away from campfire
                    if (campfire_tile?.y - NOTICE_RADIUS <= 0){
                        rand_y = (campfire_tile.y + NOTICE_RADIUS until height).random()
                    }
                    else if (campfire_tile?.y + NOTICE_RADIUS >= height){
                        rand_y = (0 until campfire_tile.y - NOTICE_RADIUS).random()
                    }
                    else{
                        rand_y =
                            if ((0..1).random() == 1)
                                (0 until campfire_tile.y - NOTICE_RADIUS).random()
                            else
                                (campfire_tile.y + NOTICE_RADIUS until height).random()
                    }
                }
                else{
                    rand_x = (0 until width).random()
                    rand_y = (0 until height).random()
                }

                // TILE CHECKS ? (Don't spawn over another monster or on rocks or smth)
                if (tiles_map[Pair(rand_x, rand_y)]?.character == null &&
                    tiles_map[Pair(rand_x, rand_y)]?.content!!.type != Ground("river")!!.type &&
                    tiles_map[Pair(rand_x, rand_y)]?.content!!.type != Ground("campfire")!!.type)
                {
                    tiles_map[Pair(rand_x, rand_y)]!!.character =
                        Character("Monster", 3, "Monster", 1, 0)
                }
                else{
                    continue
                }
                count_enemies--
            }

        }

        for (hero in heroes_list){
            var rand_x: Int
            var rand_y: Int
            do {
                if (campfire_tile != null) {
    //                    rand_x = (campfire_tile?.x - NOTICE_RADIUS .. campfire_tile?.x + NOTICE_RADIUS).random()
    //                    rand_y = (campfire_tile?.y - NOTICE_RADIUS .. campfire_tile?.y + NOTICE_RADIUS).random()
                    //Choosing random x away from campfire
                    if (campfire_tile?.x - CAMPFIRE_DISTANCE <=0){
                        rand_x = (0 until campfire_tile.x).random()
                    }
                    else if (campfire_tile?.x + CAMPFIRE_DISTANCE >= width){
                        rand_x = (campfire_tile?.x - CAMPFIRE_DISTANCE until width).random()
                    }
                    else{
                        rand_x = (campfire_tile?.x - CAMPFIRE_DISTANCE .. campfire_tile?.x + CAMPFIRE_DISTANCE).random()
                    }
                    //Choosing random x away from campfire
                    if (campfire_tile?.y - CAMPFIRE_DISTANCE <=0){
                        rand_y = (0 until campfire_tile.x).random()
                    }
                    else if (campfire_tile?.y + CAMPFIRE_DISTANCE >= width){
                        rand_y = (campfire_tile?.y - CAMPFIRE_DISTANCE until width).random()
                    }
                    else{
                        rand_y = (campfire_tile?.y - CAMPFIRE_DISTANCE .. campfire_tile?.y + CAMPFIRE_DISTANCE).random()
                    }
                }
                else{
                    rand_x = (0 until width).random()
                    rand_y = (0 until height).random()
                }
                // TILE CHECKS ? (Don't spawn over another monster or on rocks or smth)
            } while (tiles_map[Pair(rand_x, rand_y)]?.character != null ||
                tiles_map[Pair(rand_x, rand_y)]?.content!!.type == Ground("river")!!.type ||
                tiles_map[Pair(rand_x, rand_y)]?.content!!.type == Ground("campfire")!!.type)

            tiles_map[Pair(rand_x, rand_y)]!!.character = Character(hero.first.toString(), 3, hero.second.toString(), 1, 0)
        }
    }

    fun getBitmapFromView(view: View, totalWidth: Int, totalHeight: Int): Bitmap? {
        //Define a bitmap with the same size as the view
        val returnedBitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable: Drawable? = view.background
        if (bgDrawable != null) //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas) else  //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }
}
