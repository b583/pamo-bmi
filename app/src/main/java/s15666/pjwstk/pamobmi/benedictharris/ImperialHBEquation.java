package s15666.pjwstk.pamobmi.benedictharris;

public final class ImperialHBEquation extends HarrisBenedictEquation {

    @Override
    double getGenderBaseValue(Gender gender) {
        return gender.equals(Gender.MALE) ? 66 : 655.1;
    }

    @Override
    double getWeightMultiplier(Gender gender) {
        return gender.equals(Gender.MALE) ? 6.2 : 4.35;
    }

    @Override
    double getHeightMultiplier(Gender gender) {
        return gender.equals(Gender.MALE) ? 12.7 : 4.7;
    }

    @Override
    double getAgeMultiplier(Gender gender) {
        return gender.equals(Gender.MALE) ? 6.76 : 4.7;
    }
}
