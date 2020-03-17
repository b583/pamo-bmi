package s15666.pjwstk.pamobmi.ui.calories;

final class MetricHBEquation extends HarrisBenedictEquation {

    @Override
    double getGenderBaseValue(Gender gender) {
        return gender.equals(Gender.MALE) ?  66.5 : 655.1;
    }

    @Override
    double getWeightMultiplier(Gender gender) {
        return gender.equals(Gender.MALE) ? 13.75 : 9.56;
    }

    @Override
    double getHeightMultiplier(Gender gender) {
        return gender.equals(Gender.MALE) ? 5 : 1.85;
    }

    @Override
    double getAgeMultiplier(Gender gender) {
        return gender.equals(Gender.MALE) ? 6.75 : 4.67;
    }
}
