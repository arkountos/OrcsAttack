package com.example.application.example

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.app.AlertDialog
//import android.app.AppCompatDialogFragment
import androidx.appcompat.app.AppCompatDialogFragment

class HitpointsDialog : AppCompatDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("Information")
            .setMessage("This is a Dialog")
            .setPositiveButton("ok",
                DialogInterface.OnClickListener { dialogInterface, i -> })
        return builder.create()
    }
}