package edu.gatech.seclass.tourneymanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.dao.contracts.MatchContract;
import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerContract;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class MatchDao extends SQLiteOpenHelper {

    String[] projection = {
            MatchContract.MatchEntry.ID,
            MatchContract.MatchEntry.TOURNAMENT_ID,
            MatchContract.MatchEntry.STATUS,
            MatchContract.MatchEntry.PLAYER1,
            MatchContract.MatchEntry.PLAYER2,
            MatchContract.MatchEntry.WINNER
    };

    public MatchDao(Context context) {
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
     * Get all matches for the given tournament.
     * @param tournamentId ID of the tournament
     * @return a list of all matches associated with a tournament
     */
    public List<Match> getMatches(Integer tournamentId) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = MatchContract.MatchEntry.TOURNAMENT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(tournamentId)};

        String sortOrder = MatchContract.MatchEntry.ID + " DESC";

        Cursor cursor = db.query(
                MatchContract.MatchEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        List<Match> ret = new ArrayList<>();
        while(cursor.moveToNext()) {
            ret.add(mapCursorToMatch(cursor));
        }

        return ret;
    }

    public Match getMatch(Integer matchId) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = MatchContract.MatchEntry.ID + " = ?";
        String [] selectionArgs = {matchId.toString()};

        Cursor cursor = db.query(
                MatchContract.MatchEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            return mapCursorToMatch(cursor);
        }

        return null;
    }

    /**
     * Create a new match
     * @param tournamentId  ID of the tournament
     * @param player1       ID of the first player in the tournament
     * @param player2       ID of the second player in the tournament
     * @return ID of the newly created match
     */
    public Integer createMatch(final Integer tournamentId,
                               final Integer player1,
                               final Integer player2) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MatchContract.MatchEntry.TOURNAMENT_ID, tournamentId);
        values.put(MatchContract.MatchEntry.PLAYER1, player1);
        values.put(MatchContract.MatchEntry.PLAYER2, player2);
        values.put(MatchContract.MatchEntry.STATUS, MatchStatus.NOTSTARTED.getValue());

        long ret = db.insert(MatchContract.MatchEntry.TABLE_NAME, null, values);

        return Long.valueOf(ret).intValue();
    }

    public void updateMatchStatus(final Integer matchId, MatchStatus matchStatus,
                                  Integer winnerId) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MatchContract.MatchEntry.STATUS, matchStatus.getValue());

        if(winnerId != null) {
            contentValues.put(MatchContract.MatchEntry.WINNER, winnerId.toString());
        }

        String selection = MatchContract.MatchEntry.ID + " = ?";
        String[] selectionArgs = {matchId.toString()};

        db.update(
                MatchContract.MatchEntry.TABLE_NAME,
                contentValues,
                selection,
                selectionArgs);
    }

    /**
     * Update a match's status to started
     * @param matchId ID of the match to update
     */
    public void startMatch(final Integer matchId) {
        updateMatchStatus(matchId, MatchStatus.STARTED, null);
    }

    /**
     * End a match, and set a winner
     * @param matchId   ID of the match to end
     * @param playerId  ID of the winning player
     */
    public void endMatch(Integer matchId, Integer playerId) {
        updateMatchStatus(matchId, MatchStatus.COMPLETED, playerId);
    }

    //////////////////
    // Util
    //////////////////
    public static Match mapCursorToMatch(Cursor cursor) {
        Integer matchId = cursor.getInt(cursor
                .getColumnIndexOrThrow(MatchContract.MatchEntry.ID));
        Integer status = cursor.getInt(cursor
                .getColumnIndexOrThrow(MatchContract.MatchEntry.STATUS));
        Integer tournamentId = cursor.getInt(cursor
                .getColumnIndexOrThrow(MatchContract.MatchEntry.TOURNAMENT_ID));
        Integer player1 = cursor.getInt(cursor
                .getColumnIndexOrThrow(MatchContract.MatchEntry.PLAYER1));
        Integer player2 = cursor.getInt(cursor
                .getColumnIndexOrThrow(MatchContract.MatchEntry.PLAYER2));
        Integer winner = cursor.getInt(cursor
                .getColumnIndexOrThrow(MatchContract.MatchEntry.WINNER));

        Log.v("in match", winner.toString());

        Player p1 = DatabaseHelper.getInstance().getPlayerDao().getPlayer(player1);
        Player p2 = DatabaseHelper.getInstance().getPlayerDao().getPlayer(player2);
        Player w = winner > 0 ? DatabaseHelper.getInstance().getPlayerDao().getPlayer(winner) : null;

        return new Match(matchId, tournamentId, MatchStatus.forValue(status), p1, p2, w);
    }
}
