package edu.gatech.seclass.tourneymanager.dao.constants;


import java.util.HashMap;
import java.util.Map;

/**
 * Enum containing the different deck types. The deck will be saved in the Player table
 * as an integer.
 */
public enum Deck {
    ENGINEER (1),
    BUZZ (2),
    SIDEWAYS (3),
    WRECK (4),
    T (5),
    RAT (6);

    private int value;
    private static final Map<Integer, Deck> typesByValue = new HashMap<>();

    static {
        for(Deck type : Deck.values()) {
            typesByValue.put(type.value, type);
        }
    }

    Deck(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Deck forValue(int value) {
        return typesByValue.get(value);
    }

    public String getDisplay() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
