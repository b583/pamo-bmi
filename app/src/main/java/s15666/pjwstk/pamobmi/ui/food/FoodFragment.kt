package s15666.pjwstk.pamobmi.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import s15666.pjwstk.pamobmi.R
import s15666.pjwstk.pamobmi.bmi.BmiViewModel
import s15666.pjwstk.pamobmi.ui.bmi.BmiCategory
import java.util.*

class FoodFragment : Fragment() {

    private lateinit var model: BmiViewModel
    private lateinit var foodInfoBmi: TextView
    private val categoryStrings = HashMap<BmiCategory?, String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)

        model = ViewModelProvider(requireActivity()).get(BmiViewModel::class.java)
        foodInfoBmi = view.findViewById(R.id.food_info_bmi)

        categoryStrings[BmiCategory.UNDERWEIGHT] = view.context.getString(R.string.food_underweight)
        categoryStrings[BmiCategory.NORMAL] = view.context.getString(R.string.food_normalweight)
        categoryStrings[BmiCategory.OVERWEIGHT] = view.context.getString(R.string.food_overweight)
        categoryStrings[BmiCategory.OBESE] = view.context.getString(R.string.food_obese)

        init()
        return view
    }

    private fun init() {
        foodInfoBmi.text = categoryStrings[model.bmiCategory.value]
    }
}