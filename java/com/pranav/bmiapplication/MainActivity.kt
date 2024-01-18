package com.pranav.bmiapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            System.exit(0)
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
    lateinit var button: Button
    lateinit var height: EditText
    lateinit var weight: EditText
    lateinit var image:ImageView
    lateinit var age: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weight = findViewById<EditText>(R.id.editWeight);
        height = findViewById<EditText>(R.id.editHeight);
        button = findViewById(R.id.buttonbmi)
        age = findViewById(R.id.ageButton)
        image = findViewById(R.id.AboutImage)
        button.setOnClickListener(listener)
        image.setOnClickListener(listener2)
        age.setOnClickListener() {
            intent= Intent(this,AgeActivity::class.java)
            startActivity(intent)
        }
    }
    val listener2=View.OnClickListener {view ->
        when(view.getId()) {
            R.id.AboutImage -> {
                Toast.makeText(this, ";)", Toast.LENGTH_SHORT).show()
            }
        }
    }
    val listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.buttonbmi -> {
                try {
                    val weight_text = weight.getText().toString().toDouble();
                    val height_text = height.getText().toString().toDouble() * 0.01;
                    var bmi = weight_text / (height_text * height_text);
                    bmi = Math.round(bmi * 100.0) / 100.00
                    var message_display:String="hi";
                    var title_display:String="hi";
                    var color_display:Int=0;
                    if (bmi<=18.4) {
                        title_display="You are Underweight"
                        val final_gain=(18.5-bmi)*(height_text*height_text)
                        message_display="Your BMI is "+bmi+"\nGain "+Math.round(final_gain*100.00)/100.00+"kg and you are Normal!"
                        color_display=R.style.AlertDialogCustom1;
                    } else if (bmi>18.4 && bmi<=24.9) {
                        title_display="You are Normal"
                        val final_gain=(24.9-bmi)*(height_text*height_text)
                        message_display="Your BMI is "+bmi+"\nMaximum gain "+Math.round(final_gain*100.00)/100.00+"kg"
                        color_display=R.style.AlertDialogCustom2;
                    } else if (bmi>24.9 && bmi<=29.9) {
                        title_display="You are Overweight"
                        val final_gain=(bmi-24.9)*(height_text*height_text)
                        message_display="Your BMI is "+bmi+"\nLose "+Math.round(final_gain*100.00)/100.00+"kg and you are Normal!"
                        color_display=R.style.AlertDialogCustom3;
                    } else if (bmi>29.9 && bmi<=39.9) {
                        title_display="You are Obese"
                        val final_gain=(bmi-24.9)*(height_text*height_text)
                        message_display="Your BMI is "+bmi+"\nLose "+Math.round(final_gain*100.00)/100.00+"kg and you are Normal!"
                        color_display=R.style.AlertDialogCustom4;
                    } else {
                        title_display="You are Morbidly Obese"
                        val final_gain=(bmi-24.9)*(height_text*height_text)
                        message_display="Your BMI is "+bmi+"\nLose "+Math.round(final_gain*100.00)/100.00+"kg and you are Normal!"
                        color_display=R.style.AlertDialogCustom5;
                    }

                    val builder = AlertDialog.Builder(ContextThemeWrapper(this, color_display))
                    with(builder)
                    {
                        setTitle(title_display)
                        setMessage(message_display)
                        setPositiveButton("OK",null);
                        show()
                    }
    } catch (ex: Exception) {
                    val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.ErrorDialogue))
                    with(builder)
                    {
                        setTitle("Error")
                        setMessage("Check the values again!")
                        setPositiveButton("OK",null);
                        show()
                    }
                }
            }
        }
    }
}