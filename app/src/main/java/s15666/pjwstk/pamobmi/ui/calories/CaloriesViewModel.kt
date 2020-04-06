package s15666.pjwstk.pamobmi.ui.calories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import s15666.pjwstk.pamobmi.benedictharris.EnergyExpenditure
import s15666.pjwstk.pamobmi.benedictharris.Gender

class CaloriesViewModel : ViewModel() {
    val gender = MutableLiveData<Gender>()
    val age = MutableLiveData<Int?>()
    val energyExpenditure = MutableLiveData<EnergyExpenditure>()
    private val isValid = MutableLiveData<Boolean>()

    init {
        gender.value = Gender.MALE
        isValid.value = java.lang.Boolean.FALSE
        energyExpenditure.value = EnergyExpenditure.LIGHT
    }

    fun setGender(gender: Gender) {
        this.gender.value = gender
    }

    fun setAge(age: Int) {
        this.age.value = age
        if (age > 0) updateValid(true) else updateValid(false)
    }

    private fun updateValid(valid: Boolean) { if (valid != isValid.value) isValid.value = valid }

    fun isValid(): Boolean = isValid.value!!

}