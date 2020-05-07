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
import androidx.core.content.getSystemService


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
    }

    private fun openInitiativeActivity(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openGeneratorActivity(){
        val intent: Intent = Intent(this, EncounterStartActivity::class.java)
        startActivity(intent)
    }
}