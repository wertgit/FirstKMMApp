package com.bakabool.findtimeshared

import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class TimeZoneHelperImpl : TimeZoneHelper {

    override fun getTimeZoneStrings(): List<String> {
        // returns the available time zone IDs and sorts them
        return TimeZone.availableZoneIds.sorted()
    }

    override fun currentTime(): String {
        // Get the current time as an Instant.
        val currentMoment: Instant = Clock.System.now()

        /**
         * Convert the current moment into a LocalDateTime that’s based on the
        current user’s time zone.
         */
        val dateTime: LocalDateTime =
            currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

        // Format the given date using the formatDateTime method and return it
        return formatDateTime(dateTime)
    }

    override fun currentTimeZone(): String {
        val currentTimeZone = TimeZone.currentSystemDefault()
        return currentTimeZone.toString()
    }

    override fun hoursFromTimeZone(otherTimeZoneId: String): Double {
        /**
         *  return the number
        of hours from the given time zone
         */
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentUTCInstant: Instant = Clock.System.now()
        val otherTimeZone = TimeZone.of(otherTimeZoneId)
        val currentDateTime: LocalDateTime =
            currentUTCInstant.toLocalDateTime(currentTimeZone)
        val currentOtherDateTime: LocalDateTime =
            currentUTCInstant.toLocalDateTime(otherTimeZone)
        /**
         * Return the absolute difference between the hours (shouldn’t be negative),
        making sure the result is a double
         */
        return abs(
            (currentDateTime.hour - currentOtherDateTime.hour) *
                1.0
        )
    }

    override fun getTime(timezoneId: String): String {
        // Get the time zone with the given ID
        val timezone = TimeZone.of(timezoneId)
        // Get the current time as an Instant
        val currentMoment: Instant = Clock.System.now()

        // Convert the current moment into a LocalDateTime that’s based on the
        //passed-in time zone
        val dateTime: LocalDateTime =
            currentMoment.toLocalDateTime(timezone)

        // Format the given date
        return formatDateTime(dateTime)
    }

    override fun getDate(timezoneId: String): String {
        /**
         * This takes the different parts of the DateTime to create a string like: “Monday,
        October 4.”
         */
        val timezone = TimeZone.of(timezoneId)
        val currentMoment: Instant = Clock.System.now()
        val dateTime: LocalDateTime =
            currentMoment.toLocalDateTime(timezone)
        // 1
        return "${
            dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        }, " +
            "${
                dateTime.month.name.lowercase().replaceFirstChar {
                    it.uppercase()
                }
            } ${dateTime.date.dayOfMonth}"
    }

    /**
     * Given a starting hour (like 8 a.m.), an ending hour (say
    5 p.m.) and the list of time zones that everyone is in, you want to return a list of
    integers that represent the hours (0-23) that fit in everyone’s time zones. So, if
    you pass in 8 a.m. - 5 p.m. for Los Angeles and New York, you would get a list of
    hours:
     */
    override fun search(startHour: Int, endHour: Int, timezoneStrings: List<String>): List<Int> {
        val goodHours = mutableListOf<Int>()
        val timeRange = IntRange(max(0, startHour), min(23, endHour))
        val currentTimeZone = TimeZone.currentSystemDefault()

       // Go through each hour in the time range
        for (hour in timeRange) {
            var isGoodHour = false
            // Go through each time zone in the time zone list
            for (zone in timezoneStrings) {
                val timezone = TimeZone.of(zone)
                // If it’s the same time zone as the current one, then you know it’s good
                if (timezone == currentTimeZone) {
                    continue
                }
                // Check if the hour is valid.
                if (!isValid(
                        timeRange = timeRange,
                        hour = hour,
                        currentTimeZone = currentTimeZone,
                        otherTimeZone = timezone
                    )
                ) {
                    Napier.d("Hour $hour is not valid for time range")
                    isGoodHour = false
                    break
                } else {
                    Napier.d("Hour $hour is Valid for time range")
                    isGoodHour = true
                }
            }
            // If, after going through every hour and it’s a good hour, add it to our list
            if (isGoodHour) {
                goodHours.add(hour)
            }
        }
        return goodHours
    }

    private fun formatDateTime(dateTime: LocalDateTime): String {
        // Use a StringBuilder to build the string piece by piece
        val stringBuilder = StringBuilder()
        // Get the hour and minutes from the dateTime argument
        var hour = dateTime.hour
        val minute = dateTime.minute
        var amPm = " am"

        // Since you want a string with am/pm, check if the hour is greater than noon
        //(12)
        if (hour > 12) {
            amPm = " pm"
            hour -= 12
        }

        // Build the hour and colon
        stringBuilder.append(hour.toString())
        stringBuilder.append(":")

        // Check to make sure numbers 0-9 are padded
        if (minute < 10) {
            stringBuilder.append('0')
        }
        stringBuilder.append(minute.toString())
        stringBuilder.append(amPm)

        // Return the final string
        return stringBuilder.toString()
    }

    /**
     * This method takes a time range (like 8..17), the given hour to check, the current
    time zone for the user and the other time zone that you’re checking against.
    The first check verifies if the hour is in the time range. If not, it isn’t valid.
     */
    private fun isValid(
        timeRange: IntRange,
        hour: Int,
        currentTimeZone: TimeZone,
        otherTimeZone: TimeZone
    ): Boolean {
        if (hour !in timeRange) {
            return false
        }
        val currentUTCInstant: Instant = Clock.System.now()

        val currentOtherDateTime: LocalDateTime =
            currentUTCInstant.toLocalDateTime(otherTimeZone)

        val otherDateTimeWithHour = LocalDateTime(
            currentOtherDateTime.year,
            currentOtherDateTime.monthNumber,
            currentOtherDateTime.dayOfMonth,
            hour,
            0,
            0,
            0
        )

        val localInstant = otherDateTimeWithHour.toInstant(currentTimeZone)

        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)
        Napier.d("Hour $hour in Time Range ${otherTimeZone.id} is ${convertedTime.hour}")

        return convertedTime.hour in timeRange

    }

}