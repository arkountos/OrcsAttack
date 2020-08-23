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

        var rootView = findViewById<ConstraintLayout>(R.id.statblock_root_view)

        var nodestocapscondensed: Typeface? = ResourcesCompat.getFont(this, R.font.nodestocapscondensed)
        var bookinsanity: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanity)
        var bookinsanitybold: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanitybold)
        var bookinsanityitalic: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanityitalic)

        var name = findViewById<TextView>(R.id.statblock_name)
        var alignment = findViewById<TextView>(R.id.statblock_alignment)

        var useful_info_title = findViewById<TextView>(R.id.NPC_statblock_useful_info_title)
        var characteristic_title = findViewById<TextView>(R.id.NPC_statblock_characteristic_title)
        var secret_title = findViewById<TextView>(R.id.NPC_statblock_secret_title)
        var prone_to_title = findViewById<TextView>(R.id.NPC_statblock_prone_to_title)
        var strong_against_title = findViewById<TextView>(R.id.NPC_statblock_strong_against_title)

        var useful_info_value = findViewById<TextView>(R.id.NPC_statblock_useful_info_value)
        var characteristic_value = findViewById<TextView>(R.id.NPC_statblock_characteristic_value)
        var secret_value = findViewById<TextView>(R.id.NPC_statblock_secret_value)
        var prone_to_value = findViewById<TextView>(R.id.NPC_statblock_prone_to_value)
        var strong_against_value = findViewById<TextView>(R.id.NPC_statblock_strong_against_value)


        name.text = NPC_name.toString()
        name.textSize = 50F
        name.typeface = nodestocapscondensed
        name.setTextColor(resources.getColor(R.color.colorStatblockRed))

        alignment.text = "Medium undead, chaotic evil"
        alignment.typeface = bookinsanityitalic
        alignment.setTextColor(resources.getColor(R.color.colorBlack))

        useful_info_title.text = "Useful Info"
        useful_info_title.typeface = bookinsanitybold
        useful_info_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        useful_info_value.text = NPC_useful_info.toString()
        useful_info_value.typeface = bookinsanity
        useful_info_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        characteristic_title.text = "Characteristic"
        characteristic_title.typeface = bookinsanitybold
        characteristic_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        characteristic_value.text = NPC_characteristic.toString()
        characteristic_value.typeface = bookinsanity
        characteristic_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        secret_title.text = "Secret"
        secret_title.typeface = bookinsanitybold
        secret_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        secret_value.text = NPC_secret.toString()
        secret_value.typeface = bookinsanity
        secret_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        prone_to_title.text = "Prone To"
        prone_to_title.typeface = bookinsanitybold
        prone_to_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        prone_to_value.text = NPC_prone_to.toString()
        prone_to_value.typeface = bookinsanity
        prone_to_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        strong_against_title.text = "Strong Against"
        strong_against_title.typeface = bookinsanitybold
        strong_against_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        strong_against_value.text = NPC_strong_against.toString()
        strong_against_value.typeface = bookinsanity
        strong_against_value.setTextColor(resources.getColor(R.color.colorStatblockRed))






    }
}