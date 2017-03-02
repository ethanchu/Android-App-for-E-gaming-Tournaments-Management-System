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
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.PlayerResult;
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class PlayerResultDao extends SQLiteOpenHelper {

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
    public Integer createPlayerResult(Integer playerId,
                                      Double prize) {
        return 1;
    }

    /**
     * Get all of the player result items for a player
     * @param playerId  ID of the player
     * @return  a list of all of the player results; null if the player wasn't found or
     * has no entries
     */
    public List<PlayerResult> getPlayerResults(Integer playerId) {
        return new ArrayList<>();
    }
}
