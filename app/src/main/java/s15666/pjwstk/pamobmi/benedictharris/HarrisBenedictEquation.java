package s15666.pjwstk.pamobmi.benedictharris;

public abstract class HarrisBenedictEquation {

    public final double calculateEnergyExpenditure(Gender gender, EnergyExpenditure ee, double weight, double height, int age) {
        return ee.getMultiplier() * ( getGenderBaseValue(gender) + weight * getWeightMultiplier(gender)
                + height * getHeightMultiplier(gender) - age * getAgeMultiplier(gender));
    }

    abstract double getGenderBaseValue(Gender gender);
    abstract double getWeightMultiplier(Gender gender);
    abstract double getHeightMultiplier(Gender gender);
    abstract double getAgeMultiplier(Gender gender);
}
