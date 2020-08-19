package com.arkountos.orcsattack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NPCStatblockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.npc_pretty)

        var name = findViewById<TextView>(R.id.statblock_name)
//        var alignment = findViewById<TextView>(R.id.statblock_alignment)

        name.text = "Fivos"
    }
}