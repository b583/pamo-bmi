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
import androidx.navigation.fragment.NavHostFragment;

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
        doneButton.setOnClickListener((view) -> {
            goToNextPage();
        });

        model.isValid().observe(getViewLifecycleOwner(), aBoolean -> doneButton.setEnabled(aBoolean));
    }

    private void goToNextPage() {
        NavHostFragment.findNavController(this).navigate(R.id.action_welcomeFragment_to_bottomNavFragment);
    }

}
