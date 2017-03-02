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
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class TournamentDao extends SQLiteOpenHelper {

    public TournamentDao(Context context){
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
     * Creates a tournament with a status of STARTED
     * @param entryFee      the entry fee
     * @param houseCut      house cut percentage
     * @param playerIds     list of player IDs
     * @return the newly created tournament ID
     */
    public Integer createTournament(Double entryFee,
                                    Integer houseCut,
                                    List<Integer> playerIds) {
        return 1;
    }

    /**
     * Get the tournament marked as "STARTED" (there should be only 1)
     * @return the active tournament
     */
    public Tournament getActiveTournament() {
        return null;
    }

    /**
     * Sets the tournament status to ended
     *
     * When this method is called, YOU also need to create one TournamentResult
     * entry, and a Player Result entry for each player
     * @param tournament Id of the tournament to be ended
     */
    public void endTournament(Integer tournament) {
    }

    /**
     * Sets the tournament status to CANCELLED
     * @param tournamentId ID of the tournament to be cancelled
     */
    public void endTournamentEarly(Integer tournamentId) {
    }
}
