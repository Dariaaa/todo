package k_de.com.app.util

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import java.text.DateFormat.getDateTimeInstance
import java.text.SimpleDateFormat
import java.util.*


class DateManager(private val context:Context?, private val date: EditText, private val time: EditText) {

    var dateAndTime = Calendar.getInstance()

    // установка обработчика выбора времени
    internal var t: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        dateAndTime.set(Calendar.MINUTE, minute)
        setTime()
    }

    // установка обработчика выбора даты
    internal var d: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        dateAndTime.set(Calendar.YEAR, year)
        dateAndTime.set(Calendar.MONTH, monthOfYear)
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        setDate()
    }

    fun setDate(v: View) {
        DatePickerDialog(context, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show()
    }

    fun setDateAndTime(v: View) {

        TimePickerDialog(context, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show()

        DatePickerDialog(context, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show()
    }

    fun setTime(v: View) {
        TimePickerDialog(context, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show()
    }

    private fun setTime(){
        time.setText(DateUtils.toSimpleStringOnlyTime(dateAndTime.time))
    }
    private fun setDate(){
        date.setText(DateUtils.toSimpleStringOnlyDate(dateAndTime.time))
    }
}
