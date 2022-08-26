package com.math.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastDigitIsNumeric: Boolean = false
    private var lastDigitIsDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastDigitIsNumeric = true
        lastDigitIsDot = false


    }

    fun onCLear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastDigitIsNumeric and lastDigitIsDot.not()) {
            tvInput?.append(".")
            lastDigitIsDot = true
            lastDigitIsNumeric = false
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastDigitIsNumeric and isOperatorAdded(it.toString()).not()) {
                tvInput?.append((view as Button).text)
                lastDigitIsDot = false
                lastDigitIsNumeric = false
            }
        }
    }


    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") or
                    value.contains("*") or
                    value.contains("+") or
                    value.contains("-")
        }
    }
}