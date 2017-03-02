package edu.gatech.seclass.tourneymanager.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.gatech.seclass.tourneymanager.dao.contracts.MatchContract;
import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerContract;
import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerResultContract;
import edu.gatech.seclass.tourneymanager.dao.contracts.TournamentContract;
import edu.gatech.seclass.tourneymanager.dao.contracts.TournamentResultContract;

public class DatabaseHelper {

    private static Context context;
    private static DatabaseHelper instance = null;

    private final DatabaseHelperInner databaseHelperInner;

    private final PlayerDao playerDao;
    private final TournamentDao tournamentDao;
    private final TournamentResultDao tournamentResultDao;
    private final MatchDao matchDao;
    private final PlayerResultDao playerResultDao;

    private DatabaseHelper() {
        databaseHelperInner = new DatabaseHelperInner(context);
        playerDao = new PlayerDao(context);
        tournamentDao = new TournamentDao(context);
        tournamentResultDao = new TournamentResultDao(context);
        matchDao = new MatchDao(context);
        playerResultDao = new PlayerResultDao(context);
    }

    public static void setContext(Context context1) {
        context = context1;
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public TournamentDao getTournamentDao() {
        return tournamentDao;
    }

    public TournamentResultDao getTournamentResultDao() {
        return tournamentResultDao;
    }

    public MatchDao getMatchDao() {
        return matchDao;
    }

    public PlayerResultDao getPlayerResultDao() {
        return playerResultDao;
    }


    public static void init() {
        if(instance == null) {
            instance = new DatabaseHelper();
        }
    }

    public static DatabaseHelper getInstance() {
        if(instance == null) {
            instance = new DatabaseHelper();
        }

        return instance;
    }

    private class DatabaseHelperInner extends SQLiteOpenHelper {
        public DatabaseHelperInner(Context context) {
            super(context, DatabaseSettings.DATABASE_NAME,
                    null, DatabaseSettings.DATABASE_VERSION);

            // Have to actually get the readable DB before it will create tables
            SQLiteDatabase db = getReadableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(PlayerContract.PlayerEntry.CREATE_TABLE);
            db.execSQL(TournamentContract.TournamentEntry.CREATE_TABLE);
            db.execSQL(TournamentContract.TournamentEntry.CREATE_PLAYER_TABLE);
            db.execSQL(MatchContract.MatchEntry.CREATE_TABLE);
            db.execSQL(TournamentResultContract.TournamentResultEntry.CREATE_TABLE);
            db.execSQL(PlayerResultContract.PlayerResultEntry.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
