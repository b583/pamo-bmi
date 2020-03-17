package s15666.pjwstk.pamobmi.ui.calories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import s15666.pjwstk.pamobmi.benedictharris.EnergyExpenditure;
import s15666.pjwstk.pamobmi.benedictharris.Gender;

public class CaloriesViewModel extends ViewModel {

    private final MutableLiveData<Gender> gender = new MutableLiveData<>();
    private final MutableLiveData<Integer> age = new MutableLiveData<>();
    private final MutableLiveData<EnergyExpenditure> energyExpenditure = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isValid = new MutableLiveData<>();

    public CaloriesViewModel() {
        gender.setValue(Gender.MALE);
        isValid.setValue(Boolean.FALSE);
        energyExpenditure.setValue(EnergyExpenditure.LIGHT);
    }

    MutableLiveData<Gender> getGender() {
        return gender;
    }

    void setGender(Gender gender) {
        this.gender.setValue(gender);
        checkValid();
    }

    MutableLiveData<Integer> getAge() {
        return age;
    }

    void setAge(Integer age) {
        this.age.setValue(age);
        checkValid();
    }

    MutableLiveData<EnergyExpenditure> getEnergyExpenditure() {
        return energyExpenditure;
    }

    void setEnergyExpenditure(EnergyExpenditure e) {
        energyExpenditure.setValue(e);
        Boolean isValid = e != null;
        if(isValid != this.isValid.getValue()) {
            this.isValid.setValue(isValid);
        }
    }

    private void checkValid() {
        Boolean newValue = age.getValue() != null && age.getValue() > 0;
        if(isValid.getValue() != newValue) {
            isValid.setValue(newValue);
        }
    }

    MutableLiveData<Boolean> isValid() {
        return isValid;
    }

}
