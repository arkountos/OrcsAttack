package com.arkountos.orcsattack

import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.Collections.min

class NPCCharacter (input_name: String,
                    input_gender: String,
                    input_race: String,
                    input_alignment: String,
                    input_useful_info: String,
                    input_characteristic: String,
                    input_secret: String,
                    input_prone_to: String,
                    input_strong_agains: String,
                    input_notes: String,
                    input_armor_class: String,
                    input_hit_points: String,
                    input_speed: String
){
    var name: String = input_name.capitalize()
    var gender = input_gender
    var race = input_race
    var alignment : String = input_alignment
    var useful_info = input_useful_info
    var characteristic = input_characteristic
    var secret = input_secret
    var prone_to = input_prone_to
    var strong_against = input_strong_agains
    var notes = input_notes
    var armor_class = input_armor_class
    var hit_points= input_hit_points
    var speed = input_speed

    var myclass: String = ""

    // STR, DEX, CON, INT, WIS, CHA
    var stats = intArrayOf(0,0,0,0,0,0)

    var strength: Int = stats[0]
    var dexterity: Int = stats[1]
    var constitution: Int = stats[2]
    var intelligence: Int = stats[3]
    var wisdom: Int = stats[4]
    var charisma: Int = stats[5]

    fun rollStats(){
        var j = 0
        for (i in (0 until 6)){
            var rolls = intArrayOf(6, 6, 6, 6)
            while (j < 4){
                rolls[j] = (0..6).random()
                j++
            }
            j = 0
            stats[i] = rolls.sum() - rolls.min()!!
        }
        Log.d("stats[]: ", stats.toString())

        strength = stats[0]
        dexterity = stats[1]
        constitution = stats[2]
        intelligence = stats[3]
        wisdom = stats[4]
        charisma = stats[5]
    }

    fun setStats(str: TextView, dex: TextView, con: TextView, int: TextView, wis: TextView, cha: TextView){
        str.text = strength.toString()
        dex.text = dexterity.toString()
        con.text = constitution.toString()
        int.text = intelligence.toString()
        wis.text = wisdom.toString()
        cha.text = charisma.toString()
    }

    fun getNPCStats(): IntArray {
        return intArrayOf(strength, dexterity, constitution, intelligence, wisdom, charisma)
    }


}