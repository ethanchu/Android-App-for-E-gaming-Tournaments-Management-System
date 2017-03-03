package edu.gatech.seclass.tourneymanager.models;

import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;

/**
 * Data object containing information about a match.
 */
public class Match {
    private final Integer matchId;
    private final Integer tournamentId;
    private final MatchStatus matchStatus;
    private final Player player1;
    private final Player player2;
    private final Player winner;

    public Match(Integer matchId,
                 Integer tournamentId,
                 MatchStatus matchStatus,
                 Player player1,
                 Player player2,
                 Player winner) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.matchStatus = matchStatus;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        return winner;
    }
}
