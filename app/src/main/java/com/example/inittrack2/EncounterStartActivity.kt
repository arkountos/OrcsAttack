package com.example.inittrack2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_encounter_start.*

class EncounterStartActivity : AppCompatActivity() {

    private lateinit var height_option : Spinner
    private lateinit var width_option : Spinner
    private lateinit var trees_probability_option : Spinner

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
        "10"
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encounter_start)

        title = "Map generation settings"

        var height_input = "5"
        var width_input = "5"
        var trees_probability_input = "5%"

        var campfire_input = findViewById<CheckBox>(R.id.campfire_checkbox)
        var trees_input = findViewById<CheckBox>(R.id.trees_checkbox)
        var hills_input = findViewById<CheckBox>(R.id.hills_checkbox)
        var stream_input = findViewById<CheckBox>(R.id.stream_checkbox)

        // Class choosing Spinner
        height_option = findViewById(com.example.inittrack2.R.id.height_spinner)
        width_option = findViewById(com.example.inittrack2.R.id.width_spinner)
        trees_probability_option = findViewById(com.example.inittrack2.R.id.tree_probability_spinner)


        height_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, height_width_values)
        width_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, height_width_values)
        trees_probability_option.adapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, probabilities)


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


        val encounter_start_btn_done = findViewById<FloatingActionButton>(com.example.inittrack2.R.id.encounter_start_done)
        encounter_start_btn_done.setOnClickListener {

            var height_result = height_input
            var width_result = width_input
            var trees_probability_result = trees_probability_input

            var campfire_result = if(campfire_input.isChecked) "1" else "0"
            var trees_result = if(trees_input.isChecked) "1" else "0"
            var hills_result = if(hills_input.isChecked) "1" else "0"
            var stream_result = if(stream_input.isChecked) "1" else "0"


            val intent = Intent(this, EncounterGeneratorActivity::class.java)
            intent.putExtra("EXTRA_HEIGHT", height_result)
            intent.putExtra("EXTRA_WIDTH", width_result)
            intent.putExtra("EXTRA_TREES_PROBABILITY", trees_probability_result)
            intent.putExtra("EXTRA_CAMPFIRE", campfire_result)
            intent.putExtra("EXTRA_TREES", trees_result)
            intent.putExtra("EXTRA_HILLS", hills_result)
            intent.putExtra("EXTRA_STREAM", stream_result)


            startActivity(intent)
        }
    }


}
