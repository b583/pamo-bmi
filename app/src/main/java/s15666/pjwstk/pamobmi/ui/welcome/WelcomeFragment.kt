package s15666.pjwstk.pamobmi.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import s15666.pjwstk.pamobmi.R
import s15666.pjwstk.pamobmi.bmi.BmiViewModel

class WelcomeFragment : Fragment() {

    private lateinit var model: BmiViewModel
    private lateinit var doneButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        model = ViewModelProvider(requireActivity()).get(BmiViewModel::class.java)
        doneButton = view.findViewById(R.id.welcome_done_button)

        init()
        return view
    }

    private fun init() {
        doneButton.isEnabled = model.isValid.value!!
        doneButton.setOnClickListener { goToNextPage() }

        model.isValid.observe(viewLifecycleOwner, Observer { aBoolean: Boolean? -> doneButton.isEnabled = aBoolean!! })
    }

    private fun goToNextPage() {
        NavHostFragment.findNavController(this).navigate(R.id.action_welcomeFragment_to_bottomNavFragment)
    }
}