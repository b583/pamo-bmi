package s15666.pjwstk.pamobmi.benedictharris

class MetricHBEquation : HarrisBenedictEquation() {

    override fun getGenderBaseValue(gender: Gender?): Double {
        return if (gender == Gender.MALE) 66.5 else 655.1
    }

    override fun getWeightMultiplier(gender: Gender?): Double {
        return if (gender == Gender.MALE) 13.75 else 9.56
    }

    override fun getHeightMultiplier(gender: Gender?): Double {
        return if (gender == Gender.MALE) 5.0 else 1.85
    }

    override fun getAgeMultiplier(gender: Gender?): Double {
        return if (gender == Gender.MALE) 6.75 else 4.67
    }
}