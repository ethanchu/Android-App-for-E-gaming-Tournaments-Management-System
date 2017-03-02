package edu.gatech.seclass.tourneymanager.models;

import java.util.Date;

public class PlayerResult {
    Integer playerId;
    Integer tournamentId;
    Double prize;
    Date tournamentDate;

    public PlayerResult(Integer playerId,
                        Integer tournamentId,
                        Double prize,
                        Date tournamentDate) {
        this.playerId = playerId;
        this.tournamentId = tournamentId;
        this.prize = prize;
        this.tournamentDate = tournamentDate;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public Double getPrize() {
        return prize;
    }

    public Date getTournamentDate() {
        return tournamentDate;
    }
}
