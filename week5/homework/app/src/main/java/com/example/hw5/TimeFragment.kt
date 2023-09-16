package com.example.hw5

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimeFragment : Fragment() {

    private lateinit var textClock: TextClock
    private lateinit var pickTimeButton: MaterialButton
    private lateinit var timePicker: TimePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.time_layout, container, false)

        textClock = view.findViewById(R.id.textClock)
        pickTimeButton = view.findViewById(R.id.pick)

        // Khi người dùng nhấn nút "Pick Time"
        pickTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Create TimePickerDialog instance and show
            timePicker = TimePickerDialog(
                requireContext(),
                { _, selectedHour, selectedMinute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                    selectedTime.set(Calendar.MINUTE, selectedMinute)

                    // Format
                    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
                    val formattedTime = sdf.format(selectedTime.time)
                    textClock.text = formattedTime // update TextClock
                    pickTimeButton.text = formattedTime

                },
                hour, minute, false // false: 12h, true: 24h
            )
            timePicker.show()
        }
        return view
    }
}