package com.arkountos.orcsattack

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.arkountos.orcsattack.R

class BackgroundMusicService : Service() {

    lateinit var mp: MediaPlayer

    override fun onCreate() {
        mp = MediaPlayer.create(applicationContext, R.raw.orcsattackmusic)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        mp.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        mp.stop()
    }
}
