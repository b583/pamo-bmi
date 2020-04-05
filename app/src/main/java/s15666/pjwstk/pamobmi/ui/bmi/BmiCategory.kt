package s15666.pjwstk.pamobmi.ui.bmi

enum class BmiCategory(private val description: String, val max: Double) {
    UNDERWEIGHT("Underweight", 18.5),
    NORMAL("Normal weight", 24.9),
    OVERWEIGHT("Overweight", 29.9),
    OBESE("Obese", Double.MAX_VALUE);

    override fun toString(): String {
        return description
    }

    internal class NoSuchBmiException : Exception()
    companion object {
        @Throws(NoSuchBmiException::class)
        fun getCategory(bmi: Double): BmiCategory {
            val categories = values()
            for (c in categories) {
                if (bmi < c.max) {
                    return c
                }
            }
            throw NoSuchBmiException()
        }
    }

}