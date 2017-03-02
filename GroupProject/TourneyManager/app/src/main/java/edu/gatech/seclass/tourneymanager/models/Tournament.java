package edu.gatech.seclass.tourneymanager.models;

import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;

/**
 * Data class containing information about a tournament.
 */
public class Tournament {
    private final Integer matchId;
    private final Integer tournamentId;
    private final TournamentStatus tournamentStatus;
    private final List<Player> players;
    private final List<Match> matches;
    private final List<Match> completedMatches;
    private final Double entryFee;
    private final Integer houseCut;

    public Tournament(Integer matchId,
                      Integer tournamentId,
                      TournamentStatus tournamentStatus,
                      Double entryFee,
                      Integer houseCut,
                      List<Player> players,
                      List<Match> matches,
                      List<Match> completedMatches) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.tournamentStatus = tournamentStatus;
        this.houseCut = houseCut;
        this.entryFee = entryFee;
        this.players = players;
        this.matches = matches;
        this.completedMatches = completedMatches;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public TournamentStatus getTournamentStatus() {
        return tournamentStatus;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Match> getCompletedMatches() {
        return completedMatches;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public Integer getHouseCut() {
        return houseCut;
    }
}
