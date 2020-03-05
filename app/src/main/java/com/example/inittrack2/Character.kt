package com.example.inittrack2

class Character (input_name: String,
                 input_initiative: Int,
                 input_myclass: String,
                 input_hitpoints: Int = 0,
                 input_armor_class: Int = 0){

    var name: String = input_name.capitalize()
    var initiative_modifier: Int = input_initiative
    var myclass: String = input_myclass
    var hitpoints: Int = input_hitpoints
    var armor_class: Int = input_armor_class

    var initiative: Int
    var temporary_hitpoints: Int

    init {
        initiative = 0
        temporary_hitpoints = 0
    }

    fun rollInitiative(){
        initiative = (1..20).random() + initiative_modifier
    }
}

class CharComparator: Comparator<Character>{
    override fun compare(o1: Character?, o2: Character?): Int{
        var x = when {
            (o1?.initiative == null|| o2?.initiative == null) -> 0
            (o1.initiative > o2.initiative) -> -1
            (o1.initiative < o2.initiative) -> 1
            else -> 0
        }
        println("In comparator and for o1 = ${o1?.name} and o2 = ${o2?.name} we return $x")
        return(x)
    }
}