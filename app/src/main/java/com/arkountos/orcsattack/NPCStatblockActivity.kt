package com.arkountos.orcsattack

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.npc_pretty.*

class NPCStatblockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.npc_pretty)
        var rootView = findViewById<ConstraintLayout>(R.id.statblock_root_view)
        var name = findViewById<TextView>(R.id.statblock_name)
        name.text = "Fivos"

    }
}