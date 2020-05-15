package com.example.inittrack2.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.inittrack2.R
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation
import java.io.FileInputStream


class ExportBitmapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.inittrack2.R.layout.activity_export_bitmap)

        var bitmap : Bitmap? = null
        var filename: String = intent.getStringExtra("EXTRA_IMAGE_PATH")
        try {
            val istream: FileInputStream = openFileInput(filename)
            bitmap = BitmapFactory.decodeStream(istream)
            istream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        var mapImageView = findViewById<ImageView>(R.id.map_png)
        mapImageView.setImageBitmap(bitmap?.let { Bitmap.createScaledBitmap(it, 960, 960, false) })
//        holder.mImageView.setImageResource(id);
        //Glide.with(mContext).load(id).transform(SepiaFilterTransformation()).into(holder.mImageView)

//        Glide.with(applicationContext).load(bitmap).into(mapImageView)
    }
}
