package com.mfdsix.astedroid.core.utils

import android.annotation.SuppressLint
import android.os.Build.VERSION
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


@SuppressLint("SimpleDateFormat")
fun String.withDateFormat(): String {
    try{
        val currDate = this.replace("Z", "")
        if(VERSION.SDK_INT >= 26){
            val localDateTime: LocalDateTime = LocalDateTime.parse(currDate)
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            return formatter.format(localDateTime)
        }else{
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            return formatter.format(parser.parse(currDate))
        }
    }catch(e: Throwable) {
        return "-"
    }
}

class Helpers{

    companion object{
        fun getNowDateTime(): String {
            return if(VERSION.SDK_INT >= 26){
                DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            }else{
                val s = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                s.format(Date())
            }
        }
    }
}