package com.example.inittrack2

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginTop
import com.google.android.material.floatingactionbutton.FloatingActionButton


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



        var campfire_input = findViewById<CheckBox>(R.id.campfire_checkbox)
        var trees_input = findViewById<CheckBox>(R.id.trees_checkbox)
        var hills_input = findViewById<CheckBox>(R.id.hills_checkbox)
        var stream_input = findViewById<CheckBox>(R.id.stream_checkbox)
        var rocks_input = findViewById<CheckBox>(R.id.rocks_checkbox)

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


        val constraint_view = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.constraint_inside)
        val add_hero_btn = findViewById<ImageButton>(R.id.add_hero_button)
        add_hero_btn.setOnClickListener{
            var toast = Toast.makeText(getApplicationContext(), "Pressed!", Toast.LENGTH_SHORT);
            toast.show()
            var constraintLayout : ConstraintLayout = findViewById(R.id.constraint_inside);

            // Add edittext
            var hero_name: EditText = EditText(this)
            hero_name.setText("Name")
            var params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            hero_name.layoutParams = params
            params.setMargins(0, 8, 0, 0)
            hero_name.id = View.generateViewId()
            hero_name.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            hero_name.setEms(6)
            hero_name.setTextColor(Color.WHITE)
            hero_name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10F)

            constraintLayout.addView(hero_name)

            // Add spinner
            var class_spinner : Spinner = Spinner(this)
            class_spinner.id = View.generateViewId()
            class_spinner= findViewById(com.example.inittrack2.R.id.class_spinner_map)



            var constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            constraintSet.connect(hero_name.id, ConstraintSet.TOP, R.id.hero_name_map, ConstraintSet.BOTTOM, 8)
            constraintSet.connect(hero_name.id, ConstraintSet.LEFT, R.id.hero_name_map, ConstraintSet.LEFT, 0)
            constraintSet.connect(R.id.enemies_quantity_edittext, ConstraintSet.TOP, hero_name.id, ConstraintSet.BOTTOM, 8)



            constraintSet.applyTo(constraintLayout)

//            var new_constraintSet = ConstraintSet()
//            new_constraintSet.clone(constraintLayout)


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


            startActivity(intent)
        }
    }
}
