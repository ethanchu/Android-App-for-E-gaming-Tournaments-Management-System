package edu.gatech.seclass.tourneymanager.dao.contracts;

import android.provider.BaseColumns;

public final class MatchContract {
    private MatchContract() {
    }

    public static class MatchEntry implements BaseColumns {
        public static final String TABLE_NAME = "Match";

        public static final String ID                   = "match_id";
        public static final String TOURNAMENT_ID        = "tournament_id";
        public static final String STATUS               = "match_status";
        public static final String PLAYER1              = "player1";
        public static final String PLAYER2              = "player2";
        public static final String WINNER               = "winner";


        public static final String CREATE_TABLE = "\n" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    TOURNAMENT_ID + " INTEGER NOT NULL,\n" +
                    STATUS + " TINYINT NOT NULL,\n" +
                    PLAYER1 + " INTEGER NOT NULL,\n" +
                    PLAYER2 + " INTEGER NOT NULL,\n" +
                    WINNER + " INTEGER,\n" +
                    "FOREIGN KEY (" + TOURNAMENT_ID + ") REFERENCES " +
                        TournamentContract.TournamentEntry.TABLE_NAME + "(" + TournamentContract.TournamentEntry.ID +
                    ")," +
                    "FOREIGN KEY (" + PLAYER1 + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + "(" + PlayerContract.PlayerEntry.ID +
                    ")," +
                    "FOREIGN KEY (" + PLAYER2 + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + "(" + PlayerContract.PlayerEntry.ID +
                    ")," +
                    "FOREIGN KEY (" + WINNER + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + "(" + PlayerContract.PlayerEntry.ID +
                    "),\n" +
                    "CONSTRAINT player_unique UNIQUE (" + PLAYER1 + ", " + PLAYER2 + ")" +
                ");";
    }
}
