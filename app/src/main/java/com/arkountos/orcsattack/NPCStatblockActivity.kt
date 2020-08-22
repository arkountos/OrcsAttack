package com.arkountos.orcsattack

import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat

class NPCStatblockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.npc_pretty)
        var rootView = findViewById<ConstraintLayout>(R.id.statblock_root_view)
        var nodestocapscondensed: Typeface? = ResourcesCompat.getFont(this, R.font.nodestocapscondensed)
        var bookinsanityitalic: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanityitalic)

        var name = findViewById<TextView>(R.id.statblock_name)
        var alignment = findViewById<TextView>(R.id.statblock_alignment)

        name.text = "Ghoul"
        name.textSize = 50F
        name.typeface = nodestocapscondensed
        name.setTextColor(resources.getColor(R.color.colorStatblockRed))

        alignment.text = "Medium undead, chaotic evil"
        alignment.typeface = bookinsanityitalic
        alignment.setTextColor(resources.getColor(R.color.colorBlack))




    }
}