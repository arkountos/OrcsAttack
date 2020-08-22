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

        var NPC_name = intent.extras?.get("EXTRA_NPC_NAME")
        var NPC_race = intent.extras?.get("EXTRA_NPC_RACE")
        var NPC_gender = intent.extras?.get("EXTRA_NPC_GENDER")
        var NPC_useful_info = intent.extras?.get("EXTRA_NPC_USEFUL_INFO")
        var NPC_characteristic = intent.extras?.get("EXTRA_NPC_CHARACTERISTIC")
        var NPC_secret = intent.extras?.get("EXTRA_NPC_SECRET")
        var NPC_prone_to = intent.extras?.get("EXTRA_NPC_PRONE_TO")
        var NPC_strong_against = intent.extras?.get("EXTRA_NPC_STRONG_AGAINST")
        var NPC_notes = intent.extras?.get("EXTRA_NPC_NOTES")

        var NPC_strength = intent.extras?.get("EXTRA_NPC_STRENGTH")
        var NPC_dexterity = intent.extras?.get("EXTRA_NPC_DEXTERITY")
        var NPC_constitution = intent.extras?.get("EXTRA_NPC_CONSTITUTION")
        var NPC_intelligence = intent.extras?.get("EXTRA_NPC_INTELLIGENCE")
        var NPC_wisdom = intent.extras?.get("EXTRA_NPC_WISDOM")
        var NPC_charisma = intent.extras?.get("EXTRA_NPC_CHARISMA")

        var nodestocapscondensed: Typeface? = ResourcesCompat.getFont(this, R.font.nodestocapscondensed)
        var bookinsanityitalic: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanityitalic)

        var name = findViewById<TextView>(R.id.statblock_name)
        var alignment = findViewById<TextView>(R.id.statblock_alignment)

        name.text = npc_name.toString()
        name.textSize = 50F
        name.typeface = nodestocapscondensed
        name.setTextColor(resources.getColor(R.color.colorStatblockRed))

        alignment.text = "Medium undead, chaotic evil"
        alignment.typeface = bookinsanityitalic
        alignment.setTextColor(resources.getColor(R.color.colorBlack))




    }
}