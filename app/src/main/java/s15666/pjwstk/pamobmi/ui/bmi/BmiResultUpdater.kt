package s15666.pjwstk.pamobmi.ui.bmi

import android.view.View
import android.widget.TextView
import s15666.pjwstk.pamobmi.R
import s15666.pjwstk.pamobmi.bmi.BmiViewModel
import s15666.pjwstk.pamobmi.ui.bmi.BmiCategory.NoSuchBmiException

internal class BmiResultUpdater(
        private val view: View,
        private val model: BmiViewModel,
        private val bmiResultField: TextView,
        private val bmiCategoryField: TextView) {

    init {
        updateResultField(0.toDouble())
    }

    fun update(bmi: Double) {
        updateResultField(bmi)
        try {
            val category: BmiCategory = BmiCategory.Companion.getCategory(bmi)
            model.setBmiCategory(category)
            bmiCategoryField.text = category.toString()
        } catch (e: NoSuchBmiException) {
            bmiCategoryField.text = ""
        }
    }

    fun clear() {
        updateResultField(0.0)
        bmiCategoryField.text = ""
    }

    private fun updateResultField(bmi: Double) {
        bmiResultField.text = view.context.getString(R.string.bmi_value, bmi)
    }

}