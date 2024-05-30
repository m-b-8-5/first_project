package com.mb.myapplication.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class MyDatePicker: DialogFragment() {
    var onDateSetListener:(String) -> Unit = {}
    private val currentCal = Calendar.getInstance()
    companion object {
        const val DATE = "DATE"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val getDate = arguments?.getString(DATE)
        val year = getDate?.split("年")?.get(0)?.toInt() ?: currentCal.get(Calendar.YEAR)
        val month = getDate?.split("年")?.get(1)?.split("月")?.get(0)?.toInt() ?: currentCal.get(Calendar.MONTH)
        val day = getDate?.split("年")?.get(1)?.split("月")?.get(1)?.split("日")?.get(0)?.toInt() ?: currentCal.get(Calendar.DAY_OF_MONTH)
        currentCal.set(year, month, day)
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, calYear, calMonth, dayOfMonth ->
                onDateSetListener("$calYear" + "年" + "$calMonth" + "月" + "$dayOfMonth" + "日")
            },
            year,
            month,
            day
        )
        return datePickerDialog
    }
}