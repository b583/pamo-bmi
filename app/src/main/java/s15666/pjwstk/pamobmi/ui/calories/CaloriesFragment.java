package s15666.pjwstk.pamobmi.ui.calories;

import android.os.Bundle;
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

import s15666.pjwstk.pamobmi.R;

public class CaloriesFragment extends Fragment {

    private View view;
    private CaloriesViewModel model;

    private Switch genderSwitch;
    private EditText ageField;

    private SeekBar energyUsageSeekbar;
    private TextView energyUsageDescription;
    private TextView result;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calories, container, false);
        model = new ViewModelProvider(requireActivity()).get(CaloriesViewModel.class);

        genderSwitch = view.findViewById(R.id.calories_gender_switch);
        ageField = view.findViewById(R.id.calories_age_field);

        energyUsageSeekbar = view.findViewById(R.id.calories_energy_seekbar);
        energyUsageDescription = view.findViewById(R.id.calories_energy_description);
        result = view.findViewById(R.id.calories_result);

        init();
        return view;
    }

    private void init() {
    }
}