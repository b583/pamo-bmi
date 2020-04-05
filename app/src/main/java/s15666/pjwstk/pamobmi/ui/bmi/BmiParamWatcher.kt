package s15666.pjwstk.pamobmi.ui.bmi

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import s15666.pjwstk.pamobmi.bmi.BmiViewModel
import s15666.pjwstk.pamobmi.bmi.calculator.BmiCalculator
import java.util.*
import java.lang.Double as JDouble

internal class BmiParamWatcher(
        private val model: BmiViewModel,
        private val weightField: EditText,
        private val heightField: EditText,
        private val resultUpdater: BmiResultUpdater,
        private var calculator: BmiCalculator) : TextWatcher {

    private val invalid = -1.0

    init {
        loadModelData()
    }

    private fun loadModelData() {
        val weight = model.weight.value ?: invalid
        val height = model.height.value ?: invalid

        weightField.setText(String.format(Locale.US, getFormat(weight), weight))
        heightField.setText(String.format(Locale.US, getFormat(height), height))
    }

    private fun getFormat(value: Double): String = if(value == invalid) "" else "%.2f"

    // not implemented
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        updateModelField(model::setWeight, weightField)
        updateModelField(model::setHeight, heightField)

        if(!model.isValid.value!!)
            resultUpdater.clear()
        else
            resultUpdater.update(calculator.calculate(model.weight.value!!, model.height.value!!))
    }

    private fun updateModelField(field: (v: Double) -> Unit, text: TextView) {
        field(try { JDouble.valueOf(text.text.toString()) } catch(e: NumberFormatException) { invalid })
    }

    fun setCalculator(calculator: BmiCalculator) {
        this.calculator = calculator
        afterTextChanged(null)
    }

}