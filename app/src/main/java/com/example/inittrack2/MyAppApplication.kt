package com.example.inittrack2

import android.app.Application

class MyAppApplication : Application() {
    private var map_style : String = "Standard"

    fun setMapStyle(new_style: String){
        map_style = new_style
    }

    fun getMapStyle() : String{
        return map_style
    }
}