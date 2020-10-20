package com.arkountos.orcsattack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MagicItemGenerator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magic_item_generator)

        var result = ""

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.open5e.com/magicitems/"

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            result += response.substring(0,500)
        },
            {
                result += "Nope!"
            })

        queue.add(stringRequest)
        Log.d("Reply", result)
    }
}