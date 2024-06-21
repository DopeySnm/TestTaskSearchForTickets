package com.example.testtasksearchfortickets.utils

import com.example.testtasksearchfortickets.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    fun getData(year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        return Date(year, monthOfYear, dayOfMonth)
    }

    fun getCurrentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

    fun getStringIdDayOfWeek(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.set(date.year, date.month, date.day)
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
        return DayOfWeek.getByNumber(day).stringId
    }

    fun getDay(date: Date): String {
        val dateFormatter = SimpleDateFormat("dd", Locale("ru"))
        return dateFormatter.format(date)
    }
    fun getMonth(date: Date): String {
        val dateFormatter = SimpleDateFormat("MMMM", Locale("ru"))
        return dateFormatter.format(date)
    }

}

enum class DayOfWeek(val stringId: Int) {
    SUNDAY(R.string.sunday),
    MONDAY(R.string.monday),
    TUESDAY(R.string.tuesday),
    WEDNESDAY(R.string.wednesday),
    THURSDAY(R.string.thursday),
    FRIDAY(R.string.friday),
    SATURDAY(R.string.saturday);

    companion object {
        fun getByNumber(number: Int): DayOfWeek {
            entries.forEach {
                if (it.ordinal == number){
                    return it
                }
            }
            return SUNDAY
        }
    }
}
