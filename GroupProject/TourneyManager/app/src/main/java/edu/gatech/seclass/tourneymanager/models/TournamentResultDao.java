package edu.gatech.seclass.tourneymanager.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.DatabaseSettings;
import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerContract;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

public class TournamentResultDao extends SQLiteOpenHelper {

    public TournamentResultDao(Context context) {
        super(context, DatabaseSettings.DATABASE_NAME,
                null, DatabaseSettings.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("player", "oncreate");
        db.execSQL(PlayerContract.PlayerEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Integer createTournamentResult(Integer tournamentId,
                                          Integer playerId1,
                                          Integer playerId2,
                                          Integer playerId3,
                                          Double profit) {
        return 1;
    }

    public List<TournamentResult> getTournamentResults() {
        return new ArrayList<>();
    }
}
