package com.arkountos.orcsattack

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import kotlin.math.floor

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
        var bookinsanity: Typeface? = ResourcesCompat.getFont(this, R.font.scalysans)
        var bookinsanitybold: Typeface? = ResourcesCompat.getFont(this, R.font.scalysansbold)
        var bookinsanityitalic: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanityitalic)

        var name = findViewById<TextView>(R.id.statblock_name)
        var alignment = findViewById<TextView>(R.id.statblock_alignment)

        var useful_info_title = findViewById<TextView>(R.id.NPC_statblock_useful_info_title)
        var characteristic_title = findViewById<TextView>(R.id.NPC_statblock_characteristic_title)
        var secret_title = findViewById<TextView>(R.id.NPC_statblock_secret_title)
        var prone_to_title = findViewById<TextView>(R.id.NPC_statblock_prone_to_title)
        var strong_against_title = findViewById<TextView>(R.id.NPC_statblock_strong_against_title)
        var notes_title = findViewById<TextView>(R.id.NPC_statblock_notes_title)


        var useful_info_value = findViewById<TextView>(R.id.NPC_statblock_useful_info_value)
        var characteristic_value = findViewById<TextView>(R.id.NPC_statblock_characteristic_value)
        var secret_value = findViewById<TextView>(R.id.NPC_statblock_secret_value)
        var prone_to_value = findViewById<TextView>(R.id.NPC_statblock_prone_to_value)
        var strong_against_value = findViewById<TextView>(R.id.NPC_statblock_strong_against_value)
        var notes_value = findViewById<TextView>(R.id.NPC_statblock_notes_value)

        name.text = NPC_name.toString()
        name.textSize = 50F
        name.typeface = nodestocapscondensed
        name.setTextColor(resources.getColor(R.color.colorStatblockRed))

        alignment.text = "Medium " + NPC_race.toString()
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

        notes_title.text = "Notes"
        notes_title.typeface = bookinsanitybold
        notes_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        notes_value.text = NPC_notes.toString()
        notes_value.typeface = bookinsanity
        notes_value.setTextColor(resources.getColor(R.color.colorStatblockRed))
        if (notes_value.text == ""){
            notes_title.visibility = View.GONE
            notes_value.visibility = View.GONE
        }


        var NPC_strength_title = findViewById<TextView>(R.id.NPC_statblock_strength_title)
        var NPC_dexterity_title = findViewById<TextView>(R.id.NPC_statblock_dexterity_title)
        var NPC_constitution_title = findViewById<TextView>(R.id.NPC_statblock_constitution_title)
        var NPC_intelligence_title = findViewById<TextView>(R.id.NPC_statblock_intelligence_title)
        var NPC_wisdom_title = findViewById<TextView>(R.id.NPC_statblock_wisdom_title)
        var NPC_charisma_title = findViewById<TextView>(R.id.NPC_statblock_charisma_title)


        var NPC_strength_value = findViewById<TextView>(R.id.NPC_statblock_strength_value)
        var NPC_dexterity_value = findViewById<TextView>(R.id.NPC_statblock_dexterity_value)
        var NPC_constitution_value = findViewById<TextView>(R.id.NPC_statblock_constitution_value)
        var NPC_intelligence_value = findViewById<TextView>(R.id.NPC_statblock_intelligence_value)
        var NPC_wisdom_value = findViewById<TextView>(R.id.NPC_statblock_wisdom_value)
        var NPC_charisma_value = findViewById<TextView>(R.id.NPC_statblock_charisma_value)

        var NPC_strength_modifier: String = if (((NPC_strength.toString().toInt() - 10) /2) > 0)
            "+" + (NPC_strength.toString().toInt() - 10) / 2
        else
        ((NPC_strength.toString().toInt() - 10) / 2).toString()
        var NPC_dexterity_modifier: String = if (((NPC_dexterity.toString().toInt() - 10) /2) > 0)
            "+" + (NPC_dexterity.toString().toInt() - 10) / 2
        else
            ((NPC_dexterity.toString().toInt() - 10) / 2).toString()
        var NPC_constitution_modifier: String = if (((NPC_constitution.toString().toInt() - 10) /2) > 0)
            "+" + (NPC_constitution.toString().toInt() - 10) / 2
        else
            ((NPC_constitution.toString().toInt() - 10) / 2).toString()
        var NPC_intelligence_modifier: String = if (((NPC_intelligence.toString().toInt() - 10) /2) > 0)
            "+" + (NPC_intelligence.toString().toInt() - 10) / 2
        else
            ((NPC_intelligence.toString().toInt() - 10) / 2).toString()
        var NPC_wisdom_modifier: String = if (((NPC_wisdom.toString().toInt() - 10) /2) > 0)
            "+" + (NPC_wisdom.toString().toInt() - 10) / 2
        else
            ((NPC_wisdom.toString().toInt() - 10) / 2).toString()
        var NPC_charisma_modifier: String = if (((NPC_charisma.toString().toInt() - 10) /2) > 0)
            "+" + (NPC_charisma.toString().toInt() - 10) / 2
        else
            ((NPC_charisma.toString().toInt() - 10) / 2).toString()



        NPC_strength_title.text = "STR"
        NPC_strength_title.typeface = bookinsanitybold
        NPC_strength_title.setTextColor(resources.getColor(R.color.colorStatblockRed))


        NPC_strength_value.text = NPC_strength.toString() + "(" + NPC_strength_modifier + ")"
        NPC_strength_value.typeface = bookinsanitybold
        NPC_strength_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        NPC_dexterity_title.text = "DEX"
        NPC_dexterity_title.typeface = bookinsanitybold
        NPC_dexterity_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        NPC_dexterity_value.text = NPC_dexterity.toString() + "(" + NPC_dexterity_modifier + ")"
        NPC_dexterity_value.typeface = bookinsanitybold
        NPC_dexterity_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        NPC_constitution_title.text = "CON"
        NPC_constitution_title.typeface = bookinsanitybold
        NPC_constitution_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        NPC_constitution_value.text = NPC_constitution.toString() + "(" + NPC_constitution_modifier + ")"
        NPC_constitution_value.typeface = bookinsanitybold
        NPC_constitution_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        NPC_intelligence_title.text = "INT"
        NPC_intelligence_title.typeface = bookinsanitybold
        NPC_intelligence_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        NPC_intelligence_value.text = NPC_intelligence.toString() + "(" + NPC_intelligence_modifier + ")"
        NPC_intelligence_value.typeface = bookinsanitybold
        NPC_intelligence_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        NPC_wisdom_title.text = "WIS"
        NPC_wisdom_title.typeface = bookinsanitybold
        NPC_wisdom_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        NPC_wisdom_value.text = NPC_wisdom.toString() + "(" + NPC_wisdom_modifier + ")"
        NPC_wisdom_value.typeface = bookinsanitybold
        NPC_wisdom_value.setTextColor(resources.getColor(R.color.colorStatblockRed))


        NPC_charisma_title.text = "CHA"
        NPC_charisma_title.typeface = bookinsanitybold
        NPC_charisma_title.setTextColor(resources.getColor(R.color.colorStatblockRed))

        NPC_charisma_value.text = NPC_charisma.toString() + "(" + NPC_charisma_modifier + ")"
        NPC_charisma_value.typeface = bookinsanitybold
        NPC_charisma_value.setTextColor(resources.getColor(R.color.colorStatblockRed))





    }
}