package edu.gatech.seclass.tourneymanager.models;

import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;

/**
 * Data class containing information about a tournament.
 */
public class Tournament {
    private final Integer tournamentId;
    private final TournamentStatus tournamentStatus;
    private final List<Player> players;
    private final List<Match> matches;
    private final Double entryFee;
    private final Integer houseCut;

    public Tournament(Integer tournamentId,
                      TournamentStatus tournamentStatus,
                      Double entryFee,
                      Integer houseCut,
                      List<Player> players,
                      List<Match> matches) {
        this.tournamentId = tournamentId;
        this.tournamentStatus = tournamentStatus;
        this.houseCut = houseCut;
        this.entryFee = entryFee;
        this.players = players;
        this.matches = matches;
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

    public Double getEntryFee() {
        return entryFee;
    }

    public Integer getHouseCut() {
        return houseCut;
    }
}
