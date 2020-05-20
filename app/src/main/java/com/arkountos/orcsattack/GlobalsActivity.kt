package com.arkountos.orcsattack

import android.app.Application

class GlobalsActivity : Application() {
    companion object{
        var global_styles = "Sepia"
        var global_toggle_background_colours_friend_enemy = "Off"
        val NOTICE_RADIUS = 3
        val CAMPFIRE_DISTANCE = 2
        val SHARED_PREFS : String = "sharedPrefs"
    }
    override fun onCreate() {
        super.onCreate()
        // initialization code here
    }
}