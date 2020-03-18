package s15666.pjwstk.pamobmi.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.bmi.BmiViewModel;

public class FoodFragment extends Fragment {

    private BmiViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        model = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);

        init();
        return view;
    }

    private void init() {
    }
}