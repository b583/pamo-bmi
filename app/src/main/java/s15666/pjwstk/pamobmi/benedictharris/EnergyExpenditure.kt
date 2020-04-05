package s15666.pjwstk.pamobmi.benedictharris

enum class EnergyExpenditure(private val description: String, private val multiplier: Double, private val number: Int) {
    LIGHT("Little or no exercise", 1.53, 0),
    ACTIVE("1 hour of exercise daily", 1.76, 1),
    VIGOR_ACTIVE("2 hours of exercise daily", 2.25, 2);

    override fun toString(): String {
        return description
    }

    fun getMultiplier(): Double {
        return multiplier
    }

    class NoSuchEnergyExpenditureException : Exception()
    companion object {
        @JvmStatic
        @Throws(NoSuchEnergyExpenditureException::class)
        fun fromNumber(number: Int): EnergyExpenditure {
            val ee = values()
            for (e in ee) {
                if (e.number == number) {
                    return e
                }
            }
            throw NoSuchEnergyExpenditureException()
        }
    }

}