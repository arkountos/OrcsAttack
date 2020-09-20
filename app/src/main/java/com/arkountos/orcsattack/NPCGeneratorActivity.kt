package com.arkountos.orcsattack

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.w3c.dom.Text


class NPCGeneratorActivity : AppCompatActivity() {
    // Initializations needed later in the code
    val gson = Gson()
    lateinit var NPC_character: NPCCharacter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n_p_c_generator)

        val NPC_names_lists = arrayListOf<String>("Human", "Elf", "Dwarf", "Halfling", "Gnome")

        val NPC_genders_array = applicationContext.resources.getStringArray(R.array.NPC_gender)
        val NPC_race_names_array = applicationContext.resources.getStringArray(R.array.NPC_races)
        val NPC_alignments_array = applicationContext.resources.getStringArray(R.array.NPC_alignment)
        val NPC_useful_info_array = applicationContext.resources.getStringArray(R.array.NPC_useful_info)
        val NPC_secrets_array = applicationContext.resources.getStringArray(R.array.NPC_secrets)
        val NPC_characteristics_array = applicationContext.resources.getStringArray(R.array.NPC_characteristics)
        val NPC_charisma_checks_array = applicationContext.resources.getStringArray(R.array.NPC_charisma_checks)
        var NPC_name_array = emptyArray<String>()

        val NPC_name = findViewById<TextView>(R.id.NPC_table_name_value)
        val NPC_gender = findViewById<TextView>(R.id.NPC_table_gender_value)
        val NPC_race = findViewById<TextView>(R.id.NPC_table_race_value)
        val NPC_alignment = findViewById<TextView>(R.id.NPC_table_alignment_value)
        val NPC_characteristic = findViewById<TextView>(R.id.NPC_table_characteristic_value)
        val NPC_useful_info = findViewById<TextView>(R.id.NPC_table_usefulinfo_value)
        val NPC_secret = findViewById<TextView>(R.id.NPC_table_secret_value)
        val NPC_prone_to = findViewById<TextView>(R.id.NPC_table_proneto_value)
        val NPC_strong_against = findViewById<TextView>(R.id.NPC_table_strongagainst_value)
        val NPC_notes = findViewById<EditText>(R.id.NPC_table_notes_value)

        val NPC_armor_class = findViewById<TextView>(R.id.NPC_table_armor_class_value)
        val NPC_hit_points = findViewById<TextView>(R.id.NPC_table_hit_points_value)
        val NPC_speed = findViewById<TextView>(R.id.NPC_table_speed_value)

        val NPC_strength = findViewById<TextView>(R.id.NPC_str_stat_value)
        val NPC_dexterity = findViewById<TextView>(R.id.NPC_dex_stat_value)
        val NPC_constitution = findViewById<TextView>(R.id.NPC_con_stat_value)
        val NPC_intelligence = findViewById<TextView>(R.id.NPC_int_stat_value)
        val NPC_wisdom = findViewById<TextView>(R.id.NPC_wis_stat_value)
        val NPC_charisma = findViewById<TextView>(R.id.NPC_cha_stat_value)


        var generateButton = findViewById<Button>(R.id.generate_NPC_button)
        generateButton.setOnClickListener{
            NPC_gender.text = NPC_genders_array[(NPC_genders_array.indices).random()]
            NPC_race.text = NPC_race_names_array[(NPC_race_names_array.indices).random()]
            if (NPC_race.text == "Human"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_human_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_human_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_human_female)
                                     else applicationContext.resources.getStringArray(R.array.NPC_names_human_male)
                }
            }
            else if (NPC_race.text == "Elf"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_elf_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_elf_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_elf_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_elf_male)
                }
            }
            else if (NPC_race.text == "Dwarf"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_male)
                }
            }
            else if (NPC_race.text == "Halfling"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_halfling_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_halfling_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_halfling_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_halfling_male)
                }
            }
            else{
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_gnome_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_gnome_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_gnome_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_gnome_male)
                }
            }
            NPC_name.text = NPC_name_array[(NPC_name_array.indices).random()]
            NPC_alignment.text = NPC_alignments_array[(NPC_alignments_array.indices).random()]
            NPC_characteristic.text = NPC_characteristics_array[(NPC_characteristics_array.indices).random()]
            NPC_useful_info.text = NPC_useful_info_array[(NPC_useful_info_array.indices).random()]
            NPC_secret.text = NPC_secrets_array[(NPC_secrets_array.indices).random()]
            NPC_prone_to.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
            NPC_strong_against.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
            while (NPC_prone_to.text == NPC_strong_against.text){
                NPC_strong_against.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
            }

            var NPC_difficulty_level = (1..10).random()
            NPC_armor_class.text = (10 + NPC_difficulty_level + (-1..1).random()).toString()
            NPC_armor_class.setOnLongClickListener {
                val alertDialog = Dialog(this)
                var inflater = layoutInflater
                val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
                alertDialog.setContentView(view)

                var new_NPC_armor_class = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
                val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
                okButton.setOnClickListener {
                    NPC_armor_class.text = new_NPC_armor_class.text
                    alertDialog.dismiss()
                }
                val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
                cancelButton.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
                true
            }
            NPC_hit_points.text = (10 + (5 * NPC_difficulty_level) + (-3..3).random()).toString()
            NPC_hit_points.setOnLongClickListener {
                val alertDialog = Dialog(this)
                var inflater = layoutInflater
                val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
                alertDialog.setContentView(view)

                var new_NPC_hit_points = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
                val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
                okButton.setOnClickListener {
                    NPC_hit_points.text = new_NPC_hit_points.text
                    alertDialog.dismiss()
                }
                val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
                cancelButton.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
                true
            }
            NPC_speed.text = if (NPC_race.text == "Dwarf" || NPC_race.text == "Halfling") 25.toString() else 30.toString()
            NPC_speed.setOnLongClickListener {
                val alertDialog = Dialog(this)
                var inflater = layoutInflater
                val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
                alertDialog.setContentView(view)

                var new_NPC_speed = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
                val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
                okButton.setOnClickListener {
                    NPC_speed.text = new_NPC_speed.text
                    alertDialog.dismiss()
                }
                val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
                cancelButton.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
                true
            }

            NPC_character = NPCCharacter(NPC_name.text as String,
                NPC_gender.text as String,
                NPC_race.text as String,
                NPC_alignment.text as String,
                NPC_useful_info.text as String,
                NPC_characteristic.text as String,
                NPC_secret.text as String,
                NPC_prone_to.text as String,
                NPC_strong_against.text as String,
                NPC_notes.text.toString(),
                NPC_armor_class.text as String,
                NPC_hit_points.text as String,
                NPC_speed.text as String
            )



            NPC_character.rollStats()
            NPC_character.setStats(
                findViewById(R.id.NPC_str_stat_value),
                findViewById(R.id.NPC_dex_stat_value),
                findViewById(R.id.NPC_con_stat_value),
                findViewById(R.id.NPC_int_stat_value),
                findViewById(R.id.NPC_wis_stat_value),
                findViewById(R.id.NPC_cha_stat_value)
            )

        }

        NPC_name.setOnClickListener {
            if (NPC_race.text == "Human"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_human_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_human_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_human_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_human_male)
                }
            }
            else if (NPC_race.text == "Elf"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_elf_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_elf_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_elf_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_elf_male)
                }
            }
            else if (NPC_race.text == "Dwarf"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_dwarf_male)
                }
            }
            else if (NPC_race.text == "Halfling"){
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_halfling_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_halfling_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_halfling_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_halfling_male)
                }
            }
            else{
                if (NPC_gender.text == "Female"){
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_gnome_female)
                }
                else if (NPC_gender.text == "Male") {
                    NPC_name_array = applicationContext.resources.getStringArray(R.array.NPC_names_gnome_male)
                }
                else{
                    NPC_name_array = if ((0..1).random() == 1) applicationContext.resources.getStringArray(R.array.NPC_names_gnome_female)
                    else applicationContext.resources.getStringArray(R.array.NPC_names_gnome_male)
                }
            }

            NPC_name.text = NPC_name_array[(NPC_name_array.indices).random()]
        }
        NPC_name.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_name = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_name.text = new_NPC_name.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_gender.setOnClickListener{
            NPC_gender.text = NPC_genders_array[(NPC_genders_array.indices).random()]
        }
        NPC_gender.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_gender = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_gender.text = new_NPC_gender.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_race.setOnClickListener{
            NPC_race.text = NPC_race_names_array[(NPC_race_names_array.indices).random()]
        }
        NPC_race.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_race = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_race.text = new_NPC_race.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_alignment.setOnClickListener {
            NPC_alignment.text = NPC_alignments_array[(NPC_alignments_array.indices).random()]
        }
        NPC_alignment.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_alignment = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_alignment.text = new_NPC_alignment.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_characteristic.setOnClickListener{
            NPC_characteristic.text = NPC_characteristics_array[(NPC_characteristics_array.indices).random()]
        }
        NPC_characteristic.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_characteristic = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_characteristic.text = new_NPC_characteristic.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_useful_info.setOnClickListener{
            NPC_useful_info.text = NPC_useful_info_array[(NPC_useful_info_array.indices).random()]
        }
        NPC_useful_info.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_useful_info = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_useful_info.text = new_NPC_useful_info.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_secret.setOnClickListener{
            NPC_secret.text = NPC_secrets_array[(NPC_secrets_array.indices).random()]
        }
        NPC_secret.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_secret = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_secret.text = new_NPC_secret.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_prone_to.setOnClickListener{
            NPC_prone_to.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
        }
        NPC_prone_to.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_prone_to = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_prone_to.text = new_NPC_prone_to.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_strong_against.setOnClickListener{
            NPC_strong_against.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
        }
        NPC_strong_against.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_strong_against = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_strong_against.text = new_NPC_strong_against.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }

        NPC_strength.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_strength = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_strength.text = new_NPC_strength.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_dexterity.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_dexterity = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_dexterity.text = new_NPC_dexterity.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_constitution.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_constitution = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_constitution.text = new_NPC_constitution.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_intelligence.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_intelligence = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_intelligence.text = new_NPC_intelligence.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_wisdom.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_wisdom = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_wisdom.text = new_NPC_wisdom.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }
        NPC_charisma.setOnLongClickListener {
            val alertDialog = Dialog(this)
            var inflater = layoutInflater
            val view: View = inflater.inflate(com.arkountos.orcsattack.R.layout.npc_generator_custom_entry_dialog, null)
            alertDialog.setContentView(view)

            var new_NPC_charisma = alertDialog.findViewById<EditText>(R.id.custom_entry_edittext)
            val okButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_ok_button)
            okButton.setOnClickListener {
                NPC_charisma.text = new_NPC_charisma.text
                alertDialog.dismiss()
            }
            val cancelButton = alertDialog.findViewById<Button>(R.id.npc_custom_entry_dialog_cancel_button)
            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
            true
        }

        // Go to Statblock button functionality
        var goToStatblockButton = findViewById<Button>(R.id.goToStatblock_NPC_button)
        goToStatblockButton.setOnClickListener {
            var NPC_Statblock_intent: Intent = Intent(this, NPCStatblockActivity::class.java)

            if (NPC_name.text == "" || NPC_race.text == "" || NPC_gender.text == ""  || NPC_alignment.text == "" || NPC_useful_info.text == ""  || NPC_characteristic.text == ""  || NPC_secret.text == ""  || NPC_prone_to.text == ""  || NPC_strong_against.text == ""){
                Toast.makeText(this, "Generate an NPC first!",
                    Toast.LENGTH_SHORT).show();
            }
            else {
                //TODO: Add intent contents
                NPC_Statblock_intent.putExtra("EXTRA_NPC_NAME", NPC_name.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_RACE", NPC_race.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_ALIGNMENT", NPC_alignment.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_GENDER", NPC_gender.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_USEFUL_INFO", NPC_useful_info.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_CHARACTERISTIC", NPC_characteristic.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_SECRET", NPC_secret.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_PRONE_TO", NPC_prone_to.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_STRONG_AGAINST", NPC_strong_against.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_NOTES", NPC_notes.text)

                NPC_Statblock_intent.putExtra("EXTRA_NPC_ARMOR_CLASS", NPC_armor_class.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_HIT_POINTS", NPC_hit_points.text)
                NPC_Statblock_intent.putExtra("EXTRA_NPC_SPEED", NPC_speed.text)


                var stats = NPC_character.getNPCStats()

                NPC_Statblock_intent.putExtra("EXTRA_NPC_STRENGTH", stats[0])
                NPC_Statblock_intent.putExtra("EXTRA_NPC_DEXTERITY", stats[1])
                NPC_Statblock_intent.putExtra("EXTRA_NPC_CONSTITUTION", stats[2])
                NPC_Statblock_intent.putExtra("EXTRA_NPC_INTELLIGENCE", stats[3])
                NPC_Statblock_intent.putExtra("EXTRA_NPC_WISDOM", stats[4])
                NPC_Statblock_intent.putExtra("EXTRA_NPC_CHARISMA", stats[5])

                startActivity(NPC_Statblock_intent)
            }
        }



    }

    fun save(NPC_character: NPCCharacter){
        val sharedPreferences : SharedPreferences = getSharedPreferences(GlobalsActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()

        // Jsonify
//        val char = Character(name, initiative, myclass, hitpoints.toInt())
        val jsonchar = gson.toJson(NPC_character)

        // Get string set with chars
        var NPCs: Set<String>
        if (sharedPreferences.getStringSet("saved_NPC_characters", null) != null){
            NPCs = sharedPreferences.getStringSet("saved_NPC_characters", null)!!
        }
        else{
            NPCs = mutableSetOf()
            editor.putStringSet("saved_NPC_characters", NPCs)
            editor.apply()
            NPCs = sharedPreferences.getStringSet("saved_NPC_characters", null)!!
        }

        // Put new hero to heroes
        NPCs.add(jsonchar)

        // Put new heroes to shared prefs
        editor.putStringSet("saved_NPC_characters", NPCs)
        editor.apply()

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
    }


}