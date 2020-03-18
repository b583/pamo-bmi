package s15666.pjwstk.pamobmi.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.bmi.BmiViewModel;
import s15666.pjwstk.pamobmi.ui.bmi.BmiCategory;

public class FoodFragment extends Fragment {

    private BmiViewModel model;
    private TextView foodInfoBmi;

    private HashMap<BmiCategory, String> categoryStrings = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        model = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);

        categoryStrings.put(BmiCategory.UNDERWEIGHT, view.getContext().getString(R.string.food_underweight));
        categoryStrings.put(BmiCategory.NORMAL, view.getContext().getString(R.string.food_normalweight));
        categoryStrings.put(BmiCategory.OVERWEIGHT, view.getContext().getString(R.string.food_overweight));
        categoryStrings.put(BmiCategory.OBESE, view.getContext().getString(R.string.food_obese));

        foodInfoBmi = view.findViewById(R.id.food_info_bmi);

        init();
        return view;
    }

    private void init() {
        if(model.getBmiCategory() == null) {
            foodInfoBmi.setText("");
        } else {
            foodInfoBmi.setText(categoryStrings.get(model.getBmiCategory().getValue()));
        }
    }
}