package k_de.com.app.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils{
    var formatDateTime = SimpleDateFormat("dd.MM.yyyy HH:mm")
    var formatDate = SimpleDateFormat("dd.MM.yyyy")
    var formatTime = SimpleDateFormat("HH:mm")


    @JvmStatic
    fun toDate(date: String) : Date{
        var result = Date()
        try{
            result = formatDateTime.parse(date)

        }catch (e:Exception){
            result = formatDate.parse(date)
        }
        return result
    }

    @JvmStatic
    fun toSimpleString(date: Date) : String {
        return formatDateTime.format(date)
    }
    @JvmStatic
    fun toSimpleStringOnlyDate(date: Date) : String {
        return formatDate.format(date)
    }
    @JvmStatic
    fun toSimpleStringOnlyTime(date: Date) : String {
        return formatTime.format(date)
    }
}