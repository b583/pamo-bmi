package s15666.pjwstk.pamobmi.bmi.calculator

class MetricBmiCalculator : BmiCalculator {
    override fun calculate(weight: Double, height: Double): Double {
        return weight / (height * height) * 10000
    }
}