package s15666.pjwstk.pamobmi;

import android.content.Context;
import android.widget.TextView;

import s15666.pjwstk.pamobmi.BmiCategory.NoSuchBmiException;

class BmiResultUpdater {

    private Context context;

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    BmiResultUpdater(Context context, TextView bmiResultField, TextView bmiCategoryField) {
        this.context = context;
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
        bmiResultField.setText(context.getString(R.string.bmi, bmi));
    }
}
