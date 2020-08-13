package com.arkountos.orcsattack.debug

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.arkountos.orcsattack.R

class BackgroundMusicService : Service() {

    lateinit var mp: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
            TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        mp = MediaPlayer.create(applicationContext, R.raw.zoro)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        mp.start()
    }

    override fun onDestroy() {
        mp.stop()
    }
}
