package edu.gatech.seclass.tourneymanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerContract;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

/**
 * Data access object used to store and retrieve information about a player.
 */
public class PlayerDao extends SQLiteOpenHelper {

    private static final String[] projection = {
            PlayerContract.PlayerEntry.ID,
            PlayerContract.PlayerEntry.NAME,
            PlayerContract.PlayerEntry.USERNAME,
            PlayerContract.PlayerEntry.PHONENUMBER,
            PlayerContract.PlayerEntry.DECK
    };

    public PlayerDao(Context context) {
        super(context, DatabaseSettings.DATABASE_NAME,
                null, DatabaseSettings.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlayerContract.PlayerEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Create a new player
     * @param name          player name
     * @param username      player username
     * @param phoneNumber   phone number
     * @param deck          deck
     * @return ID of the new player
     */
    public Integer createPlayer(String name,
                             String username,
                             String phoneNumber,
                             Deck deck) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlayerContract.PlayerEntry.NAME, name);
        values.put(PlayerContract.PlayerEntry.USERNAME, username);
        values.put(PlayerContract.PlayerEntry.PHONENUMBER, phoneNumber);
        values.put(PlayerContract.PlayerEntry.DECK, deck.getValue());

        long ret = db.insert(PlayerContract.PlayerEntry.TABLE_NAME, null, values);

        return Long.valueOf(ret).intValue();
    }

    /**
     * Get player by ID
     * @param playerId  ID of the player to retrieve
     * @return found player. null if not found
     */
    public Player getPlayer(Integer playerId) {
        SQLiteDatabase db = getReadableDatabase();


        String selection = PlayerContract.PlayerEntry.ID + " = ?";
        String[] selectionArgs = {playerId.toString()};

        String sortOrder = PlayerContract.PlayerEntry.ID + " ASC";

        Cursor cursor = db.query(
                PlayerContract.PlayerEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            return mapCursorToPlayer(cursor);
        }

        return null;
    }

    /**
     * Get a list of all players
     * @return all players
     */
    public List<Player> getPlayers() {
        SQLiteDatabase db = getReadableDatabase();
        List<Player> ret = new ArrayList<>();

        String[] projection = {
                PlayerContract.PlayerEntry.ID,
                PlayerContract.PlayerEntry.NAME,
                PlayerContract.PlayerEntry.USERNAME,
                PlayerContract.PlayerEntry.PHONENUMBER,
                PlayerContract.PlayerEntry.DECK
        };

        String sortOrder = PlayerContract.PlayerEntry.ID + " ASC";

        Cursor cursor = db.query(
                PlayerContract.PlayerEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        while(cursor.moveToNext()) {
            ret.add(mapCursorToPlayer(cursor));
        }

        return ret;
    }

    /**
     * Set the total lifetime winnings amount for a player
     * @param playerId  ID of the player
     * @param winnings  total winnings
     */
    public void setWinnings(Integer playerId,
                            Double winnings) {
    }

    //////////////////
    // Util
    //////////////////
    private Player mapCursorToPlayer(Cursor cursor) {
        Integer playerId = cursor.getInt(cursor
                .getColumnIndexOrThrow(PlayerContract.PlayerEntry.ID));
        String name = cursor.getString(cursor
                .getColumnIndexOrThrow(PlayerContract.PlayerEntry.NAME));
        String userName = cursor.getString(cursor
                .getColumnIndexOrThrow(PlayerContract.PlayerEntry.NAME));
        String phoneNumber = cursor.getString(cursor
                .getColumnIndexOrThrow(PlayerContract.PlayerEntry.NAME));
        Integer deck = cursor.getInt(cursor
                .getColumnIndexOrThrow(PlayerContract.PlayerEntry.DECK));

       return new Player(playerId, name, userName, phoneNumber, 0.0,
                    Deck.forValue(deck));
    }
}
