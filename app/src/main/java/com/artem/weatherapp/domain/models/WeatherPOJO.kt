package com.artem.weatherapp.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeatherPOJO : Serializable {

    @SerializedName("sys")
    var sys: Sys? = null

    @SerializedName("main")
    var main: Main? = null

    @SerializedName("wind")
    var wind: Wind? = null

    @SerializedName("timezone")
    var timezone:Int = 0

    @SerializedName("name")
    var name:String = "null"

    @SerializedName("weather")
    var weather: List<Weather>? = null

    @SerializedName("dt")
    var dt:Int = 0
}

class Main:Serializable {

    @SerializedName("temp")
    var temp: Float = 0.0f
    @SerializedName("humidity")
    var humidity: Int = 0
    @SerializedName("pressure")
    var pressure: Int = 0
    @SerializedName("temp_min")
    var temp_min: Float = 0.0f
    @SerializedName("temp_max")
    var temp_max: Float = 0.0f
    @SerializedName("feels_like")
    var feels_like:Float = 0.0f


}

class Sys:Serializable {
    @SerializedName("country")
    var country: String? = null
}

class Wind:Serializable{
    @SerializedName("speed")
    var speed:Float = 0.0f
    @SerializedName("deg")
    var deg:Float = 0.0f
}

class Weather:Serializable{
    @SerializedName("id")
    var id:Int = 0
    @SerializedName("main")
    var main:String = "null"
    @SerializedName("description")
    var description:String = "null"
    @SerializedName("icon")
    var icon:String = "null"
}
