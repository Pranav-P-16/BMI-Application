package com.pranav.bmiapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate;
import java.time.Period

class AgeActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var datePicker: DatePicker
    lateinit var result: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)

        datePicker=findViewById(R.id.datePicker1)
        button=findViewById(R.id.buttonage)
        result=findViewById(R.id.resultView)

        button.setOnClickListener() {
            val year=datePicker.year;
            val month=datePicker.month;
            val day=datePicker.getDayOfMonth()+1;
            var c=getAge(year,month,day)
            if (c<0) {
                c=0;
            }
            result.text = "You're "+c.toString()+" years Old";
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).years
}