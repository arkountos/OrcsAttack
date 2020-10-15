package com.arkountos.orcsattack

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_scroll_presentation.*


class ScrollPresentationActivity : AppCompatActivity() {

    private var STORAGE_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_presentation)

        var blackchancery: Typeface? = ResourcesCompat.getFont(this, R.font.blackchancery)
        var bookinsanity: Typeface? = ResourcesCompat.getFont(this, R.font.bookinsanity)
        var nodestocapscondensed: Typeface? = ResourcesCompat.getFont(
            this,
            R.font.nodestocapscondensed
        )
        var scalysans: Typeface? = ResourcesCompat.getFont(this, R.font.scalysans)
        var solberaimitation: Typeface? = ResourcesCompat.getFont(this, R.font.solberaimitation)
        var zatannamisdirection: Typeface? = ResourcesCompat.getFont(
            this,
            R.font.zatannamisdirection
        )

        var fontnametouri: Map<String, Typeface> = mapOf(
            "Black Chancery" to blackchancery!!,
            "Book Insanity" to bookinsanity!!,
            "Nodesto Caps Condensed" to nodestocapscondensed!!,
            "Scaly Sans" to scalysans!!,
            "Solbera Imitation" to solberaimitation!!,
            "Zatanna Misdirection" to zatannamisdirection!!
        )

        var font_result = intent.getStringExtra("EXTRA_FONT")
        var font_size_result = intent.getStringExtra("EXTRA_SIZE")
        var text_result = intent.getStringExtra("EXTRA_TEXT")

        Log.d("Target activity:", "$font_result  $font_size_result  $text_result")

        var note = findViewById<TextView>(R.id.note)
        note.text = text_result.toString()
        note.textSize = font_size_result.toFloat()
        note.typeface = fontnametouri[font_result]

        var export_button = findViewById<Button>(R.id.scroll_presentation_export)
        export_button.setOnClickListener {

            checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)
            share_image(note_constraint_layout)

//            var fileName: String = ""
//
//            val alertDialog = Dialog(this)
//            var inflater = LayoutInflater.from(this)
//            val view: View = inflater.inflate(R.layout.npc_save_path_dialog, null)
//            val pathEdittext = view.findViewById<EditText>(R.id.npc_save_path_dialog_edittext)
//            val okButton = view.findViewById<Button>(R.id.npc_save_path_dialog_ok_button)
//            okButton.setOnClickListener {
//                Log.d("Btn", "Clicked!" + "")
//                fileName = pathEdittext.text.toString()
//                // generate the image path
//                val imagePath: String = Environment.getExternalStorageDirectory()
//                    .toString() + File.separator.toString() + fileName + ".png"
//
//                try {
//
//                    // save the image as png
//                    val out = FileOutputStream(imagePath)
//                    // compress the image to png and pass it to the output stream
//
//                    loadBitmapFromView(note_constraint_layout)!!.compress(
//                        Bitmap.CompressFormat.PNG,
//                        90,
//                        out
//                    )
//
//                    // save the image
//                    out.flush()
//                    out.close()
//
//                    Toast.makeText(this, "Saved as $imagePath", Toast.LENGTH_LONG).show()
//                } catch (error: Exception) {
//                    Log.e("Error saving image", error.message)
//                }
//                alertDialog.dismiss()
//            }
//            val cancelButton = view.findViewById<Button>(R.id.npc_save_path_dialog_cancel_button)
//            cancelButton.setOnClickListener {
//                Log.d("Btn", "Clicked Cancel!")
//                alertDialog.dismiss()
//            }
//            alertDialog.setContentView(view)
//            alertDialog.show()
        }
    }

    fun loadBitmapFromView(view: View): Bitmap? {

        // width measure spec
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            view.measuredWidth, View.MeasureSpec.EXACTLY
        )
        // height measure spec
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            view.measuredHeight, View.MeasureSpec.EXACTLY
        )
        // measure the view
        view.measure(widthSpec, heightSpec)
        // set the layout sizes
        view.layout(
            view.left,
            view.top,
            view.measuredWidth + view.left,
            view.measuredHeight + view.top
        )
        // create the bitmap
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        // create a canvas used to get the view's image and draw it on the bitmap
        val c = Canvas(bitmap)
        // position the image inside the canvas
        c.translate((-view.scrollX).toFloat(), (-view.scrollY).toFloat())
        // get the canvas
        view.draw(c)
        return bitmap
    }


    // Function to check and request permission
    private fun checkPermission(permission: String, requestCode: Int) {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        }
        else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }


    // As seen on https://stackoverflow.com/questions/7661875/how-to-use-share-image-using-sharing-intent-to-share-images-in-android
    fun share_image(view: View) {
        /* ACTION_SEND: Deliver some data to someone else.
        createChooser (Intent target, CharSequence title): Here, target- The Intent that the user will be selecting an activity to perform.
            title- Optional title that will be displayed in the chooser.
        Intent.EXTRA_TEXT: A constant CharSequence that is associated with the Intent, used with ACTION_SEND to supply the literal data to be sent.
        */

        val mBitmap: Bitmap = loadBitmapFromView(note_constraint_layout)!!

        val path: String =
            MediaStore.Images.Media.insertImage(contentResolver, mBitmap, "Image Description", null)
        val uri = Uri.parse(path)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(intent, "Share Image"))

    }
}