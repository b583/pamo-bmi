package s15666.pjwstk.pamobmi.benedictharris

abstract class HarrisBenedictEquation {

    fun calculateEnergyExpenditure(gender: Gender?, ee: EnergyExpenditure, weight: Double, height: Double, age: Int): Double {
        return ee.getMultiplier() * (getGenderBaseValue(gender) + weight * getWeightMultiplier(gender) + height * getHeightMultiplier(gender) - age * getAgeMultiplier(gender))
    }

    abstract fun getGenderBaseValue(gender: Gender?): Double
    abstract fun getWeightMultiplier(gender: Gender?): Double
    abstract fun getHeightMultiplier(gender: Gender?): Double
    abstract fun getAgeMultiplier(gender: Gender?): Double
}