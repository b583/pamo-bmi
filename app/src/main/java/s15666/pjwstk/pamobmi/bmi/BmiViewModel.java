package s15666.pjwstk.pamobmi.bmi;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmiViewModel extends ViewModel {

    private final MutableLiveData<Double> weight = new MutableLiveData<>();
    private final MutableLiveData<Double> height = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isMetric = new MutableLiveData<>();

    public BmiViewModel() {
        isMetric.setValue(Boolean.TRUE);
    }

    public MutableLiveData<Double> getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight.setValue(weight);
    }

    public MutableLiveData<Double> getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height.setValue(height);
    }

    public MutableLiveData<Boolean> isMetric() {
        return isMetric;
    }

    public void setIsMetric(Boolean isMetric) {
        this.isMetric.setValue(isMetric);
    }

}
