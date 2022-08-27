package com.math.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

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
        if (lastDigitIsNumeric
            and lastDigitIsDot.not()
            and allowOneMoreDot()
        ) {
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

    fun onEqual(view: View) {
        if (lastDigitIsNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    val result = one.toDouble() - two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("+")) {

                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    val result = one.toDouble() + two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("/")) {

                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    val result = one.toDouble() / two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("*")) {

                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    val result = one.toDouble() * two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }


            } catch (ex: ArithmeticException) {
                ex.printStackTrace()
            }
        }
    }

    private fun allowOneMoreDot(): Boolean {
        if (tvInput?.text.isNullOrEmpty())
            return false

        var tvValue = tvInput?.text.toString()

        if (tvValue.startsWith("-")) {
            tvValue = tvValue.substring(1)
        }
        val numbers = tvValue.split("-", "+", "*", "/")
        return when (numbers.size) {
            1 -> numbers[0].contains(".").not()
            2 -> numbers[1].contains(".").not()
            else -> true
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)

        return value
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