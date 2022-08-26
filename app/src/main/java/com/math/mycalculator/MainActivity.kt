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
        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show()
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
}