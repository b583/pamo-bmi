package s15666.pjwstk.pamobmi.ui.calories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import s15666.pjwstk.pamobmi.benedictharris.Gender;

class CaloriesViewModel extends ViewModel {

    private final MutableLiveData<Gender> gender = new MutableLiveData<>();
    private final MutableLiveData<Integer> age = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isValid = new MutableLiveData<>();

    CaloriesViewModel() {
        gender.setValue(Gender.MALE);
        isValid.setValue(Boolean.FALSE);
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
