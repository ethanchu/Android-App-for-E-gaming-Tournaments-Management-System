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
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class MatchDao extends SQLiteOpenHelper {

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
        return new ArrayList<>();
    }

    /**
     * Create a new match
     * @param tournamentId  ID of the tournament
     * @param player1       ID of the first player in the tournament
     * @param player2       ID of the second player in the tournament
     * @return ID of the newly created match
     */
    public Integer createMatch(final Integer tournamentId,
                               final Player player1,
                               final Player player2) {
        return 1;
    }

    /**
     * Update a match's status to started
     * @param matchId ID of the match to update
     */
    public void startMatch(final Integer matchId) {
    }

    /**
     * End a match, and set a winner
     * @param matchId   ID of the match to end
     * @param playerId  ID of the winning player
     */
    public void endMatch(Integer matchId, Integer playerId) {
    }
}
