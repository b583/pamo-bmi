package s15666.pjwstk.pamobmi.ui.bmi;

import android.view.View;
import android.widget.TextView;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.bmi.BmiViewModel;
import s15666.pjwstk.pamobmi.ui.bmi.BmiCategory.NoSuchBmiException;

class BmiResultUpdater {

    private View view;
    private BmiViewModel model;

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    BmiResultUpdater(View view, BmiViewModel model, TextView bmiResultField, TextView bmiCategoryField) {
        this.view = view;
        this.model = model;
        this.bmiResultField = bmiResultField;
        this.bmiCategoryField = bmiCategoryField;

        updateResultField((double) 0);
    }

    void update(double bmi) {

        updateResultField(bmi);

        try {
            BmiCategory category = BmiCategory.getCategory(bmi);

            model.setBmiCategory(category);
            bmiCategoryField.setText(category.toString());
        } catch (NoSuchBmiException e) {
            model.setBmiCategory(null);
            bmiCategoryField.setText("");
        }
    }

    void clear() {
        updateResultField(0);
        bmiCategoryField.setText("");
    }

    private void updateResultField(double bmi) {
        bmiResultField.setText(view.getContext().getString(R.string.bmi_value, bmi));
    }
}
