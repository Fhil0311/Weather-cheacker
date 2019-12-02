package com.example.myapplication.Retrofit

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json



data class RetrofitWeatherData(

    @Json(name = "Headline")
    @SerializedName("Headline")
    val headline: Headline,

    @Json(name = "DailyForecastsData")
    @SerializedName("DailyForecasts")
    val dailyForecasts: DailyForecastsData


)

data class Headline(
    @Json(name = "EffectiveDate")
    @SerializedName("EffectiveDate")
    var effectiveData: String,

    @Json(name = "EffectiveEpochDate")
    @SerializedName("EffectiveEpochDate")
    var effectiveEpochDate: Long,

    @Json(name = "Severity")
    @SerializedName("Severity")
    var severity: Int,

    @Json(name = "Text")
    @SerializedName("Text")
    var text: String,

    @Json(name = "Category")
    @SerializedName("Category")
    var category: String,

    @Json(name = "EndDate")
    @SerializedName("" +
            "EndDate")
    var endDate: String,
    @Json(name = "EndEpochDate")
    @SerializedName("EndEpochDate")
    var endEpochDate: Long,

    @Json(name = "MobileLink")
    @SerializedName("MobileLink")
    var mobileLink: String,

    @Json(name = "Link")
    @SerializedName("Link")
    var link: String

)

data class DailyForecastsData(
    @Json(name = "Date")
    @SerializedName("Date")
    var date: String,

    @Json(name = "EpochDate")
    @SerializedName("EpochDate")
    var epochDate: Long,

    @Json(name = "Temperature")
    @SerializedName("Temperature")
    var temperature: Temperature,

    @Json(name = "Day")
    @SerializedName("Day")
    var day: Day,

    @Json(name = "Night")
    @SerializedName("Night")
    var night: Night,

    @Json(name = "Sources")
    @SerializedName("Sources")
    var sources: Sources,

    @Json(name = "MobileLink")
    @SerializedName("MobileLink")
    var mobileLink: String,

    @Json(name = "Link")
    @SerializedName("Link")
    var link: String

)

data class Temperature(
    @Json(name = "Minimum")
    @SerializedName("Minimum")
    var minimum: Minimum,

    @Json(name = "Maximum")
    @SerializedName("Maximum")
    var maximum: Maximum

)

data class Minimum(
    @Json(name = "Value")
    @SerializedName("Value")
    var Value: Double,

    @Json(name = "Unit")
    @SerializedName("Unit")
    var Unit: String,

    @Json(name = "UnitType")
    @SerializedName("UnitType")
    var UnitType: Int
)

data class Maximum(
    @Json(name = "Value")
    @SerializedName("Value")
    var Value: Double,

    @Json(name = "Unit")
    @SerializedName("Unit")
    var Unit: String,

    @Json(name = "UnitType")
    @SerializedName("UnitType")
    var UnitType: Int
)

data class Day(
    @Json(name = "Icon")
    @SerializedName("Icon")
    var icon: Int,

    @Json(name = "IconPhrase")
    @SerializedName("IconPhrase")
    var iconPhrase: String,

    @Json(name = "HasPrecipitation")
    @SerializedName("HasPrecipitation")
    var hasPrecipitation: Boolean,

    @Json(name = "PrecipitationType")
    @SerializedName("PrecipitationType")
    var precipitationType: String,

    @Json(name = "PrecipitationIntensity")
    @SerializedName("PrecipitationIntensity")
    var precipitationIntensity: String

)

data class Night(
    @Json(name = "Icon")
    @SerializedName("Icon")
    var icon: Int,

    @Json(name = "IconPhrase")
    @SerializedName("IconPhrase")
    var iconPhrase: String,

    @Json(name = "HasPrecipitation")
    @SerializedName("HasPrecipitation")
    var hasPrecipitation: Boolean

)

data class Sources(
    @Json(name = "AccuWeather")
    @SerializedName("AccuWeather")
    var res: String

)