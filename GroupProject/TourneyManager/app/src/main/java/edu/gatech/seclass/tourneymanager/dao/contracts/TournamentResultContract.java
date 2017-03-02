package edu.gatech.seclass.tourneymanager.dao.contracts;

import android.provider.BaseColumns;

public final class TournamentResultContract {
    private TournamentResultContract() {
    }

    public static class TournamentResultEntry implements BaseColumns {
        public static final String TABLE_NAME = "TournamentResult";

        public static final String ID               = "tournament_result_id";
        public static final String TOURNAMENT_ID    = "tournament_id";
        public static final String PROFIT           = "profit";
        public static final String FIRST_PLACE      = "first_place_id";
        public static final String SECOND_PLACE     = "second_place_id";
        public static final String THIRD_PLACE      = "third_place_id";

        public static final String CREATE_TABLE = "\n" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    TOURNAMENT_ID + " INTEGER NOT NULL,\n" +
                    PROFIT + " DOUBLE NOT NULL,\n" +
                    FIRST_PLACE + " INTEGER NOT NULL,\n" +
                    SECOND_PLACE + " INTEGER NOT NULL,\n" +
                    THIRD_PLACE + " INTEGER NOT NULL,\n" +
                    "FOREIGN KEY (" + FIRST_PLACE + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + "(" + PlayerContract.PlayerEntry.ID +
                    "),\n" +
                    "FOREIGN KEY (" + FIRST_PLACE + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + "(" + PlayerContract.PlayerEntry.ID +
                    "),\n" +
                    "FOREIGN KEY (" + FIRST_PLACE + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + "(" + PlayerContract.PlayerEntry.ID +
                    ")\n" +
                ");\n";
    }
}
