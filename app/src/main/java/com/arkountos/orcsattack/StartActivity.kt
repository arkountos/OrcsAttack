package com.arkountos.orcsattack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class StartActivity : AppCompatActivity() {
    //    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val sharedPreferences : SharedPreferences = getSharedPreferences(GlobalsActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        val musicButton = findViewById<ImageView>(R.id.musicButton)

        if (sharedPreferences.getInt("music", 1) == 1) {
            musicButton.setImageResource(R.drawable.ic_baseline_music_note_24)
            var musicServiceIntent = Intent(applicationContext, BackgroundMusicService::class.java)
            startService(musicServiceIntent)
        }
        else{
            musicButton.setImageResource(R.drawable.ic_baseline_music_off_24)
        }

        musicButton.setOnClickListener{
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            if (sharedPreferences.getInt("music", 1) == null){
                editor.putInt("music", 0)
                editor.apply()
            }
            if (sharedPreferences.getInt("music", 1) == 1){
                musicButton.setImageResource(R.drawable.ic_baseline_music_off_24)
                stopService(Intent(applicationContext, BackgroundMusicService::class.java))
                editor.putInt("music", 0)
                editor.apply()
                var intent = Intent(this, BackgroundMusicService::class.java)
                stopService(intent)
            }
            else{
                musicButton.setImageResource(R.drawable.ic_baseline_music_note_24)
                startService(Intent(applicationContext, BackgroundMusicService::class.java))
                editor.putInt("music", 1)
                editor.apply()
                var intent = Intent(this, BackgroundMusicService::class.java)
                startService(intent)
            }

        }


        val initiativeButton: Button = findViewById(R.id.gotoInit)
        initiativeButton.setOnClickListener{
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
//            ?vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
            openInitiativeActivity()
        }
        val generatorButton: Button = findViewById(R.id.gotoGenerator)
        generatorButton.setOnClickListener{
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            openGeneratorActivity()
        }
        val NPCgeneratorButton: Button = findViewById(R.id.gotoNPCGeneratorButton)
        NPCgeneratorButton.setOnClickListener{
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            openNPCGeneratorActivity()
        }
        val settingsButton: Button = findViewById(R.id.gotoSettings)
        settingsButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            openSettingsActivity()
        }
        val aboutButton: Button = findViewById(R.id.gotoAbout)
        aboutButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            openAboutActivity()
        }
        val exitButton: Button = findViewById(R.id.gotoExit)
        exitButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            finish();
            exitProcess(0);
        }

    }

    private fun openInitiativeActivity(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openGeneratorActivity(){
        val intent: Intent = Intent(this, EncounterStartActivity::class.java)
        startActivity(intent)
    }

    private fun openNPCGeneratorActivity(){
        val intent: Intent = Intent(this, NPCGeneratorActivity::class.java)
        startActivity(intent)
    }

    private fun openSettingsActivity(){
        val intent: Intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openAboutActivity(){
        val intent: Intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}