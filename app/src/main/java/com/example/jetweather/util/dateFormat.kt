package com.example.jetweather.util

import java.text.SimpleDateFormat
import java.util.Date

fun dateFormat(timeStamp:Int) : String{
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = Date(timeStamp.toLong() * 1000) // we have to give milliseconds
    return sdf.format(date)

}

fun timeFormat(timeStamp: Int) : String{
    val sdf = SimpleDateFormat("hh:mm aa")
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}
fun dayFormat(timeStamp: Int) : String{
    val sdf = SimpleDateFormat("EEE")
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}


fun convertDecimal(number : String):String{
   val a =  number.toFloat() /1000 // convert into km
    val formattedString = "%.1f".format(a)
    return formattedString
}