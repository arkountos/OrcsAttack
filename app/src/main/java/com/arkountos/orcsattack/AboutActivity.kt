package com.arkountos.orcsattack

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val about_text: TextView = findViewById(R.id.about_paragraph)
        about_text.movementMethod = LinkMovementMethod.getInstance()

        val about_attributions: TextView = findViewById(R.id.about_attribution)
        about_attributions.movementMethod = LinkMovementMethod.getInstance()

        var feedback_button: Button = findViewById(R.id.feedback_button)
        feedback_button.setOnClickListener {
            val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else{
                vibrator.vibrate(20);
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("fivosiliadis@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Orcs Attack!: ")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(intent, ""))
        }
    }
}
