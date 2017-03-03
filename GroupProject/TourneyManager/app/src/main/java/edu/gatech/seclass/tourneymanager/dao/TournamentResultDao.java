package edu.gatech.seclass.tourneymanager.dao;

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
import edu.gatech.seclass.tourneymanager.dao.contracts.TournamentResultContract;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.TournamentResult;

public class TournamentResultDao extends SQLiteOpenHelper {
    private static final String[] projection = {
            TournamentResultContract.TournamentResultEntry.ID,
            TournamentResultContract.TournamentResultEntry.TOURNAMENT_ID,
            TournamentResultContract.TournamentResultEntry.PROFIT,
            TournamentResultContract.TournamentResultEntry.FIRST_PLACE,
            TournamentResultContract.TournamentResultEntry.SECOND_PLACE,
            TournamentResultContract.TournamentResultEntry.THIRD_PLACE,
    };

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

    /**
     * Create a tournament result in the database.
     * @param tournamentId  the tournament ID
     * @param playerId1     ID of the first place player
     * @param playerId2     ID of the second place player
     * @param playerId3     ID of the third place player
     * @param profit        house profit from the tournament
     * @return  ID of the new tournament result entry
     */
    public Integer createTournamentResult(Integer tournamentId,
                                          Integer playerId1,
                                          Integer playerId2,
                                          Integer playerId3,
                                          Double profit) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TournamentResultContract.TournamentResultEntry.TOURNAMENT_ID, tournamentId);
        values.put(TournamentResultContract.TournamentResultEntry.FIRST_PLACE, playerId1);
        values.put(TournamentResultContract.TournamentResultEntry.SECOND_PLACE, playerId2);
        values.put(TournamentResultContract.TournamentResultEntry.THIRD_PLACE, playerId3);
        values.put(TournamentResultContract.TournamentResultEntry.PROFIT, profit);

        long ret = db.insert(TournamentResultContract.TournamentResultEntry.TABLE_NAME, null, values);

        return Long.valueOf(ret).intValue();
    }

    /**
     * Get a list of all tournament results.
     * @return a list of all tournament results.
     */
    public List<TournamentResult> getTournamentResults() {
        SQLiteDatabase db = getReadableDatabase();
        List<TournamentResult> ret = new ArrayList<>();

        String sortOrder = TournamentResultContract.TournamentResultEntry.ID + " DESC";

        Cursor cursor = db.query(
                TournamentResultContract.TournamentResultEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        while(cursor.moveToNext()) {
            ret.add(mapCursorToResult(cursor));
        }

        return ret;
    }

    //////////////////
    // Util
    //////////////////
    public static TournamentResult mapCursorToResult(Cursor cursor) {
        Integer resultID = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentResultContract.TournamentResultEntry.ID));
        Integer tournamentId = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentResultContract.TournamentResultEntry.TOURNAMENT_ID));
        Double profit = cursor.getDouble(cursor
                .getColumnIndexOrThrow(TournamentResultContract.TournamentResultEntry.PROFIT));
        Integer player1Id = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentResultContract.TournamentResultEntry.FIRST_PLACE));
        Integer player2Id = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentResultContract.TournamentResultEntry.SECOND_PLACE));
        Integer player3Id = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentResultContract.TournamentResultEntry.THIRD_PLACE));

        Player p1 = DatabaseHelper.getInstance().getPlayerDao().getPlayer(player1Id);
        Player p2 = DatabaseHelper.getInstance().getPlayerDao().getPlayer(player2Id);
        Player p3 = DatabaseHelper.getInstance().getPlayerDao().getPlayer(player3Id);

        return new TournamentResult(resultID, tournamentId, profit, p1, p2, p3);
    }
}
