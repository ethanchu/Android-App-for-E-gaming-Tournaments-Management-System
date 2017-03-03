package edu.gatech.seclass.tourneymanager.models;

import java.util.Date;

public class PlayerResult {
    Integer playerResultId;
    Integer playerId;
    Integer tournamentId;
    Double prize;
    Date tournamentDate;

    public PlayerResult(Integer playerResultId,
                        Integer playerId,
                        Integer tournamentId,
                        Double prize,
                        Date tournamentDate) {
        this.playerResultId = playerResultId;
        this.playerId = playerId;
        this.tournamentId = tournamentId;
        this.prize = prize;
        this.tournamentDate = tournamentDate;
    }

    public Integer getPlayerResultId() {
        return playerResultId;
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
