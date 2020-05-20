package com.arkountos.orcsattack.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.arkountos.orcsattack.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ExportBitmapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.arkountos.orcsattack.R.layout.activity_export_bitmap)

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
//        mapImageView.setImageBitmap(bitmap?.let { Bitmap.createScaledBitmap(it, mapImageView.width, mapImageView.height, false) })
//        holder.mImageView.setImageResource(id);
        //Glide.with(mContext).load(id).transform(SepiaFilterTransformation()).into(holder.mImageView)

        Glide.with(applicationContext).load(bitmap).override(600, 600).into(mapImageView)

        // Error filename not found
        Log.d("1", "1")
        if (bitmap != null) {
            SaveImage(bitmap)
        }

    }

    private fun SaveImage(finalBitmap: Bitmap) {
        Log.d("2", "2")

        val root = ContextCompat.getExternalFilesDirs(applicationContext, null).toString()
        Log.d("root path", root)
        val myDir = File("$root/saved_maps")
        myDir.mkdirs()
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val currentDateandTime: String = sdf.format(Date())
        var filename: String = currentDateandTime
        val fname = "Map-$filename.png"
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}
