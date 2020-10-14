package com.arkountos.orcsattack

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class ScrollPresentationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_presentation)

        var blackchancery: Typeface? = ResourcesCompat.getFont(this, R.font.blackchancery)
        var bookinsanity: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanity)
        var nodestocapscondensed: Typeface? = ResourcesCompat.getFont(this, R.font.nodestocapscondensed)
        var scalysans: Typeface? = ResourcesCompat.getFont(this, R.font.scalysans)
        var solberaimitation: Typeface? = ResourcesCompat.getFont(this, R.font.solberaimitation)
        var zatannamisdirection: Typeface? = ResourcesCompat.getFont(this, R.font.zatannamisdirection)

        var fontnametouri: Map<String, Typeface> = mapOf(
            "Black Chancery" to blackchancery!!,
            "Book Insanity" to bookinsanity!!,
            "Nodesto Caps Condensed" to nodestocapscondensed!!,
            "Scaly Sans" to scalysans!!,
            "Solbera Imitation" to solberaimitation!!,
            "Zatanna Misdirection" to zatannamisdirection!!)

        var font_result = intent.getStringExtra("EXTRA_FONT")
        var font_size_result = intent.getStringExtra("EXTRA_SIZE")
        var text_result = intent.getStringExtra("EXTRA_TEXT")

        Log.d("Target activity:", "$font_result  $font_size_result  $text_result")

        var note = findViewById<TextView>(R.id.note)
        note.text = text_result.toString()
        note.textSize = font_size_result.toFloat()
        note.typeface = fontnametouri[font_result]
    }
}