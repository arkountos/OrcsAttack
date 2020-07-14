package com.arkountos.orcsattack

class NPCCharacter (input_name: String,
                    input_gender: String,
                    input_race: String,
                    input_useful_info: String,
                    input_characteristic: String,
                    input_secret: String,
                    input_prone_to: String,
                    input_strong_agains: String,
                    input_notes: String
){
    var name: String = input_name.capitalize()
    var gender = input_gender
    var race = input_race
    var useful_info = input_useful_info
    var characteristic = input_characteristic
    var secret = input_secret
    var prone_to = input_prone_to
    var strong_against = input_strong_agains
    var notes = input_notes

    var myclass: String = ""

    var strength: Int = 0
    var dexterity: Int = 0
    var constitution: Int = 0
    var intelligence: Int = 0
    var wisdom: Int = 0
    var charisma: Int = 0

    fun rollStats(){
        var i = 0
        var rolls = emptyArray<Int>()
        while (i < 6){

        }
    }

}