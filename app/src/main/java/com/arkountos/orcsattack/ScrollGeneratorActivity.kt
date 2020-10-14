package com.arkountos.orcsattack

import android.content.Intent
import android.graphics.Typeface
import android.hardware.SensorEventListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlin.properties.Delegates


class ScrollGeneratorActivity : AppCompatActivity() {

    private lateinit var font_option: Spinner
    private lateinit var font_result: Typeface
    private lateinit var font_result_name: String
    private lateinit var font_size_option: Spinner
    private var font_size_result by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parchment_generator)

        var lorem_ipsum = findViewById<EditText>(R.id.lorem_ipsum)

        var blackchancery: Typeface? = ResourcesCompat.getFont(this, R.font.blackchancery)
        var bookinsanity: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanity)
        var nodestocapscondensed: Typeface? = ResourcesCompat.getFont(this, R.font.nodestocapscondensed)
        var scalysans: Typeface? = ResourcesCompat.getFont(this, R.font.scalysans)
        var solberaimitation: Typeface? = ResourcesCompat.getFont(this, R.font.solberaimitation)
        var zatannamisdirection: Typeface? = ResourcesCompat.getFont(this, R.font.zatannamisdirection)

        var fontnametouri: Map<String, Typeface> = mapOf("blackchancery" to blackchancery!!)
        //Add them all to an array and pick from the array inside the spinner

        var fonts = arrayOf(
            blackchancery,
            bookinsanity,
            nodestocapscondensed,
            scalysans,
            solberaimitation,
            zatannamisdirection
        )
        var fonts_names = arrayOf(
            "Black Chancery",
            "Book Insanity",
            "Nodesto Caps Condensed",
            "Scaly Sans",
            "Solbera Imitation",
            "Zatanna Misdirection"
        )

        font_option = findViewById(R.id.font_spinner)
        font_option.adapter = ArrayAdapter<String>(this, R.layout.spinner_item, fonts_names)
        font_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                font_result = blackchancery!!
                font_result_name = fonts_names[0]
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                font_result_name = fonts_names[position]
                font_result = fonts[position]!!
                lorem_ipsum.typeface = font_result
            }
        }

        var sizes_array = arrayOf(10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30)

        var seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                font_size_result = progress
                lorem_ipsum.textSize = font_size_result.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
//        font_size_option = findViewById(R.id.font_size_spinner)
//        font_size_option.adapter = ArrayAdapter<Int>(this, R.layout.spinner_item, sizes_array)
//        font_size_option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                font_size_result = 20
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                font_size_result = sizes_array[position]
//                lorem_ipsum.textSize = font_size_result.toFloat()
//            }
//        }

        var gotoParchmentButton = findViewById<Button>(R.id.gotoParchmentButton)
        gotoParchmentButton.setOnClickListener {
            Log.d("con-in-tents", "font_result_name: " + font_result_name +", font_size_result: " + font_size_result.toString() + ", text: " + lorem_ipsum.text.toString())
            var intent = Intent(this, ScrollPresentationActivity::class.java)
            intent.putExtra("EXTRA_FONT", font_result_name)
            intent.putExtra("EXTRA_SIZE", font_size_result.toString())
            intent.putExtra("EXTRA_TEXT", lorem_ipsum.text.toString())
            startActivity(intent)
        }
    }
}