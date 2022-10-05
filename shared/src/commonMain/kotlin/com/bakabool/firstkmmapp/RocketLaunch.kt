package com.bakabool.firstkmmapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The RocketLaunch class is marked with the @Serializable annotation,
 * so that the kotlinx.serialization plugin can automatically generate a default serializer for it.
 */
@Serializable
data class RocketLaunch (
    /**
     * The @SerialName annotation allows you to redefine field names,
     * making it possible to declare properties in data classes with more readable names.
     */
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("success")
    val launchSuccess: Boolean?,
)
