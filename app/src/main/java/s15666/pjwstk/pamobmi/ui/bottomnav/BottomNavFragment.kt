package s15666.pjwstk.pamobmi.ui.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import s15666.pjwstk.pamobmi.R

class BottomNavFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav, container, false)
        val navView: BottomNavigationView = view.findViewById(R.id.nav_view)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_bmi, R.id.navigation_calories, R.id.navigation_food)
                .build()

        val activity = requireActivity() as AppCompatActivity
        val navHostFragment = FragmentManager.findFragment<NavHostFragment>(view.findViewById(R.id.nav_host_fragment))
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)

        return view
    }
}