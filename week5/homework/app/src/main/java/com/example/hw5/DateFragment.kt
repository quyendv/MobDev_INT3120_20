package com.example.hw5

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var pickDateButton: MaterialButton
    private lateinit var datePicker: DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.date_layout, container, false)

        calendarView = view.findViewById(R.id.calendar)
        pickDateButton = view.findViewById(R.id.pick)

        pickDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create DatePickerDialog instance and show
            datePicker = DatePickerDialog(
                requireContext(),
                { _, selectedYear, monthOfYear, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(selectedYear, monthOfYear, dayOfMonth)

                    // Update CalendarView
                    calendarView.date = selectedDate.timeInMillis

                    // format
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = sdf.format(selectedDate.time)
                    pickDateButton.text = formattedDate
                },
                year, month, day
            )
            datePicker.show()
        }
        return view
    }
}

