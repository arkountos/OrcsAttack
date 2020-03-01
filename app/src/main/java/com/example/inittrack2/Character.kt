package com.example.inittrack2

class Character (input_name: String, input_initiative: Int){
    var name: String
    var initiative_modifier: Int
    var initiative: Int
    //private var icon : ??? (maybe String as bitmap)
    init {
        name = input_name
        initiative_modifier = input_initiative
        initiative = 0
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