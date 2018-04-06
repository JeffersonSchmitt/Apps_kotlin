package com.example.jeffersonschmitt.ktempconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var celciusRadio: RadioButton
    lateinit var fahrenheitRadio: RadioButton
    lateinit var converterButton: Button
    lateinit var tempConvertida: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.valorTemp) as EditText

        celciusRadio = findViewById(R.id.celciusRadio) as RadioButton

        fahrenheitRadio = findViewById(R.id.fahrenheitRadio) as RadioButton

        converterButton = findViewById(R.id.converterButton) as Button

        tempConvertida = findViewById(R.id.tempConvertida) as TextView

        converterButton.setOnClickListener { converter(it) }
    }

    fun converter(view: View) {
        var temp: Double? = editText.text.toString().toDouble()

        try {
            if (celciusRadio.isChecked) {
                if (temp != null && !temp.equals("")) {
                    temp = (temp - 32) * 5 / 9
                    tempConvertida.text = temp.toString() + " : Em Celcius"
                }
                else{
                    temp=0.0
                }

            } else if (fahrenheitRadio.isChecked) {
                if (temp != null && !temp.equals("")) {
                    temp = temp * 9 / 5 + 32
                    tempConvertida.text = temp.toString() + " : Em Fahrenheit"
                }else{
                    temp=0.0
                }

            }
        } catch (e: Exception) {
            e.printStackTrace();
            Toast.makeText(this, "SEM TEMPERATURA PARA CONVERTER", Toast.LENGTH_SHORT);
        }


    }
}
