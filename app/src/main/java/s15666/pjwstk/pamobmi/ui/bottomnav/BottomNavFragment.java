package s15666.pjwstk.pamobmi.ui.bottomnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import s15666.pjwstk.pamobmi.R;

public class BottomNavFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_nav, container, false);
        BottomNavigationView navView = view.findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_bmi, R.id.navigation_calories, R.id.navigation_food)
                .build();

        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        NavHostFragment navHostFragment = FragmentManager.findFragment(view.findViewById(R.id.nav_host_fragment));
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        return view;
    }

}
