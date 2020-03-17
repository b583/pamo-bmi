package s15666.pjwstk.pamobmi.benedictharris;

import androidx.annotation.NonNull;

public enum EnergyExpenditure {
    LIGHT("Little or no exercise", 1.53, 0),
    ACTIVE("1 hour of exercise daily", 1.76, 1),
    VIGOR_ACTIVE("2 hours of exercise daily", 2.25, 2);

    EnergyExpenditure(String name, double multiplier, int number) {
        this.name = name;
        this.multiplier = multiplier;
        this.number = number;
    }

    private final String name;
    private final double multiplier;
    private final int number;

    public double getMultiplier() {
        return multiplier;
    }

    public int getNumber() {
        return number;
    }

    @Override
    @NonNull
    public String toString() {
        return name;
    }

    public static EnergyExpenditure fromNumber(int number) throws NoSuchEnergyExpenditureException {

        EnergyExpenditure[] ee = EnergyExpenditure.values();
        for (EnergyExpenditure e : ee) {
            if (e.getNumber() == number) {
                return e;
            }
        }
        throw new NoSuchEnergyExpenditureException();
    }

    public static class NoSuchEnergyExpenditureException extends Exception {
    }
}
