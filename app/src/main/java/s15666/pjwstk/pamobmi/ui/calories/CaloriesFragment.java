package s15666.pjwstk.pamobmi.ui.calories;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.Objects;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.benedictharris.EnergyExpenditure;
import s15666.pjwstk.pamobmi.benedictharris.Gender;
import s15666.pjwstk.pamobmi.benedictharris.HarrisBenedictEquation;
import s15666.pjwstk.pamobmi.benedictharris.ImperialHBEquation;
import s15666.pjwstk.pamobmi.benedictharris.MetricHBEquation;
import s15666.pjwstk.pamobmi.bmi.BmiViewModel;

public class CaloriesFragment extends Fragment {

    private View view;

    private CaloriesViewModel caloriesViewModel;
    private BmiViewModel bmiViewModel;
    private HarrisBenedictEquation harrisBenedictEquation;

    private Switch genderSwitch;
    private EditText ageField;

    private SeekBar energyUsageSeekBar;
    private TextView energyUsageDescription;
    private TextView result;

    @SuppressWarnings("ConstantConditions")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calories, container, false);
        Activity activity = requireActivity();

        caloriesViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(CaloriesViewModel.class);
        bmiViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(BmiViewModel.class);
        if(bmiViewModel.isMetric().getValue() != null && bmiViewModel.isMetric().getValue()) {
            harrisBenedictEquation = new MetricHBEquation();
        } else {
            harrisBenedictEquation = new ImperialHBEquation();
        }


        genderSwitch = view.findViewById(R.id.calories_gender_switch);
        ageField = view.findViewById(R.id.calories_age_field);

        energyUsageSeekBar = view.findViewById(R.id.calories_energy_seekbar);
        energyUsageDescription = view.findViewById(R.id.calories_energy_description);
        result = view.findViewById(R.id.calories_result);

        init();
        return view;
    }

    private void init() {
        initGenderSwitch();
        initAgeField();
        initEnergyUsageSeekBar();
    }

    private void initGenderSwitch() {
        genderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            genderSwitch.setText(
                    isChecked ? genderSwitch.getTextOn(): genderSwitch.getTextOff());
            caloriesViewModel.setGender(isChecked ? Gender.MALE : Gender.FEMALE);
            updateResult();
        });

        boolean isMale = Objects.equals(caloriesViewModel.getGender().getValue(), Gender.MALE);
        genderSwitch.setChecked(isMale);
        genderSwitch.setText(
                isMale ? genderSwitch.getTextOn(): genderSwitch.getTextOff());
    }

    private void initAgeField() {
        ageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                Integer age = null;
                try {
                    age = Integer.valueOf(s.toString());
                } catch (NumberFormatException e) {
                    // TODO clear calories result
                } finally {
                    caloriesViewModel.setAge(age);
                    updateResult();
                }
            }
        });
    }

    private void initEnergyUsageSeekBar() {
        energyUsageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void onSeekBarChanged(int progress) {
        EnergyExpenditure e = null;
        try {
            e = EnergyExpenditure.fromNumber(progress);
            updateEnergyUsageDescriptor(e.toString());
        } catch (EnergyExpenditure.NoSuchEnergyExpenditureException ex) {
            // TODO clear calories result
        } finally {
            caloriesViewModel.setEnergyExpenditure(e);
            updateResult();
        }
    }

    private void updateEnergyUsageDescriptor(String description) {
        energyUsageDescription.setText(description);
    }

    private void updateResult() {
        if(caloriesViewModel.isValid() == null || bmiViewModel.isValid() == null) {
            return;
        }

        if(caloriesViewModel.isValid().getValue() && bmiViewModel.isValid().getValue()) {

            int calories = (int) harrisBenedictEquation.calculateEnergyExpenditure(
                    caloriesViewModel.getGender().getValue(),
                    caloriesViewModel.getEnergyExpenditure().getValue(),
                    bmiViewModel.getWeight().getValue(),
                    bmiViewModel.getHeight().getValue(),
                    caloriesViewModel.getAge().getValue());

            result.setText(view.getContext().getString(R.string.calories_result, calories));
        }
    }

}