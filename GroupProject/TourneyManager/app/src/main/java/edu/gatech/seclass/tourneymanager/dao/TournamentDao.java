package edu.gatech.seclass.tourneymanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;
import edu.gatech.seclass.tourneymanager.dao.contracts.PlayerContract;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.contracts.TournamentContract;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class TournamentDao extends SQLiteOpenHelper {

    private static final String[] projection = {
            TournamentContract.TournamentEntry.ID,
            TournamentContract.TournamentEntry.STATUS,
            TournamentContract.TournamentEntry.ENTRYFEE,
            TournamentContract.TournamentEntry.HOUSECUT
    };

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
        SQLiteDatabase db = getWritableDatabase();
        ContentValues tournamentValues = new ContentValues();
        tournamentValues.put(TournamentContract.TournamentEntry.ENTRYFEE, entryFee);
        tournamentValues.put(TournamentContract.TournamentEntry.HOUSECUT, houseCut);
        tournamentValues.put(TournamentContract.TournamentEntry.STATUS, TournamentStatus.STARTED.getValue());

        Integer tournamentId = Long.valueOf(db.insert(TournamentContract.TournamentEntry.TABLE_NAME,
                null, tournamentValues)).intValue();

        if (tournamentId > 0) {
            ContentValues tournamentPlayerContentValues;
            for (Integer player : playerIds) {
                tournamentPlayerContentValues = new ContentValues();
                tournamentPlayerContentValues
                        .put(TournamentContract.TournamentEntry.TOURNAMENT_PLAYER_PLAYER_ID, player);
                tournamentPlayerContentValues
                        .put(TournamentContract.TournamentEntry.TOURNAMENT_PLAYER_TOURNAMENT_ID, tournamentId);

                db.insert(TournamentContract.TournamentEntry.TOURNAMENT_PLAYER_TABLE_NAME, null, tournamentPlayerContentValues);
            }
        }

        return tournamentId;
    }

    /**
     * Get the tournament marked as "STARTED" (there should be only 1)
     * @return the active tournament
     */
    public Tournament getActiveTournament() {
        SQLiteDatabase db = getReadableDatabase();

        String selection = TournamentContract.TournamentEntry.STATUS + " = ?";
        String[] selectionArgs = {String.valueOf(TournamentStatus.STARTED.getValue())};

        Cursor cursor = db.query(
                TournamentContract.TournamentEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            Tournament tournament = mapCursorToTournament(cursor);

            String[] selectionArgsPlayers = {tournament.getTournamentId().toString()};

            cursor.close();

            cursor = db.rawQuery(TournamentContract.TournamentEntry.GET_PLAYERS, selectionArgsPlayers);

            while (cursor.moveToNext()) {
                tournament.getPlayers().add(PlayerDao.mapCursorToPlayer(cursor));
            }

            tournament.getMatches().addAll(
                    DatabaseHelper.getInstance().getMatchDao().getMatches(tournament.getTournamentId()));

            cursor.close();

            return tournament;
        }

        cursor.close();

        return null;
    }

    public void updateTournamentStatus(Integer tournamentId, TournamentStatus tournamentStatus) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TournamentContract.TournamentEntry.STATUS, tournamentStatus.getValue());

        String selection = TournamentContract.TournamentEntry.ID + " = ?";
        String[] selectionArgs = {tournamentId.toString()};

        db.update(
                TournamentContract.TournamentEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    /**
     * Sets the tournament status to ended
     *
     * When this method is called, YOU also need to create one TournamentResult
     * entry, and a Player Result entry for each player
     * @param tournamentId Id of the tournament to be ended
     */
    public void endTournament(Integer tournamentId) {
        updateTournamentStatus(tournamentId, TournamentStatus.COMPLETED);
    }

    /**
     * Sets the tournament status to CANCELLED
     * @param tournamentId ID of the tournament to be cancelled
     */
    public void endTournamentEarly(Integer tournamentId) {
        updateTournamentStatus(tournamentId, TournamentStatus.CANCELLED);
    }

    //////////////////
    // Util
    //////////////////
    public static Tournament mapCursorToTournament(Cursor cursor) {
        Integer tournamentId = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentContract.TournamentEntry.ID));
        Integer status = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentContract.TournamentEntry.STATUS));
        Double entryFee = cursor.getDouble(cursor
                .getColumnIndexOrThrow(TournamentContract.TournamentEntry.ENTRYFEE));
        Integer houseCut = cursor.getInt(cursor
                .getColumnIndexOrThrow(TournamentContract.TournamentEntry.HOUSECUT));

        return new Tournament(tournamentId, TournamentStatus.forValue(status), entryFee,
                houseCut, new ArrayList<Player>(), new ArrayList<Match>());
    }
}
