package com.example.lr_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class task2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultText: TextView = findViewById(R.id.resultText2)

        val carbonInput2: EditText = findViewById(R.id.carbonInput2)
        val hydrogenInput2: EditText = findViewById(R.id.hydrogenInput2)
        val oxygenInput2: EditText = findViewById(R.id.oxygenInput2)
        val sulfurInput2: EditText = findViewById(R.id.sulfurInput2)
        val lowerHeatingValueInput: EditText = findViewById(R.id.lowerHeatingValueInput)
        val moistureInput2: EditText = findViewById(R.id.moistureInput2)
        val ashInput2: EditText = findViewById(R.id.ashInput2)
        val vanadiumInput2: EditText = findViewById(R.id.vanadiumInput2)

        val calcButton2: Button = findViewById(R.id.calcButton2)
        val toTask1Button: Button = findViewById(R.id.toTask1)

        calcButton2.setOnClickListener {
            val carbon = carbonInput2.text.toString().toFloatOrNull() ?: 0f
            val hydrogen = hydrogenInput2.text.toString().toFloatOrNull() ?: 0f
            val oxygen = oxygenInput2.text.toString().toFloatOrNull() ?: 0f
            val sulfur = sulfurInput2.text.toString().toFloatOrNull() ?: 0f
            val lowerHeatingValue = lowerHeatingValueInput.text.toString().toFloatOrNull() ?: 0f
            val moisture = moistureInput2.text.toString().toFloatOrNull() ?: 0f
            val ash = ashInput2.text.toString().toFloatOrNull() ?: 0f
            val vanadium = vanadiumInput2.text.toString().toFloatOrNull() ?: 0f

            val lowerHeatingComponents = LowerHeatingComponents(carbon, hydrogen, oxygen, sulfur, lowerHeatingValue, moisture, ash, vanadium)

            val calculator = Calculator(fuel = null, lowerHeatingsComponents = lowerHeatingComponents)


            val calcResult = calculator.calculateLowerHeatingValue()

            resultText.text = "Result: $calcResult МДж/кг"
        }

        toTask1Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}