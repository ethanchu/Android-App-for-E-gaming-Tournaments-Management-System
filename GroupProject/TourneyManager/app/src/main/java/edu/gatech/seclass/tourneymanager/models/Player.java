package edu.gatech.seclass.tourneymanager.models;

import edu.gatech.seclass.tourneymanager.dao.constants.Deck;

/**
 * Data class containing information about the player.
 */
public class Player {
    private final Integer playerId;
    private final String name;
    private final String username;
    private final String phoneNumber;
    private final Double winnings;
    private final Deck deck;

    public Player(Integer playerId,
                  String name,
                  String username,
                  String phoneNumber,
                  Double winnings,
                  Deck deck) {
        this.playerId = playerId;
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.winnings = winnings;
        this.deck = deck;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getWinnings() {
        return winnings;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean equals(Player p){
        if(this.playerId == null){
            return false;
        }
        return this.playerId.equals(p.getPlayerId());
    }
}
