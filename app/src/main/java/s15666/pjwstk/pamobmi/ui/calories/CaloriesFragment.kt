package s15666.pjwstk.pamobmi.ui.calories

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import s15666.pjwstk.pamobmi.R
import s15666.pjwstk.pamobmi.benedictharris.*
import s15666.pjwstk.pamobmi.benedictharris.EnergyExpenditure.Companion.fromNumber
import s15666.pjwstk.pamobmi.benedictharris.EnergyExpenditure.NoSuchEnergyExpenditureException
import s15666.pjwstk.pamobmi.bmi.BmiViewModel

class CaloriesFragment : Fragment() {
    
    private lateinit var aView: View
    private lateinit var caloriesViewModel: CaloriesViewModel
    private lateinit var bmiViewModel: BmiViewModel
    private lateinit var harrisBenedictEquation: HarrisBenedictEquation
    private lateinit var genderSwitch: Switch
    private lateinit var ageField: EditText
    private lateinit var energyUsageSeekBar: SeekBar
    private lateinit var energyUsageDescription: TextView
    private lateinit var result: TextView
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        aView = inflater.inflate(R.layout.fragment_calories, container, false)
        
        val modelOwner: ViewModelStoreOwner = requireActivity()
        caloriesViewModel = ViewModelProvider(modelOwner).get(CaloriesViewModel::class.java)
        bmiViewModel = ViewModelProvider(modelOwner).get(BmiViewModel::class.java)
        
        harrisBenedictEquation = if(bmiViewModel.isMetric.value!!) MetricHBEquation() else ImperialHBEquation()
        
        genderSwitch = aView.findViewById(R.id.calories_gender_switch)
        ageField = aView.findViewById(R.id.calories_age_field)
        energyUsageSeekBar = aView.findViewById(R.id.calories_energy_seekbar)
        energyUsageDescription = aView.findViewById(R.id.calories_energy_description)
        result = aView.findViewById(R.id.calories_result)

        init()
        return aView
    }

    private fun init() {
        initGenderSwitch()
        initAgeField()
        initEnergyUsageSeekBar()
    }

    private fun initGenderSwitch() {
        val isMale = caloriesViewModel.gender.value == Gender.MALE
        genderSwitch.isChecked = isMale
        genderSwitch.text = if (isMale) genderSwitch.textOn else genderSwitch.textOff

        genderSwitch.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            genderSwitch.text = if (isChecked) genderSwitch.textOn else genderSwitch.textOff
            caloriesViewModel.setGender(if (isChecked) Gender.MALE else Gender.FEMALE)
            updateResult()
        }
    }

    private fun initAgeField() {
        ageField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                try {
                    caloriesViewModel.setAge(Integer.valueOf(s.toString()))
                    updateResult()
                } catch(e: NumberFormatException) {
                    caloriesViewModel.setAge(-1)
                    result.text = ""
                }
                updateResult()
            }
        })
    }

    private fun initEnergyUsageSeekBar() {
        energyUsageSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                onProgressChanged(progress)
            }
        })
    }

    private fun onProgressChanged(progress: Int) {
        try {
            val energyExpenditure = fromNumber(progress)
            caloriesViewModel.energyExpenditure.value = energyExpenditure
            energyUsageDescription.text = energyExpenditure.toString()
        } catch (ex: NoSuchEnergyExpenditureException) {
            result.text = ""
            energyUsageDescription.text = ""
        }
        updateResult()
    }

    private fun updateResult() {
        if(caloriesViewModel.isValid() && bmiViewModel.isValid.value!!) {

            val calories = harrisBenedictEquation.calculateEnergyExpenditure(
                    caloriesViewModel.gender.value,
                    caloriesViewModel.energyExpenditure.value!!,
                    bmiViewModel.weight.value!!,
                    bmiViewModel.height.value!!,
                    caloriesViewModel.age.value!!).toInt()

            result.text = aView.context.getString(R.string.calories_result, calories)
        }
    }
}