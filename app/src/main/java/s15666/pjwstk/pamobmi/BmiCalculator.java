package s15666.pjwstk.pamobmi;

class BmiCalculator {

    double calculate(double weight, double height) {
        return weight / (height * height) * 10000;
    }

}
