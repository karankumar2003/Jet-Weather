package com.example.jetweather.data

import java.lang.Exception

class DataOrException<T,Boolean,Exception>(
    var data:T? = null,
    var isLoading: Boolean? = null,
    val exception: Exception? = null,
)