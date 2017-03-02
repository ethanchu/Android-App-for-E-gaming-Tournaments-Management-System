package edu.gatech.seclass.tourneymanager.dao.constants;


import java.util.HashMap;
import java.util.Map;

public enum MatchStatus {
    NOTSTARTED      (1),
    STARTED         (2),
    COMPLETED       (3);

    private int value;
    private static final Map<Integer, MatchStatus> typesByValue = new HashMap<>();

    static {
        for(MatchStatus type : MatchStatus.values()) {
            typesByValue.put(type.value, type);
        }
    }

    MatchStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MatchStatus forValue(int value) {
        return typesByValue.get(value);
    }

    public String getDisplay() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
