package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val bundle = intent.extras
        val username = bundle?.getString("username")
        val txtHeader = findViewById<TextView>(R.id.txtHeader)
        txtHeader.append(username)
        val outputText = findViewById<TextView>(R.id.txtValues)
        outputText.text = "0"

    }

    fun numberClick(v: View) {
        var inputValue = (v as Button).text.toString()
        val currentOut = findViewById<TextView>(R.id.txtValues)
        //To replace the default number 0
        if (currentOut.text != "0") {
            inputValue = currentOut.text as String + inputValue
            currentOut.text = inputValue
        } else {
            currentOut.text = inputValue
        }
    }

    @SuppressLint("SetTextI18n")
    fun decimalClick(v: View) {
        val currentOut = findViewById<TextView>(R.id.txtValues)

        if ((validDecimal(currentOut.text as String))) {
            if (currentOut.text.last().isDigit()) {
                currentOut.text = currentOut.text as String + "."
            } else if ((currentOut.text.last() == '+' || currentOut.text.last() == '-' || currentOut.text.last() == '/' || currentOut.text.last() == '*')) {
                currentOut.text = currentOut.text as String + "0."
            }
        } else {
            Toast.makeText(this, "Invalid Decimal", Toast.LENGTH_SHORT).show()
        }
    }

    fun clearClick(v: View) {
        val currentOut = findViewById<TextView>(R.id.txtValues)
        currentOut.text = "0"
        Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
    }

    fun equalClick(v: View) {
        val currentOut = findViewById<TextView>(R.id.txtValues)
        currentOut.text = operate(currentOut.text as String)
    }

    fun operationClick(v: View) {
        var inputValue = (v as Button).text.trim().toString()
        val currentOut = findViewById<TextView>(R.id.txtValues)

        //To replace any operator before adding another
        if (currentOut.text.last() == '+' || currentOut.text.last() == '-' || currentOut.text.last() == '/' || currentOut.text.last() == '*') {
            inputValue =
                currentOut.text.substring(0, currentOut.text.length - 1) + inputValue
            currentOut.text = inputValue
        } else {
            inputValue = currentOut.text as String + inputValue
            currentOut.text = inputValue
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

    private fun operate(inputValue: String): String {
        val inputs = inputValue.split("-", "+", "/", "*")
        try {

            val operatorValue: Char = inputValue.toCharArray()[inputs[0].length]
            var outputValue = 0.0

            when (operatorValue) {
                '+' -> {
                    outputValue = inputs[0].toDouble() + inputs[1].toDouble()
                }
                '-' -> {
                    outputValue = inputs[0].toDouble() - inputs[1].toDouble()
                }
                '/' -> {
                    outputValue = inputs[0].toDouble() / inputs[1].toDouble()
                }
                '*' -> {
                    outputValue = inputs[0].toDouble() * inputs[1].toDouble()
                }
            }
            return outputValue.toBigDecimal().toString()
        } catch (ex: Exception) {
            Toast.makeText(this, "Operation Error", Toast.LENGTH_SHORT).show()
            return inputValue
        }
    }

    private fun validDecimal(currentOut: String): Boolean {
        var valid = true
        val outArray = currentOut.split("-", "+", "/", "*")
        for (value in outArray) {
            valid = !value.contains(".")
        }
        return valid
    }

}




















