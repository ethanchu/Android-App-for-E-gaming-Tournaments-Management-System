package edu.gatech.seclass.tourneymanager.dao.contracts;

import android.provider.BaseColumns;

public final class TournamentContract {
    private TournamentContract() {
    }

    public static class TournamentEntry implements BaseColumns {
        public static final String TABLE_NAME = "Tournament";
        public static final String TOURNAMENT_PLAYER_TABLE_NAME = "TournamentPlayer";

        public static final String ID = "tournament_id";
        public static final String STATUS = "tournament_status";
        public static final String ENTRYFEE = "entry_fee";
        public static final String HOUSECUT = "house_cut";
        public static final String CREATEDATE = "create_dt";

        public static final String TOURNAMENT_PLAYER_ID = "tournament_player_id";
        public static final String TOURNAMENT_PLAYER_TOURNAMENT_ID = "tournament_player_tournament_id";
        public static final String TOURNAMENT_PLAYER_PLAYER_ID = "player_id";

        public static final String CREATE_TABLE = "\n" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    STATUS + " TINYINT NOT NULL,\n" +
                    ENTRYFEE + " DOUBLE NOT NULL,\n" +
                    HOUSECUT + " INTEGER NOT NULL,\n" +
                    CREATEDATE + " DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ");";

        public static final String CREATE_PLAYER_TABLE = "\n" +
                "CREATE TABLE IF NOT EXISTS " + TOURNAMENT_PLAYER_TABLE_NAME + " (\n" +
                    TOURNAMENT_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    TOURNAMENT_PLAYER_TOURNAMENT_ID + " INTEGER NOT NULL,\n" +
                    TOURNAMENT_PLAYER_PLAYER_ID + " INTEGER NOT NULL,\n" +
                    "FOREIGN KEY (" + TOURNAMENT_PLAYER_PLAYER_ID + ") REFERENCES " +
                        PlayerContract.PlayerEntry.TABLE_NAME + " (" + PlayerContract.PlayerEntry.ID +
                    "),\n" +
                    "FOREIGN KEY (" + TOURNAMENT_PLAYER_TOURNAMENT_ID + ") REFERENCES " +
                        TABLE_NAME + " (" + ID +
                    ")\n" +
                ");";

        public static final String GET_PLAYERS = "\n" +
                "SELECT " +
                    "p."+ PlayerContract.PlayerEntry.ID + ", " +
                    PlayerContract.PlayerEntry.NAME + ", " +
                    PlayerContract.PlayerEntry.USERNAME + ", " +
                    PlayerContract.PlayerEntry.PHONENUMBER + ", " +
                    PlayerContract.PlayerEntry.DECK + " \n" +
                "FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " AS p\n" +
                "LEFT JOIN " + TOURNAMENT_PLAYER_TABLE_NAME + " AS tpt ON tpt." + TOURNAMENT_PLAYER_PLAYER_ID +
                    " = p." + PlayerContract.PlayerEntry.ID + "\n" +
                "WHERE tpt." + TOURNAMENT_PLAYER_TOURNAMENT_ID + " = ?";
    }
}
