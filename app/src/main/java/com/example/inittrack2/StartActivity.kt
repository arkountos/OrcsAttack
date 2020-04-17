package com.example.inittrack2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val initiativeButton: Button = findViewById(R.id.gotoInit)
        initiativeButton.setOnClickListener{
            openInitiativeActivity()
        }
        val generatorButton: Button = findViewById(R.id.gotoGenerator)
        generatorButton.setOnClickListener{
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