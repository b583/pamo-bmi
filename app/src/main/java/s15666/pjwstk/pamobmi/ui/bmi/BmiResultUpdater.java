package s15666.pjwstk.pamobmi.ui.bmi;

import android.view.View;
import android.widget.TextView;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.ui.bmi.BmiCategory.NoSuchBmiException;

class BmiResultUpdater {

    private View view;

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    BmiResultUpdater(View view, TextView bmiResultField, TextView bmiCategoryField) {
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

    void clear() {
        updateResultField(0);
        bmiCategoryField.setText("");
    }

    private void updateResultField(double bmi) {
        bmiResultField.setText(view.getContext().getString(R.string.bmi, bmi));
    }
}
