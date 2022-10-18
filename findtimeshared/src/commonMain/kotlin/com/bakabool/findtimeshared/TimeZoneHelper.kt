package com.bakabool.findtimeshared

/**
 * Creating an interface makes it easy to test
 * using an interface makes it easy to create mocked time zone helpers
 */
interface TimeZoneHelper {
    /**
     * Return a list of time zone strings. (This is a list of all time zones from the
    JetBrains kotlinx-datetime library)
     */
    fun getTimeZoneStrings(): List<String>

    /**
     * Return the current formatted time
     */
    fun currentTime(): String

    /**
     * Return the current time zone id
     */
    fun currentTimeZone(): String

    /**
     * Return the number of hours from the given time zone
     */
    fun hoursFromTimeZone(otherTimeZoneId: String): Double

    /**
     * Return the formatted time for the given time zone
     */
    fun getTime(timezoneId: String): String

    /**
     * Return the formatted date for the given time zone
     */
    fun getDate(timezoneId: String): String

    /**
     * Search for a list of hours that start at @param startHour  , end at @param endHour and are in
    all the given time zone strings
     */
    fun search(startHour: Int, endHour: Int, timezoneStrings: List<String>): List<Int>
}