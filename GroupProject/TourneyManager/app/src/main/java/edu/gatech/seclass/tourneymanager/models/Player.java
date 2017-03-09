package edu.gatech.seclass.tourneymanager.models;

import edu.gatech.seclass.tourneymanager.dao.constants.Deck;

/**
 * Data class containing information about the player.
 */
public class Player implements Comparable<Player> {
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

    public boolean equals(Player p){ //<<AGIFFT3, I added this so I can compare player objects, the Object.equals didnt find objects with same playerID equal
        if(this.playerId == null){
            return false;
        }
        return this.playerId.equals(p.getPlayerId());
    }

    @Override
    public int compareTo(Player that) {
        if (this.getWinnings()  < that.getWinnings())  return +1;
        if (this.getWinnings()  > that.getWinnings())  return -1;
        return 0;
    }
}
