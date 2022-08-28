package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastnumeric: Boolean = false
    var lastdot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //The fucntion displays whatever button is pressed
    fun onDigit(view: android.view.View) {
        tvInput.append((view as Button).text)
        //stores the information if a digit is pressed
        lastnumeric = true


    }


    //the function clears the screen

    fun onClear(view: View) {
        tvInput.text = ""
        lastnumeric = false
        lastdot = false


    }

    //single dcl is allowed

    fun onDecimalPoint(view: View) {
        //check if the lastdigit was a numeric and not the last dot
        if (lastnumeric && !lastdot) {
            //if the digit was a numeric append the dot
            tvInput.append(".")
            //set to false since we have used the dot and
            lastnumeric = false
            //set to true since since the dot has been implemented
            lastdot = true

        }
    }

    //function to eliminate the zero
    private fun zeroRemove(result: String): String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length-2)
        }
        return  value
    }

    fun onOperator(view: View) {
        if (lastnumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastdot = false
            lastnumeric = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if (lastnumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""



            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = zeroRemove((one.toDouble() - two.toDouble()).toString())
                }

                if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = zeroRemove((one.toDouble() +two.toDouble()).toString())
                }

                if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = zeroRemove((one.toDouble() * two.toDouble()).toString())
                }

                if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = zeroRemove((one.toDouble() / two.toDouble()).toString())
                }

            }

            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

}









