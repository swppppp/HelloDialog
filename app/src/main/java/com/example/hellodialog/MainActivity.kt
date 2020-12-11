package com.example.hellodialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_dlg)

        btn.setOnClickListener {

            AlertDialog.Builder(this)
                    .setMessage("message")
                    .setTitle("title")
                    .setPositiveButton("ok") { _, _ ->
                        Log.i("dialog", "ok")
                    }.setNegativeButton("no") { _, _ ->
                        Log.i("dialog", "cancel")
                    }.create()
                    .show()
        }

        findViewById<View>(R.id.btn_dlg2).setOnClickListener {
            MyAlertDialog.Builder(this)
                    .setMessage("message")
                    .setTitle("title")
                    .setPositiveButton("ok") { _, _ ->
                        Log.i("dialog", "ok")
                    }.setNegativeButton("no") { _, _ ->
                        Log.i("dialog", "cancel")
                    }.create()
                    .show()
        }
    }
}

class MyAlertDialog(context: Context) : AlertDialog(context) {

    class Builder(activity: AppCompatActivity) {
        fun setMessage(msg: String): Any {

        }

    }


}
