package com.vitorscheffer.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private val apiKey = "vz3Ot94LMiJTQdK8CpyISQGy7oGfk8Rh"
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

        Thread {
            val url = URL("https://api.apilayer.com/exchangerates_data/convert?to=${currency}&from=BRL&amount=${value}")
            val conn = url.openConnection() as HttpsURLConnection

            conn.requestMethod = "GET"
            conn.setRequestProperty("apikey", apiKey)

            try {
                val data = conn.inputStream.bufferedReader().readText()
                val obj = JSONObject(data)

                runOnUiThread {
                    val res = obj.getDouble("result")

                    result.visibility = View.VISIBLE
                    result.text = "R$ ${String.format("%.2f", res)}"
                }
            } finally {
                conn.disconnect()
            }

        }.start()
    }

}