package s15666.pjwstk.pamobmi.ui.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import s15666.pjwstk.pamobmi.R;
import s15666.pjwstk.pamobmi.bmi.BmiViewModel;

public class WelcomeFragment extends Fragment {

    private BmiViewModel model;
    private Button doneButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        model = new ViewModelProvider(requireActivity()).get(BmiViewModel.class);
        doneButton = view.findViewById(R.id.welcome_done_button);

        init();
        return view;
    }

    private void init() {
        Boolean isValid = model.isValid().getValue();
        doneButton.setEnabled(isValid == null ? false : isValid);
        model.isValid().observe(this, aBoolean -> doneButton.setEnabled(aBoolean));
    }

}
