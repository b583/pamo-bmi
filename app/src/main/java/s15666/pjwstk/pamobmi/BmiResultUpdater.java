package s15666.pjwstk.pamobmi;

import android.widget.TextView;

import s15666.pjwstk.pamobmi.BmiCategory.NoSuchBmiException;

class BmiResultUpdater {

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    BmiResultUpdater(TextView bmiResultField, TextView bmiCategoryField) {
        this.bmiResultField = bmiResultField;
        this.bmiCategoryField = bmiCategoryField;
    }

    void update(double bmi) {
        // TODO formatted string
        bmiResultField.setText(String.format("Calculated BMI: %f", bmi));

        try {
            bmiCategoryField.setText(BmiCategory.getCategory(bmi).toString());
        } catch (NoSuchBmiException e) {
            bmiCategoryField.setText("");
        }
    }
}
