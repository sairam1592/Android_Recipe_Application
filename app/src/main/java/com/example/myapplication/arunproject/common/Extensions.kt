package com.example.myapplication.arunproject.common

import com.example.myapplication.arunproject.common.AppConstants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String =
    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())

fun <T> T?.throwExceptionIfNull(message: String = ""): T {
    return this ?: throw java.lang.NullPointerException(message)
}