package edu.gatech.seclass.tourneymanager.dao.constants;


import java.util.HashMap;
import java.util.Map;

public enum TournamentStatus {
    NOTSTARTED      (1),
    STARTED         (2),
    COMPLETED       (3),
    CANCELLED       (4);

    private int value;
    private static final Map<Integer, TournamentStatus> typesByValue = new HashMap<>();

    static {
        for(TournamentStatus type : TournamentStatus.values()) {
            typesByValue.put(type.value, type);
        }
    }

    TournamentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TournamentStatus forValue(int value) {
        return typesByValue.get(value);
    }

    public String getDisplay() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
