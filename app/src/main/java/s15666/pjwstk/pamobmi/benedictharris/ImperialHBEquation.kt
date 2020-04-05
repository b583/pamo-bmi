package s15666.pjwstk.pamobmi.benedictharris

class ImperialHBEquation : HarrisBenedictEquation() {

    override fun getGenderBaseValue(gender: Gender?): Double {
        return if (gender == Gender.MALE) 66.0 else 655.1
    }

    override fun getWeightMultiplier(gender: Gender?): Double {
        return if (gender == Gender.MALE) 6.2 else 4.35
    }

    override fun getHeightMultiplier(gender: Gender?): Double {
        return if (gender == Gender.MALE) 12.7 else 4.7
    }

    override fun getAgeMultiplier(gender: Gender?): Double {
        return if (gender == Gender.MALE) 6.76 else 4.7
    }
}