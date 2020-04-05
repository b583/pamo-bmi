package s15666.pjwstk.pamobmi.bmi.calculator

class ImperialBmiCalculator : BmiCalculator {
    override fun calculate(weight: Double, height: Double): Double {
        return weight * 703 / (height * height)
    }
}