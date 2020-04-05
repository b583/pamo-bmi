package s15666.pjwstk.pamobmi.ui.bmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import s15666.pjwstk.pamobmi.R
import s15666.pjwstk.pamobmi.bmi.BmiViewModel
import s15666.pjwstk.pamobmi.bmi.calculator.BmiCalculator
import s15666.pjwstk.pamobmi.bmi.calculator.ImperialBmiCalculator
import s15666.pjwstk.pamobmi.bmi.calculator.MetricBmiCalculator

class BmiFragment : Fragment() {

    private lateinit var aView: View
    
    private lateinit var model: BmiViewModel
    private lateinit var weight: TextView
    private lateinit var height: TextView
    private lateinit var weightField: EditText
    private lateinit var heightField: EditText
    private lateinit var metricSwitch: Switch
    private lateinit var bmiResultField: TextView
    private lateinit var bmiCategoryField: TextView
    private lateinit var paramWatcher: BmiParamWatcher

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        aView = inflater.inflate(R.layout.fragment_bmi, container, false)

        model = ViewModelProvider(requireActivity()).get(BmiViewModel::class.java)
        weight = aView.findViewById(R.id.weightText)
        height = aView.findViewById(R.id.heightText)
        weightField = aView.findViewById(R.id.weightField)
        heightField = aView.findViewById(R.id.heightField)
        bmiResultField = aView.findViewById(R.id.bmiResultField)
        bmiCategoryField = aView.findViewById(R.id.bmiCategoryField)
        metricSwitch = aView.findViewById(R.id.metricSwitch)

        init()
        return aView
    }

    private fun init() {
        val resultUpdater = BmiResultUpdater(aView, model, bmiResultField, bmiCategoryField)
        paramWatcher = BmiParamWatcher(model, weightField, heightField, resultUpdater, MetricBmiCalculator())
        weightField.addTextChangedListener(paramWatcher)
        heightField.addTextChangedListener(paramWatcher)
        initMetricSwitch()
    }

    private fun initMetricSwitch() {
        metricSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            onMetricSwitchChange(isChecked)
        }

        val isMetric = if(model.isMetric.value != null) model.isMetric.value!! else false
        onMetricSwitchChange(isMetric)
    }

    private fun onMetricSwitchChange(isChecked: Boolean) {
        metricSwitch.text = if (isChecked) metricSwitch.textOn else metricSwitch.textOff
        setNumeral(isChecked)
    }

    private fun setNumeral(useMetric: Boolean) {
        if(useMetric)
            onSetNumeral("kg", "cm", useMetric, MetricBmiCalculator())
        else
            onSetNumeral("pound", "inch", useMetric, ImperialBmiCalculator())
    }

    private fun onSetNumeral(w: String, h: String, isMetric: Boolean, calc: BmiCalculator) {
        model.setIsMetric(isMetric)
        paramWatcher.setCalculator(calc)

        weight.text = getString(R.string.bmi_weight, w)
        height.text = getString(R.string.bmi_height, h)
    }

}