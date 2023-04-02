package com.vitorscheffer.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConverter = findViewById<Button>(R.id.btn_converter)

        result = findViewById<TextView>(R.id.txt_result)

        btnConverter.setOnClickListener {
            converter()
        }
    }

    private fun converter() {
        val selectedCurrency = findViewById<RadioGroup>(R.id.radioGroup)

        val checked = selectedCurrency.checkedRadioButtonId

        val currency = when(checked) {
            R.id.radio_usd -> "USD"
            R.id.radio_eur -> "EUR"
            else           -> "CLP"
        }

        val editField = findViewById<EditText>(R.id.edit_field)

        val value = editField.text

        if (value.isEmpty()) return

        result.visibility = View.VISIBLE
        result.text = currency + value
    }
}