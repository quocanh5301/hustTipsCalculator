package com.example.tipcalculatehust

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import com.example.tipcalculatehust.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var mainActivityBinding: ActivityMainBinding
    lateinit var button: Button
    lateinit var radioGroup: RadioGroup
    lateinit var switchMaterial: SwitchMaterial
    lateinit var textView: TextView
    lateinit var editText: TextInputEditText
    lateinit var editTextLayout: TextInputLayout

    var tipPercentage : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        switchMaterial = mainActivityBinding.tipSwitch
        textView = mainActivityBinding.resultText
        editText = mainActivityBinding.editText
        editTextLayout = mainActivityBinding.editTextLayout

        radioGroup = mainActivityBinding.radioButtonContainer
        tipPercentage = when (radioGroup.checkedRadioButtonId) {
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }

        button = mainActivityBinding.calculateTip
        button.setOnClickListener(View.OnClickListener {
            if (editText.text!!.isEmpty()){
                editTextLayout.setError("Please enter your service cost...")
            } else {
                calculateTip(editText.text.toString().toDouble())
            }
        })
    }

    fun calculateTip(costOfService : Double){
        textView.visibility = View.VISIBLE

        val tipPercentage = when (radioGroup.checkedRadioButtonId) {
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }

        if (switchMaterial.isChecked){
            textView.setText("" + ceil(tipPercentage*costOfService))
        } else{
            textView.setText("" + tipPercentage*costOfService)
        }
    }
}