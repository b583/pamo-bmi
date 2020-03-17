package s15666.pjwstk.pamobmi.benedictharris;

public enum EnergyExpenditure {
    LIGHT("Little or no exercise", 1.53),
    ACTIVE("1 hour of exercise daily", 1.76),
    VIGOR_ACTIVE("2 hours of exercise daily", 2.25);

    EnergyExpenditure(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    private final String name;
    private final double multiplier;

    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return name;
    }
}
