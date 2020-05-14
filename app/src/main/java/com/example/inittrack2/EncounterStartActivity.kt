package com.example.inittrack2

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_encounter_start.*


class EncounterStartActivity : AppCompatActivity() {

    private lateinit var height_option : Spinner
    private lateinit var width_option : Spinner
    private lateinit var trees_probability_option : Spinner
    private lateinit var rocks_probability_option : Spinner
    private lateinit var enemies_quantity_option: Spinner
    private lateinit var hero_class_option: Spinner




    val classes = arrayOf(
        "Barbarian",
        "Bard",
        "Cleric",
        "Druid",
        "Fighter",
        "Monk",
        "Paladin",
        "Ranger",
        "Rogue",
        "Sorcerer",
        "Warlock",
        "Wizard"
    )

    val quantities = arrayOf(
        "0",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10"
    )

    val height_width_values = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        "20",
        "25",
        "50",
        "100"
    )

    val probabilities = arrayOf(
        "1%",
        "2%",
        "5%",
        "10%",
        "15%",
        "20%",
        "25%",
        "50%",
        "75%"
    )

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encounter_start)

        title = "Map generation settings"

        var height_input = "5"
        var width_input = "5"
        var trees_probability_input = "5%"
        var rocks_probability_input = "5%"
        var enemies_quantity_input = "1"
        var hero_class_input = "Fighter"

        var heroes: MutableList<Pair<String, String>> = mutableListOf()
        var nameclass_ids: MutableList<Pair<Int, Int>> = mutableListOf()
        var class_id_name_map : HashMap<Int, String> = hashMapOf()

        val campfire_input = findViewById<CheckBox>(R.id.campfire_checkbox)
        val trees_input = findViewById<CheckBox>(R.id.trees_checkbox)
        val hills_input = findViewById<CheckBox>(R.id.hills_checkbox)
        val stream_input = findViewById<CheckBox>(R.id.stream_checkbox)
        val rocks_input = findViewById<CheckBox>(R.id.rocks_checkbox)

        // Class choosing Spinner
        height_option = findViewById(com.example.inittrack2.R.id.height_spinner)
        width_option = findViewById(com.example.inittrack2.R.id.width_spinner)
        trees_probability_option = findViewById(com.example.inittrack2.R.id.tree_probability_spinner)
        rocks_probability_option = findViewById(com.example.inittrack2.R.id.rock_probability_spinner)
        enemies_quantity_option = findViewById(com.example.inittrack2.R.id.enemies_quantity_spinner)
        hero_class_option = findViewById(com.example.inittrack2.R.id.class_spinner_map)




        height_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, height_width_values)
        width_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, height_width_values)
        trees_probability_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, probabilities)
        rocks_probability_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, probabilities)
        enemies_quantity_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, quantities)
        hero_class_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, classes)




        height_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                height_input = "5"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                height_input = height_width_values[position]

            }
        }

        width_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                width_input = "5"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                width_input = height_width_values[position]

            }
        }

        height_option.setSelection(9)
        width_option.setSelection(9)

        trees_probability_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                trees_probability_input = "5%"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                trees_probability_input = probabilities[position]

            }
        }

        rocks_probability_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                rocks_probability_input = "5%"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                rocks_probability_input = probabilities[position]

            }
        }

        trees_probability_option.setSelection(3)
        rocks_probability_option.setSelection(3)

        enemies_quantity_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                enemies_quantity_input = "1"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                enemies_quantity_input = quantities[position]

            }
        }

        hero_class_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                hero_class_input = "Fighter"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                hero_class_input = classes[position]

            }
        }

        enemies_quantity_option.setSelection(0)
        hero_class_option.setSelection(0)

        var last_element_on_list = findViewById<EditText>(R.id.hero_name_map)
        var last_spinner_on_list = findViewById<Spinner>(R.id.class_spinner_map)



        val add_hero_btn = findViewById<ImageButton>(R.id.add_hero_button)
        add_hero_btn.setOnClickListener{
            var toast = Toast.makeText(applicationContext, "Pressed!", Toast.LENGTH_SHORT);
            toast.show()
            var constraintLayout : ConstraintLayout = findViewById(R.id.constraint_inside);

            // Add edittext
            var hero_name: EditText = EditText(this)
            hero_name.hint = "Name"
            hero_name.setHintTextColor(Color.WHITE)
            var params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            hero_name.layoutParams = params
            hero_name.id = View.generateViewId()
            hero_name.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            hero_name.setEms(6)
            hero_name.setTextColor(Color.WHITE)
            hero_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10F)
            Log.d("EditText", "name: " + hero_name.text.toString())
            constraintLayout.addView(hero_name)

            // Add spinner
            var class_spinner : Spinner = Spinner(this)
            var class_spinner_input = ""
            class_spinner.id = View.generateViewId()

            class_spinner.adapter = ArrayAdapter<String>(this, R.layout.spinner_item, classes)
            var spinner_params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            class_spinner.layoutParams = spinner_params
            var input: String = "Paladin"
            Log.d("Before", "onItemSelectedListener")
            class_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    class_spinner_input = "Fighter"
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("HERE!", "in onItemSelected()")
                    class_spinner_input = classes[position]
                    class_id_name_map[class_spinner.id] = class_spinner_input
                }
            }
            Log.d("After", "onItemSelectedListener")



            nameclass_ids.add(Pair(hero_name.id, class_spinner.id))

            var constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            constraintSet.connect(hero_name.id, ConstraintSet.TOP, last_element_on_list.id, ConstraintSet.BOTTOM, 40)
            constraintSet.connect(hero_name.id, ConstraintSet.LEFT, last_element_on_list.id, ConstraintSet.LEFT, 0)

            constraintSet.applyTo(constraintLayout)
            last_element_on_list = hero_name

            constraintLayout.addView(class_spinner)

            var constraintSet2 = ConstraintSet()
            constraintSet2.clone(constraintLayout)
            constraintSet2.connect(class_spinner.id, ConstraintSet.TOP, last_element_on_list.id, ConstraintSet.TOP, 0)
            constraintSet2.connect(class_spinner.id, ConstraintSet.LEFT, last_spinner_on_list.id, ConstraintSet.LEFT, 0)

            constraintSet2.applyTo(constraintLayout)
            last_spinner_on_list = class_spinner

            var constraintSet3 = ConstraintSet()
            constraintSet3.clone(constraintLayout)
            constraintSet3.connect(R.id.enemies_quantity_edittext, ConstraintSet.TOP, last_spinner_on_list.id, ConstraintSet.BOTTOM, 16)

            constraintSet3.applyTo(constraintLayout)

        }


        val encounter_start_btn_done = findViewById<FloatingActionButton>(com.example.inittrack2.R.id.encounter_start_done)
        encounter_start_btn_done.setOnClickListener {

            var height_result = height_input
            var width_result = width_input
            var trees_probability_result = trees_probability_input
            var rocks_probability_result = rocks_probability_input
            var enemies_quantity_result = enemies_quantity_input


            var campfire_result = if(campfire_input.isChecked) "1" else "0"
            var trees_result = if(trees_input.isChecked) "1" else "0"
            var hills_result = if(hills_input.isChecked) "1" else "0"
            var stream_result = if(stream_input.isChecked) "1" else "0"
            var rocks_result = if(rocks_input.isChecked) "1" else "0"

            for ((name_id, class_id) in nameclass_ids) {
                var hero : Pair<String, String> = Pair(findViewById<EditText>(name_id).text.toString(), class_id_name_map[class_id]!!)
                Log.d("Heroes Size", "Heroes size is " + heroes.size)
                heroes.add(hero)
                Log.d("Heroes Size After", "Heroes size is " + heroes.size)
                Log.d("Hero added is: ", "" + hero)
            }




            val intent = Intent(this, EncounterGeneratorActivity::class.java)
            intent.putExtra("EXTRA_HEIGHT", height_result)
            intent.putExtra("EXTRA_WIDTH", width_result)
            intent.putExtra("EXTRA_TREES_PROBABILITY", trees_probability_result)
            intent.putExtra("EXTRA_CAMPFIRE", campfire_result)
            intent.putExtra("EXTRA_TREES", trees_result)
            intent.putExtra("EXTRA_HILLS", hills_result)
            intent.putExtra("EXTRA_STREAM", stream_result)
            intent.putExtra("EXTRA_ROCKS", rocks_result)
            intent.putExtra("EXTRA_ROCKS_PROBABILITY", rocks_probability_result)
            intent.putExtra("EXTRA_ENEMIES_QUANTITY", enemies_quantity_result)
            intent.putExtra("EXTRA_HERO_CLASS", hero_class_input)
            intent.putExtra("EXTRA_HEROES_SIZE", heroes.size.toString())
            Log.d("Heroes.size is", heroes.size.toString())
            for ((i, hero) in heroes.withIndex()){
                Log.d("i.toString()", "" + i.toString())
                var extra_name = "EXTRA_HERO_" + i.toString() + "_NAME"
                Log.d("extra_name" , "" + extra_name)
                Log.d("hero.first", "" + hero.first)
                intent.putExtra(extra_name.toString(), hero.first)
                var extra_class = "EXTRA_HERO_" + i.toString() + "_CLASS"
                intent.putExtra(extra_class.toString(), hero.second)
            }

            Log.d("Print Heroes", "")
            for ((i, hero) in heroes.withIndex()){
                Log.d("Hero:", "$i : $hero")
            }

            startActivity(intent)
        }
    }


//    fun spinnerChoose(spinner: Spinner): String{
//        var input: String = "Fighter"
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                input = "Fighter"
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                input = classes[position]
//            }
//        }
//        return(input)
//    }
}
