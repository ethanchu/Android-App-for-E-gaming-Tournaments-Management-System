package edu.gatech.seclass.tourneymanager.dao.contracts;

import android.provider.BaseColumns;

public final class PlayerResultContract {
    private PlayerResultContract() {
    }

    public static class PlayerResultEntry implements BaseColumns {
        public static final String TABLE_NAME = "PlayerResult";

        public static final String ID                       = "player_result_id";
        public static final String PLAYER_ID                = "player_id";
        public static final String TOURNAMENT_ID            = "tournament_id";
        public static final String PRIZE                    = "prize";
        public static final String TOURNAMENT_DATE          = "tournament_date";

        public static final String CREATE_TABLE = "\n" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    PLAYER_ID + " INTEGER NOT NULL,\n" +
                    TOURNAMENT_ID + " INTEGER NOT NULL,\n" +
                    PRIZE + " DOUBLE NOT NULL,\n" +
                    TOURNAMENT_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                    "FOREIGN KEY (" + PLAYER_ID + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + " (" + PlayerContract.PlayerEntry.ID +
                    "),\n" +
                    "FOREIGN KEY (" + TOURNAMENT_ID + ") REFERENCES " +
                        TournamentContract.TournamentEntry.TABLE_NAME + " (" + TournamentContract.TournamentEntry.ID +
                    "),\n" +
                    "CONSTRAINT tournament_unique UNIQUE (" + TOURNAMENT_ID + ", " + TOURNAMENT_ID + ")" +
                ");";
    }
}
