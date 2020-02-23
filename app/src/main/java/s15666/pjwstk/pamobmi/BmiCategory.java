package s15666.pjwstk.pamobmi;

enum BmiCategory {

    UNDERWEIGHT("Underweight", 18.5),
    NORMAL("Normal weight", 24.9),
    OVERWEIGHT("Overweight", 29.9),
    OBESE("Obese", Double.MAX_VALUE);

    BmiCategory(String name, double max) {
        this.name = name;
        this.max = max;
    }

    // TODO color
    private final String name;
    private final double max;

    public double getMax() {
        return max;
    }

    @Override
    public String toString() {
        return name;
    }

    static BmiCategory getCategory(double bmi) throws NoSuchBmiException {
        BmiCategory[] categories = BmiCategory.values();
        for (BmiCategory c : categories) {
            if (bmi < c.getMax()) {
                return c;
            }
        }
        throw new NoSuchBmiException();
    }

    static class NoSuchBmiException extends Exception {
    }

}
