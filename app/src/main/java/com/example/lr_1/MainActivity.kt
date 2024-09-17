package com.example.lr_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultText: TextView = findViewById(R.id.resultText)

        val hydrogenInput: EditText = findViewById(R.id.hydrogenInput)
        val carbonInput: EditText = findViewById(R.id.carbonInput)
        val sulfurInput: EditText = findViewById(R.id.sulfurInput)
        val nitrogenInput: EditText = findViewById(R.id.nitrogenInput)
        val oxygenInput: EditText = findViewById(R.id.oxygenInput)
        val moistureInput: EditText = findViewById(R.id.moistureInput)
        val ashInput: EditText = findViewById(R.id.ashInput)

        val calcButton: Button = findViewById(R.id.calcButton)
        val toTask2Button: Button = findViewById(R.id.toTask2)

        calcButton.setOnClickListener {
            val hydrogen = hydrogenInput.text.toString().toFloatOrNull() ?: 0f
            val carbon = carbonInput.text.toString().toFloatOrNull() ?: 0f
            val sulfur = sulfurInput.text.toString().toFloatOrNull() ?: 0f
            val nitrogen = nitrogenInput.text.toString().toFloatOrNull() ?: 0f
            val oxygen = oxygenInput.text.toString().toFloatOrNull() ?: 0f
            val moisture = moistureInput.text.toString().toFloatOrNull() ?: 0f
            val ash = ashInput.text.toString().toFloatOrNull() ?: 0f

            val fuel = FuelComponents(hydrogen, carbon, sulfur, nitrogen, oxygen, moisture, ash)

            val calculator = Calculator(fuel = fuel, lowerHeatingsComponents = null)

            val calcResults = calculator.calculateFuelParametrs()

            var result = ""

            calcResults.forEach {(key, value) ->
                result += "$key, $value\n"
            }

            resultText.text = "Result: \n $result"
        }

        toTask2Button.setOnClickListener {
            val intent = Intent(this, task2::class.java)
            startActivity(intent)
        }

    }
}