package s15666.pjwstk.pamobmi.bmi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import s15666.pjwstk.pamobmi.ui.bmi.BmiCategory

class BmiViewModel : ViewModel() {
    val weight = MutableLiveData<Double?>()
    val height = MutableLiveData<Double?>()
    val isMetric = MutableLiveData<Boolean>()
    val isValid = MutableLiveData<Boolean>()
    val bmiCategory = MutableLiveData<BmiCategory?>()

    init {
        isMetric.value = java.lang.Boolean.TRUE
        isValid.value = java.lang.Boolean.FALSE
    }

    fun setWeight(weight: Double) {
        this.weight.value = weight
        checkValid()
    }

    fun setHeight(height: Double) {
        this.height.value = height
        checkValid()
    }

    fun setIsMetric(isMetric: Boolean) {
        this.isMetric.value = isMetric
    }

    private fun checkValid() {
        val newValue = isFieldValid(weight) && isFieldValid(height)
        if (isValid.value != newValue) {
            isValid.value = newValue
        }
    }

    private fun isFieldValid(field: MutableLiveData<Double?>): Boolean = field.value != null && field.value!! > 0.0

    fun setBmiCategory(category: BmiCategory) {
        bmiCategory.value = category
    }

}