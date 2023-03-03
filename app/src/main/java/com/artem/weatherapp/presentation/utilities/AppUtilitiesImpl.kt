package com.artem.weatherapp.presentation.utilities


import android.content.Context
import android.util.Log
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class AppUtilitiesImpl @Inject constructor(private val context: Context) : AppUtilities {

    override fun validationInput(checkString: String): Boolean {
        val pattern: Pattern = Pattern.compile("[a-zA-Z]*")
        val matcher: Matcher = pattern.matcher(checkString.trim())
        val valid: Boolean = matcher.matches()

        Log.d("Debug","AppUtilitiesImp: valid - $valid")
        Log.d("Debug","AppUtilitiesImp: String - ${checkString.trim().isNotEmpty()}")
        return (checkString.trim().isNotEmpty() && valid)
    }

    override fun toastDisplayError(message: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, message, duration)
        toast.show()
    }
}