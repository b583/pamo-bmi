package s15666.pjwstk.pamobmi.calculator;

public class MetricBmiCalculator implements BmiCalculator {

    @Override
    public double calculate(double weight, double height) {
        return weight / (height * height) * 10000;
    }

}
