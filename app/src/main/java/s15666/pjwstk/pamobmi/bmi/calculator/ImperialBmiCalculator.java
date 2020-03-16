package s15666.pjwstk.pamobmi.bmi.calculator;

public class ImperialBmiCalculator implements BmiCalculator {

    @Override
    public double calculate(double weight, double height) {
        return weight * 703 / (height * height);
    }

}
