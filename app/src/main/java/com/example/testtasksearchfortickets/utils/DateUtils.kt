package com.example.testtasksearchfortickets.utils

import com.example.testtasksearchfortickets.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    private const val DATE_MASK = "yyyy-MM-dd'T'HH:mm:ss"

    fun getData(year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        return Date(year, monthOfYear, dayOfMonth)
    }

    fun getCurrentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

    fun getTime(date: String): String {
        val dateFormatInput = SimpleDateFormat(DATE_MASK, Locale("ru"))
        val resultFormat = SimpleDateFormat("HH:mm", Locale("ru"))
        return resultFormat.format(dateFormatInput.parse(date))
    }

    fun getFlightTime(departureDate: String, arrivalDate: String): String {
        val dateFormatInput = SimpleDateFormat(DATE_MASK, Locale("ru"))
        val departure = dateFormatInput.parse(departureDate)
        val arrival = dateFormatInput.parse(arrivalDate)
        val differenceInTime: Long = arrival.time - departure.time
        val differenceInHours: Long = ((differenceInTime
                / (1000 * 60 * 60))
                % 24)
        val differenceInMinutes = ((differenceInTime
                / (1000 * 60))
                % 60)
        val totalTimeInHours: Double = differenceInHours + (differenceInMinutes / 60.0)
        return String.format("%.1f", totalTimeInHours)
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
