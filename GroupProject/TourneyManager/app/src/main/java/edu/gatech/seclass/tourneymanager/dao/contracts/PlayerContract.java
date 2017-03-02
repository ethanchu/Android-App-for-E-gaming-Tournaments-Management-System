package edu.gatech.seclass.tourneymanager.dao.contracts;

import android.provider.BaseColumns;

public final class PlayerContract {
    private PlayerContract() {
    }

    public static class PlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "Player";

        public static final String ID           = "player_id";
        public static final String NAME         = "name";
        public static final String USERNAME     = "username";
        public static final String PHONENUMBER  = "phone_number";
        public static final String DECK         = "deck";

        public static final String CREATE_TABLE = "\n" +
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        NAME + " VARCHAR(100) NOT NULL,\n" +
                        USERNAME + " VARCHAR(50) NOT NULL UNIQUE,\n" +
                        PHONENUMBER + " VARCHAR(50) NOT NULL,\n" +
                        DECK + " TINYINT NOT NULL\n" +
                ");";
    }
}
