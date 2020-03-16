package s15666.pjwstk.pamobmi;

import android.view.View;
import android.widget.TextView;

import s15666.pjwstk.pamobmi.BmiCategory.NoSuchBmiException;

public class BmiResultUpdater {

    private View view;

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    public BmiResultUpdater(View view, TextView bmiResultField, TextView bmiCategoryField) {
        this.view = view;
        this.bmiResultField = bmiResultField;
        this.bmiCategoryField = bmiCategoryField;

        updateResultField((double) 0);
    }

    void update(double bmi) {

        updateResultField(bmi);

        try {
            bmiCategoryField.setText(BmiCategory.getCategory(bmi).toString());
        } catch (NoSuchBmiException e) {
            bmiCategoryField.setText("");
        }
    }

    private void updateResultField(double bmi) {
        bmiResultField.setText(view.getContext().getString(R.string.bmi, bmi));
    }
}
