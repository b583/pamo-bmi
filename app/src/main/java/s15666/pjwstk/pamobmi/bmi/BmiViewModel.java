package s15666.pjwstk.pamobmi.bmi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmiViewModel extends ViewModel {

    private final MutableLiveData<Double> weight = new MutableLiveData<>();
    private final MutableLiveData<Double> height = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isMetric = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isValid = new MutableLiveData<>();

    public BmiViewModel() {
        isMetric.setValue(Boolean.TRUE);
        isValid.setValue(Boolean.FALSE);
    }

    public MutableLiveData<Double> getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight.setValue(weight);
        checkValid();
    }

    public MutableLiveData<Double> getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height.setValue(height);
        checkValid();
    }

    public MutableLiveData<Boolean> isMetric() {
        return isMetric;
    }

    public void setIsMetric(Boolean isMetric) {
        this.isMetric.setValue(isMetric);
    }

    private void checkValid() {
        Boolean newValue = isMetricValid(weight) && isMetricValid(height);
        if(isValid.getValue() != newValue) {
            isValid.setValue(newValue);
        }
    }

    private boolean isMetricValid(MutableLiveData<Double> field) {
        return field.getValue() != null && field.getValue() > 0;
    }

    public MutableLiveData<Boolean> isValid() {
        return isValid;
    }

}
