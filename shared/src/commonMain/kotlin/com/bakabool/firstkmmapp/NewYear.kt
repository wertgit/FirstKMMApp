package com.bakabool.firstkmmapp

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

object NewYear {

    fun daysUntilNewYear(): Int {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val closestNewYear = LocalDate(today.year + 1, 1, 1)
        return today.daysUntil(closestNewYear)
    }

    fun daysUntilChristmas(): Int {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val closestChristmas = LocalDate(today.year, 12, 25)
        return today.daysUntil(closestChristmas)
    }
}