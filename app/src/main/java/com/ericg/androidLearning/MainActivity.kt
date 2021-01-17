package com.ericg.androidLearning

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ericg.androidLearning.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil as DB

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)

class MainActivity : AppCompatActivity() {

    private var uri: Uri? = null
    private var mainActivityBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = DB.setContentView(this, R.layout.activity_main)

        mainActivityBinding?.btnSelectImage?.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            this.type = "image/*"
        }
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 0 && data != null) {
            uri = data.data
            val bitMap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            mainActivityBinding!!.imageView.setImageBitmap(bitMap)
            mainActivityBinding!!.imageView.clipToOutline = true

        }
    }
}