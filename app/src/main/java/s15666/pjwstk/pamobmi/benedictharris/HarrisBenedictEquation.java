package s15666.pjwstk.pamobmi.benedictharris;

public abstract class HarrisBenedictEquation {

    final double calculate(Gender gender, double weight, double height, int age) {
        return getGenderBaseValue(gender) + weight * getWeightMultiplier(gender)
                + height * getHeightMultiplier(gender) - age * getAgeMultiplier(gender);
    }

    abstract double getGenderBaseValue(Gender gender);
    abstract double getWeightMultiplier(Gender gender);
    abstract double getHeightMultiplier(Gender gender);
    abstract double getAgeMultiplier(Gender gender);

}
