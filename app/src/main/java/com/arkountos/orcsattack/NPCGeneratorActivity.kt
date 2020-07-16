package com.arkountos.orcsattack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class NPCGeneratorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n_p_c_generator)

        val NPC_names_lists = arrayListOf<String>("Human", "Elf", "Dwarf", "Halfling", "Gnome")

        val NPC_genders_array = applicationContext.resources.getStringArray(R.array.NPC_gender)
        val NPC_race_names_array = applicationContext.resources.getStringArray(R.array.NPC_races)
        val NPC_useful_info_array = applicationContext.resources.getStringArray(R.array.NPC_useful_info)
        val NPC_secrets_array = applicationContext.resources.getStringArray(R.array.NPC_secrets)
        val NPC_characteristics_array = applicationContext.resources.getStringArray(R.array.NPC_characteristics)
        val NPC_charisma_checks_array = applicationContext.resources.getStringArray(R.array.NPC_charisma_checks)
        var NPC_name_array = emptyArray<String>()

        val NPC_name = findViewById<TextView>(R.id.NPC_table_name_value)
        val NPC_gender = findViewById<TextView>(R.id.NPC_table_gender_value)
        val NPC_race = findViewById<TextView>(R.id.NPC_table_race_value)
        val NPC_characteristic = findViewById<TextView>(R.id.NPC_table_characteristic_value)
        val NPC_useful_info = findViewById<TextView>(R.id.NPC_table_usefulinfo_value)
        val NPC_secret = findViewById<TextView>(R.id.NPC_table_secret_value)
        val NPC_prone_to = findViewById<TextView>(R.id.NPC_table_proneto_value)
        val NPC_strong_against = findViewById<TextView>(R.id.NPC_table_strongagainst_value)
        val NPC_notes = findViewById<EditText>(R.id.NPC_table_notes_value)


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
            NPC_characteristic.text = NPC_characteristics_array[(NPC_characteristics_array.indices).random()]
            NPC_useful_info.text = NPC_useful_info_array[(NPC_useful_info_array.indices).random()]
            NPC_secret.text = NPC_secrets_array[(NPC_secrets_array.indices).random()]
            NPC_prone_to.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
            NPC_strong_against.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
            while (NPC_prone_to.text == NPC_strong_against.text){
                NPC_strong_against.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
            }

            var NPC_character = NPCCharacter(NPC_name.text as String,
                NPC_gender.text as String,
                NPC_race.text as String,
                NPC_useful_info.text as String,
                NPC_characteristic.text as String,
                NPC_secret.text as String,
                NPC_prone_to.text as String,
                NPC_strong_against.text as String,
                NPC_notes.text.toString()
            )



            NPC_character.rollStats()
            NPC_character.setStats(findViewById(R.id.NPC_str_stat_value),
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
        NPC_gender.setOnClickListener{
            NPC_gender.text = NPC_genders_array[(NPC_genders_array.indices).random()]
        }
        NPC_race.setOnClickListener{
            NPC_race.text = NPC_race_names_array[(NPC_race_names_array.indices).random()]
        }
        NPC_characteristic.setOnClickListener{
            NPC_characteristic.text = NPC_characteristics_array[(NPC_characteristics_array.indices).random()]
        }
        NPC_secret.setOnClickListener{
            NPC_secret.text = NPC_secrets_array[(NPC_secrets_array.indices).random()]
        }
        NPC_prone_to.setOnClickListener{
            NPC_prone_to.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
        }
        NPC_strong_against.setOnClickListener{
            NPC_strong_against.text = NPC_charisma_checks_array[(NPC_charisma_checks_array.indices).random()]
        }



    }


}