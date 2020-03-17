package s15666.pjwstk.pamobmi.ui.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.bmi.BmiViewModel;
import s15666.pjwstk.pamobmi.bmi.calculator.ImperialBmiCalculator;
import s15666.pjwstk.pamobmi.bmi.calculator.MetricBmiCalculator;

public class BmiFragment extends Fragment {

    private View view;
    private BmiViewModel model;

    private TextView weight;
    private TextView height;

    private EditText weightField;
    private EditText heightField;

    private Switch metricSwitch;

    private TextView bmiResultField;
    private TextView bmiCategoryField;

    private BmiParamWatcher paramWatcher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bmi, container, false);
        model = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);

        weight = view.findViewById(R.id.weightText);
        height = view.findViewById(R.id.heightText);

        weightField = view.findViewById(R.id.weightField);
        heightField = view.findViewById(R.id.heightField);

        bmiResultField = view.findViewById(R.id.bmiResultField);
        bmiCategoryField = view.findViewById(R.id.bmiCategoryField);

        metricSwitch = view.findViewById(R.id.metricSwitch);

        init();
        return view;
    }

    private void init() {
        BmiResultUpdater resultUpdater = new BmiResultUpdater(view, bmiResultField, bmiCategoryField);
        paramWatcher = new BmiParamWatcher(model, weightField, heightField, resultUpdater, new MetricBmiCalculator());

        weightField.addTextChangedListener(paramWatcher);
        heightField.addTextChangedListener(paramWatcher);

        metricSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                metricSwitch.setText(metricSwitch.getTextOn());
                setNumeral(true);
            } else {
                metricSwitch.setText(metricSwitch.getTextOff());
                setNumeral(false);
            }
        });

        Boolean isMetric = model.isMetric().getValue();
        if(isMetric == null || !isMetric) {
            metricSwitch.setChecked(false);
            metricSwitch.setText(metricSwitch.getTextOff());
            setNumeral(false);
        } else {
            metricSwitch.setChecked(true);
            metricSwitch.setText(metricSwitch.getTextOn());
            setNumeral(true);
        }
    }

    private void setNumeral(boolean useMetric) {
        String w;
        String h;
        if(useMetric) {
            w = "kg";
            h = "cm";
            model.setIsMetric(true);
            paramWatcher.setCalculator(new MetricBmiCalculator());
        } else {
            w = "pound";
            h = "inch";
            model.setIsMetric(false);
            paramWatcher.setCalculator(new ImperialBmiCalculator());
        }

        this.weight.setText( getString(R.string.bmi_weight, w));
        this.height.setText( getString(R.string.bmi_height, h));
    }

}
