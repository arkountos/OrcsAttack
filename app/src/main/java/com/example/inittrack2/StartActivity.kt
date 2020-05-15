package com.example.inittrack2

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.view.*
import kotlin.system.exitProcess


class StartActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        val vibrator: Vibrator
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val initiativeButton: Button = findViewById(R.id.gotoInit)
        initiativeButton.setOnClickListener{
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
            openInitiativeActivity()
        }
        val generatorButton: Button = findViewById(R.id.gotoGenerator)
        generatorButton.setOnClickListener{
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
            openGeneratorActivity()
        }
        val settingsButton: Button = findViewById(R.id.gotoSettings)
        settingsButton.setOnClickListener {
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
            openSettingsActivity()
        }
        val aboutButton: Button = findViewById(R.id.gotoAbout)
        aboutButton.setOnClickListener {
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
            openAboutActivity()
        }
        val exitButton: Button = findViewById(R.id.gotoExit)
        exitButton.setOnClickListener {
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
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

    private fun openSettingsActivity(){
        val intent: Intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openAboutActivity(){
        val intent: Intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}