package s15666.pjwstk.pamobmi.ui.bmi;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Locale;

import s15666.pjwstk.pamobmi.bmi.BmiViewModel;
import s15666.pjwstk.pamobmi.bmi.calculator.BmiCalculator;

class BmiParamWatcher implements TextWatcher {

    private BmiViewModel model;

    private EditText weightField;
    private EditText heightField;

    private BmiResultUpdater resultUpdater;
    private BmiCalculator calculator;

    BmiParamWatcher(BmiViewModel model, EditText weightField, EditText heightField,
                    BmiResultUpdater resultUpdater, BmiCalculator calculator) {
        this.model = model;
        this.weightField = weightField;
        this.heightField = heightField;
        this.resultUpdater = resultUpdater;
        this.calculator = calculator;

        loadModelData();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // not implemented
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // not implemented
    }

    private void loadModelData() {
        Double weight = model.getWeight().getValue();
        Double height = model.getHeight().getValue();

        // TODO get currently used locale instead of hardcoding US
        if(weight != null) {
            weightField.setText(String.format(Locale.US, "%.2f", model.getWeight().getValue()));
        }

        if(height != null) {
            heightField.setText(String.format(Locale.US, "%.2f", model.getHeight().getValue()));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        Double weight = null;
        Double height = null;

        try {
            weight = Double.valueOf(weightField.getText().toString());
            model.setWeight(weight);
        } catch (NumberFormatException e) {
            model.setWeight(null);
        }

        try {
            height = Double.valueOf(heightField.getText().toString());
            model.setHeight(height);
        } catch (NumberFormatException e) {
            model.setHeight(null);
        }

        if(weight == null || height == null) {
            resultUpdater.clear();
        } else {
            resultUpdater.update(calculator.calculate(weight, height));
        }
    }

    void setCalculator(BmiCalculator calculator) {
        this.calculator = calculator;
        afterTextChanged(null);
    }
}
