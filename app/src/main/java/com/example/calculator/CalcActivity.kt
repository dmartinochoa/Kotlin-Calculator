package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class CalcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val bundle = intent.extras

        val username = bundle?.getString("username");
        val txtHeader = findViewById<TextView>(R.id.txtHeader)
        txtHeader.append(username)
        var outputText = findViewById<TextView>(R.id.txtValues)
        outputText.setText("0")
    }

    fun numberClick(v: View) {
        var inputValue = (v as Button).text.trim().toString()
        var currentOut = findViewById<TextView>(R.id.txtValues)
        //To replace the default number 0
        if (currentOut.text != "0") {
            inputValue = currentOut.text as String + inputValue
            currentOut.setText(inputValue)
        } else {
            currentOut.setText(inputValue)
        }
    }

    fun operationClick(v: View) {
        var inputValue = (v as Button).text.trim().toString()
        var currentOut = findViewById<TextView>(R.id.txtValues)
        var tete = currentOut.text.last()
        //To replace any operator before adding another
        if (currentOut.text.last() == '+' || currentOut.text.last() == '-' || currentOut.text.last() == '/' || currentOut.text.last() == '*') {
            inputValue =
                currentOut.text.substring(0, currentOut.text.length - 1) as String + inputValue
            currentOut.setText(inputValue)
        } else {
            inputValue = currentOut.text as String + inputValue
            currentOut.setText(inputValue)
        }

        //To complete previous operation before adding a new one
        if (currentOut.text.substring(0, currentOut.length() - 1)
                .contains('+') || currentOut.text.substring(0, currentOut.length() - 1)
                .contains('-') || currentOut.text.substring(0, currentOut.length() - 1).contains(
                '/'
            ) || currentOut.text.substring(0, currentOut.length() - 1).contains('*')
        ) {
            currentOut.text = operate(currentOut.text.toString())
        }
    }

    fun operate(inputValue: String): String {
        var inputs = inputValue.split("-", "+", "/", "*")
        var operatorValue: Char
        operatorValue = inputValue.toCharArray()[inputs[0].length].toChar()
        Log.d("array", inputs.toString())
        var outputValue = 0.0
        Log.d("arasdaray", operatorValue.toString())

        when (operatorValue) {
            '+' -> {
                outputValue = inputs[0].toDouble() + inputs[1].toDouble()
            }
            '-' -> {
                Log.d("resdfsdfsult", "negatve")
                outputValue = inputs[0].toDouble() - inputs[1].toDouble()
            }
            '/' -> {
                outputValue = inputs[0].toDouble() / inputs[1].toDouble()
            }
            '*' -> {
                outputValue = inputs[0].toDouble() * inputs[1].toDouble()
            }
        }
        Log.d("result", outputValue.toString())

        return outputValue.toString()
    }
}

