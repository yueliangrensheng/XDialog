package com.yazao.dialog

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yazao.dialog.demo.R

class MainActivity : AppCompatActivity() {

    private val mTextView: TextView by lazy {
        Log.i("yazao", " --- lazy mTextView --- ")
        findViewById<TextView>(R.id.text_view)
    }

    private val xDialog: XDialog by lazy {
        Log.i("yazao", " --- lazy createDialog --- ")
        createDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        Log.i("yazao", " --- lazy showDialog --- ")
        when {
            xDialog.isShowing -> xDialog.hide()
            else -> xDialog.show()
        }
    }

    private fun createDialog(): XDialog {
        return XDialog.Builder(this)
            .setLayoutRes(R.layout.dialog_demo_layout)
            .setCancelable(true)
            .setCanceledOnTouchOutside(true)
            .setGravity(Gravity.BOTTOM)
            .setText(R.id.dialog_content, "tax")
            .setOnClickListener(
                R.id.btn_text,
                OnDialogClickListener { dialog, view ->
                    Toast.makeText(
                        this@MainActivity,
                        "click btn",
                        Toast.LENGTH_SHORT
                    ).show()

                    dialog.hide()
                })
            .show()
    }
}