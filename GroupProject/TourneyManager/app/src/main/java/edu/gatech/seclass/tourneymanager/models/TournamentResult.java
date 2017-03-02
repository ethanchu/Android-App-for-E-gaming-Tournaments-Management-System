package edu.gatech.seclass.tourneymanager.models;

/**
 * Data object containing information about a completed match.
 */
public class TournamentResult {
    private final Integer tournamentResultId;
    private final Integer tournamentId;
    private final Integer playerId;
    private final Double profit;
    private final Player firstPlace;
    private final Player secondPlace;
    private final Player thirdPlace;

    public TournamentResult(final Integer tournamentResultId,
                            final Integer tournamentId,
                            final Integer playerId,
                            final Double profit,
                            final Player firstPlace,
                            final Player secondPlace,
                            final Player thirdPlace) {
        this.tournamentResultId = tournamentResultId;
        this.tournamentId = tournamentId;
        this.playerId = playerId;
        this.profit = profit;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.thirdPlace = thirdPlace;
    }

    public Integer getTournamentResultId() {
        return tournamentResultId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Double getProfit() {
        return profit;
    }

    public Player getFirstPlace() {
        return firstPlace;
    }

    public Player getSecondPlace() {
        return secondPlace;
    }

    public Player getThirdPlace() {
        return thirdPlace;
    }
}
