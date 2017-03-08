package edu.gatech.seclass.tourneymanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerContract;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerResultContract;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.PlayerResult;
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class PlayerResultDao extends SQLiteOpenHelper {

    private final static String[] projection = {
            PlayerResultContract.PlayerResultEntry.ID,
            PlayerResultContract.PlayerResultEntry.TOURNAMENT_ID,
            PlayerResultContract.PlayerResultEntry.PLAYER_ID,
            PlayerResultContract.PlayerResultEntry.PRIZE,
            PlayerResultContract.PlayerResultEntry.TOURNAMENT_DATE
    };

    public PlayerResultDao(Context context) {
        super(context, DatabaseSettings.DATABASE_NAME,
                null, DatabaseSettings.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Create a new player result in the database. This will be a historical view of
     * a players tournaments and winnings.
     * @param playerId  ID of the player
     * @param prize     amount the player won (0 if they were not in the top 3)
     * @return ID of the new player result entry
     */
    public Integer createPlayerResult(Integer tournamentId,
                                      Integer playerId,
                                      Double prize) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlayerResultContract.PlayerResultEntry.TOURNAMENT_ID, tournamentId);
        values.put(PlayerResultContract.PlayerResultEntry.PLAYER_ID, playerId);
        values.put(PlayerResultContract.PlayerResultEntry.PRIZE, prize);

        long ret = db.insert(PlayerResultContract.PlayerResultEntry.TABLE_NAME, null, values);

        return Long.valueOf(ret).intValue();
    }

    /**
     * Get all of the player result items for a player
     * @param playerId  ID of the player
     * @return  a list of all of the player results; null if the player wasn't found or
     * has no entries
     */
    public List<PlayerResult> getPlayerResults(Integer playerId) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = PlayerResultContract.PlayerResultEntry.PLAYER_ID + " = ?";
        String[] selectionArgs = {playerId.toString()};

        String sortOder = PlayerResultContract.PlayerResultEntry.TOURNAMENT_DATE + " DESC";

        Cursor cursor = db.query(
                PlayerResultContract.PlayerResultEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOder);

        List<PlayerResult> ret = new ArrayList<>();

        while(cursor.moveToNext()) {
            ret.add(mapCursorToResult(cursor));
        }


        return ret;
    }

    /**
     * Get the total lifetime winnings for a player
     * @param playerId  player ID
     * @return  the sum of all winnings for the player
     */
    public Double getPlayerTotalWinnings(Integer playerId) {
        SQLiteDatabase db = getReadableDatabase();

        String[] selectionArgs = {playerId.toString()};

        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + PlayerResultContract.PlayerResultEntry.PRIZE + ")\n" +
                "FROM " + PlayerResultContract.PlayerResultEntry.TABLE_NAME + "\n" +
                "WHERE " + PlayerResultContract.PlayerResultEntry.PLAYER_ID + " = ?", //<<AGIFFT3, added semicolon
                selectionArgs);

        Double ret = 0.0;

        if(cursor.moveToFirst()) {
            ret = cursor.getDouble(0);
        }

        return ret;
    }

    //////////////////
    // Util
    //////////////////
    public static PlayerResult mapCursorToResult(Cursor cursor) {
        Integer resultId = cursor.getInt(cursor
                .getColumnIndexOrThrow(PlayerResultContract.PlayerResultEntry.ID));
        Integer tournamentId = cursor.getInt(cursor
                .getColumnIndexOrThrow(PlayerResultContract.PlayerResultEntry.TOURNAMENT_ID));
        Integer playerId = cursor.getInt(cursor
                .getColumnIndexOrThrow(PlayerResultContract.PlayerResultEntry.PLAYER_ID));
        Double prize = cursor.getDouble(cursor
                .getColumnIndexOrThrow(PlayerResultContract.PlayerResultEntry.PRIZE));
        String date = cursor.getString(cursor
                .getColumnIndexOrThrow(PlayerResultContract.PlayerResultEntry.TOURNAMENT_DATE));

        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        try {
            d = iso8601Format.parse(date);
        } catch (ParseException e) {
            Log.e("PlayerResultDao", "error parsing date");
        }

        return new PlayerResult(resultId, tournamentId, playerId, prize, d);
    }

}
