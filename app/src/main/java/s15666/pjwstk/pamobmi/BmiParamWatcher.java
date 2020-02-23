package s15666.pjwstk.pamobmi;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class BmiParamWatcher implements TextWatcher {

    private EditText weightField;
    private EditText heightField;

    private BmiCalculator calculator = new BmiCalculator();
    private BmiResultUpdater resultUpdater;

    public BmiParamWatcher(EditText weightField, EditText heightField, BmiResultUpdater resultUpdater) {
        this.weightField = weightField;
        this.heightField = heightField;
        this.resultUpdater = resultUpdater;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // not implemented
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // not implemented
    }

    @Override
    public void afterTextChanged(Editable s) {
        double weight;
        double height;

        try {
            weight = Double.valueOf(weightField.getText().toString());
            height = Double.valueOf(heightField.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }

        resultUpdater.update(calculator.calculate(weight, height));
    }

}
