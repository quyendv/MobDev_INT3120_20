package com.example.hw4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var cheeseRadioGroup: RadioGroup
    private lateinit var shapeRadioGroup: RadioGroup
    private lateinit var pepperoniCheckBox: CheckBox
    private lateinit var mushroomCheckBox: CheckBox
    private lateinit var veggiesCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setContentView(R.layout.linear_layout)
//        setContentView(R.layout.relative_layout)
//        setContentView(R.layout.table_layout)
        setContentView(R.layout.constraint_layout)

        resultTextView = findViewById(R.id.result)
        nameEditText = findViewById(R.id.name)
        phoneEditText = findViewById(R.id.phone)
        cheeseRadioGroup = findViewById(R.id.cheese)
        shapeRadioGroup = findViewById(R.id.shape)
        pepperoniCheckBox = findViewById(R.id.pepperoni)
        mushroomCheckBox = findViewById(R.id.mushroom)
        veggiesCheckBox = findViewById(R.id.veggies)

        val placeOrderButton: Button = findViewById(R.id.sms_btn)
        val exitButton: Button = findViewById(R.id.exit_btn)

        placeOrderButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val selectedCheese = when (cheeseRadioGroup.checkedRadioButtonId) {
                R.id.radio_cheese1 -> "Single"
                R.id.radio_cheese2 -> "Double"
                R.id.radio_cheese3 -> "None"
                else -> "None"
            }
            val selectedShape = when (shapeRadioGroup.checkedRadioButtonId) {
                R.id.square -> "Square"
                R.id.round -> "Round"
                else -> "None"
            }
            val selectedToppings = mutableListOf<String>()
            if (pepperoniCheckBox.isChecked) selectedToppings.add("Pepperoni")
            if (mushroomCheckBox.isChecked) selectedToppings.add("Mushroom")
            if (veggiesCheckBox.isChecked) selectedToppings.add("Veggies")

            val result =
                "$name - $phone \n\nCheese: $selectedCheese \n\nShape: $selectedShape \n\nToppings: ${
                    selectedToppings.joinToString(", ")
                }"

            resultTextView.text = result
        }

        exitButton.setOnClickListener {
            finish() // Đóng ứng dụng
        }
    }
}