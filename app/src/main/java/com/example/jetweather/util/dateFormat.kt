package com.example.jetweather.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.log

fun dateFormat(timeStamp:Int) : String{
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = Date(timeStamp.toLong() * 1000) // we have to give milliseconds
    return sdf.format(date)

}

fun timeFormat(timeStamp: Int) : String{
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}