package com.vitorscheffer.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConverter = findViewById<Button>(R.id.btn_converter)

        result = findViewById<TextView>(R.id.txt_result)

        btnConverter.setOnClickListener {
            result.visibility = View.VISIBLE
            result.text = "Novo Texto"
        }

    }
}