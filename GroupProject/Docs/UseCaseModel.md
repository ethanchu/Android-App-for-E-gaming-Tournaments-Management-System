# Use Case Model

**Author**: agiff3 Team: 6300Spring17Team07

## 1 Use Case Diagram

![use case diagram](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/GroupProject/Docs/images/UseCaseDiagram.PNG)

## 2 Use Case Descriptions

This section gives an informal description of each use case.

### Player Use Cases

This section lists each use case that the player can interact with.


*Requirements:*
This use case must allow the actor to view a list of all matches for a tournament and the attributes of a match such as players involved in the match, the state of the match (waiting, started, finished, etc.) and the result of the match if completed.

*Pre-conditions:*
There must be an active tournament meaning the Manager has peformed the Create Tournament use case and not performed the End Tournament use case.

*Post-conditions:*
There is no state change to the system as a result of this action but the post condition is a GUI change displaying hte information described in the Requirements.

*Scenarios, Normal Event Sequence:* 

 - The actor enters the application and selects their role (Player or Manager)
 - The clicks a button to view the match list
 - The system performs a GUI update displaying the match data assuming a tournament is active (see exceptional event)

*Scenarios, Exceptional Event Sequence:*

 - The Actor enters the application and selects their role (Player or Manager)
 - The Actor clicks a button to view the match list
 - The system indicates a warning stating that a tournament is not active and a match list cannot be displayed assuming a tournament is not active

#### View Players and Player Prizes

*Requirements:* This use case must allow the Actor to view a list of all Players that have been added but not removed as well as the totals for all prizes they have won.

*Pre-conditions:* There are no pre-conditions to run this use case, the use case must be able to gracefully handle if there are no players and if players have no winnings.

*Post-conditions:* There is no state change to the system as a result of this action but the post condition is a GUI change displaying the information described in the Requirements.

*Scenarios, Normal Event Sequence:* 

 - The actor enters the application and selects their role (Player or Manager)
 - The clicks a button to view the Players and Player Prizes
 - The system performs a GUI update displaying the A list of players and their associated prizes

*Scenarios, Exceptional Event Sequence (no players):*

 - The Actor enters the application and selects their role (Player or Manager)
 - The Actor clicks a button to view the Players and Player Prizes
 - The system still shows the page of active players and their prizes, as there are no players the data section of the page/GUI would be empty or blank

### Manager Use Cases

This section lists each use case that the manager can interact with.

#### View Match List

*Requirements:*
This use case must allow the Actor to view a list of all matches for a tournament and the attributes of a match such as players involved in the match, the state of the match (waiting, started, finished, etc.) and the result of the match if completed.

*Pre-conditions:*
There must be an active tournament meaning the Manager has peformed the Create Tournament use case and not performed the End Tournament use case.

*Post-conditions:*
There is no state change to the system as a result of this action but the post condition is a GUI change displaying hte information described in the Requirements.

*Scenarios, Normal Event Sequence:* 

 - The actor enters the application and selects their role (Player or Manager)
 - The clicks a button to view the match list
 - The system performs a GUI update displaying the match data assuming a tournament is active (see exceptional event)

*Scenarios, Exceptional Event Sequence (no active tournament):*

 - The Actor enters the application and selects their role (Player or Manager)
 - The Actor clicks a button to view the match list
 - The system indicates a warning stating that a tournament is not active and a match list cannot be displayed assuming a tournament is not active

#### Update Tournament State

*Requirements:*
This use case allows the manager to update the state of a match or tournament by implementing one of the three use cases that inherit Update Tournament State

*Pre-conditions:*
There must be an active tournament meaning the Manager has peformed the Create Tournament use case and not performed the End Tournament use case.
Additionally the actor has to have performed the View Match List use case to get a GUI for the tournament and match state.
Finally the actor must be the manager (the player does not have access to this use case).

*Post-conditions, Set Match Result:* 
If the Actor selects to update the tournament state by setting a match result, the state of the selected match should change to completed and a winner (and optionally loser) should be declared based on the Manger's selection.

*Scenarios, Normal Event Sequence, Set Match Result:* 

 - The Actor just completed the Normal Sequence for View Match List
 - The Actor clicks a button next to a match in the started or active state within the list of matches indicating the Actor would like to set the result
 - The Actor selects a winner
 - The system changes the state of the match to complete, updates the attributes of the match based on the winner selected
 - The system returns to the GUI for View Match List

 Note: it is assumed that no option to start match will be displayed for a match that cannot be completed therefore this use case cannot be entered for a match in an invalid state

*Scenarios, Normal Event Sequence, Start Match:* 

 - The Actor just completed the Normal Sequence for View Match List
 - The Actor clicks a button next to a match in the waiting (or not started or completed) state within the list of matches indicating the Actor would like to start the Match
 - The system changes the state of the match to started
 - The system returns to the GUI for View Match List with the GUI changed to indicate the match has started and any interactions that allows the user to take (such as a button to set match result)
*Note it is assumed that no option to start match will be displayed for a match that cannot be started therefore this use case cannot be entered for a match in an invalid state

*Scenarios, Normal Event Sequence, End Tournament:* 

 - The Actor just completed the Normal Sequence for View Match List
 - The Actor clicks a button not associated with a match indicating the Actor would like to end the tournament
 - The system changes the state of the match to finished
 - If all matches were completed, the system updates the player prizes and house profits and stores the match results
 - If all matches were not completed, the system refunds players (if required) cancels or completes any outstanding matches
 - The system returns to a home GUI outside of the View Match List GUI which is no longer valid as there is no active tournament.


#### View Players and Player Prizes

*Requirements:* This use case must allow the Actor to view a list of all Players that have been added but not removed as well as the totals for all prizes they have won.

*Pre-conditions:* There are no pre-conditions to run this use case, the use case must be able to gracefully handle if there are no players and if players have no winnings.

*Post-conditions:* There is no state change to the system as a result of this action but the post condition is a GUI change displaying the information described in the Requirements.

*Scenarios, Normal Event Sequence:* 

 - The actor enters the application and selects their role (Player or Manager)
 - The clicks a button to view the Players and Player Prizes
 - The system performs a GUI update displaying a list of players and their associated prizes

*Scenarios, Exceptional Event Sequence (no players):*

 - The Actor enters the application and selects their role (Player or Manager)
 - The Actor clicks a button to view the Players and Player Prizes
 - The system still shows the page of active players and their prizes, as there are no players the data section of the page/GUI would be empty or blank

#### View Player Winnings

*Requirements:* This use case must allow the Actor to view a list of an individual player's winnings from each tournament (where the given player won a prize)

*Pre-conditions:* There must be atleast 1 player in the application that has been added but not removed.
The given player does not need to have won a prize or entered any tournaments for this use case.
The Actor must be the Manager.

*Post-conditions:* There is no state change to the system as a result of this action but the post condition is a GUI change displaying the information described in the Requirements.

*Scenarios, Normal Event Sequence:* 

 - The Actor just completed the Normal Sequence for View Player and Player Prizes.
 - The Actor clicks a button associated with a Player indicating the Actor would like to view more detailed information on the player.
 - The system performs a GUI update displaying a list of the prizes the selected player has won.  This list may be empty or blank if the player has not won any prizes.

*Scenarios, Exceptional Event Sequence (no players):*

 - The Actor just completed the Normal Sequence for View Player and Player Prizes.
 - There is no option to View Player winnings meaning this use case cannot be performed.  It is assumed the button to View player winnings will be next to and associated with a player, so if there are no players, then there will be no button.

#### Remove Player

*Requirements:* This use case will change the data contained in the system by removing a player and the associated records from memory / databse.

*Pre-conditions:* Atleast one or more players have been added to the system but not removed.
The Actor is Manager.
The player to be removed is either not in a match that is active or pending (if the player is in the active tournament and has not been eliminated) or the application is capable of automatically setting the result of the match they are in but this may involve some interesting corner cases.


*Post-conditions:* One player is completely removed from the system including any long term storage (database) and any prizes / match states that they have won.

*Scenarios, Normal Event Sequence:* 

 - The Actor just completed the Normal Sequence for View Player and Player Prizes.
 - The Actor clicks a button associated with a Player indicating the Actor would like to remove the player from the application.
 - The system deletes the player from the database.
 - The system shows the View Player and Player Prizes GUI with the updated list of players

*Scenarios, Exceptional Event Sequence (no players):*

 - The Actor just completed the Normal Sequence for View Player and Player Prizes.
 - There is no option to Remove a player meaning this use case cannot be performed.  It is assumed the button to Remove a Player will be next to and associated with a player, so if there are no players, then there will be no button.

*Scenarios, Exceptional Event Sequence (player to remove is active in a Tournament):*

 - The Actor just completed the Normal Sequence for View Player and Player Prizes.
 - The Actor clicks a button associated with a Player indicating the Actor would like to remove the player from the application.
 - The system indicates a warning that a player cannot be removed while they are active in a tournament.
 - The system shows the View Player and Player Prizes GUI with the same list of players.

#### Add Player

*Requirements:* This use case will change the list of players in the database by adding a new player with associated information.

*Pre-conditions:* The only pre-condition is the Actor must be Manager.

*Post-conditions:* The system adds a player and associated player information to the database.

*Scenarios, Normal Event Sequence:* 

 - The Actor enters the application and selects their role as Manager
 - The Actor clicks a button to add a Player
 - The system displays a GUI to enter key user information such as First and Last Name, Username, Deck Choice or any other desired information.
 - The Actor clicks a button to add the Player.
 - The system verifies the data is valid such as ensuring the Username is unique
 - The system adds the user to the database
 - The system returns to the previous GUI

*Scenarios, Exceptional Event Sequence (non-unique username):* 

 - The Actor enters the application and selects their role as Manager
 - The Actor clicks a button to add a Player
 - The system displays a GUI to enter key user information such as First and Last Name, Username, Deck Choice or any other desired information.
 - The Actor clicks a button to add the Player.
 - The system verifies the data and finds the username is already in the system.
 - The system indicates a warning that the username is already used.
 - The system stays on the Add Player GUI and allows the Actor to change the username.

#### Create Tournament

*Requirements:* The Create Tournament use case allows the Actor to setup and create a tournament.

*Pre-conditions:* There must not be an Active Tournament.
The Actor must be Manager.

*Post-conditions:* An active tournament exists with a set of matches.

*Scenarios, Normal Event Sequence:* 

 - The Actor enters the application and selects their role as Manager.
 - The Actor clicks a button to set up a Tournament
 - The system displays a GUI to enter tournament information such as players enrolled in the tournament, entry fee, house percentage, etc.
 - The system calculates the tournament prizes and house cut and displays the information
 - The Actor clicks a button to start the tournament
 - The system creates matches for the tournament.
 - The system stores the tournament as the active tournament in the database.
 - The system returns to the previous GUI outside the tournament setup (the manager home screen)

*Scenarios, Exceptional Event Sequence (wrong number of players added to tournament):* 

 - The Actor enters the application and selects their role as Manager.
 - The Actor clicks a button to set up a Tournament
 - The system displays a GUI to enter tournament information such as players enrolled in the tournament, entry fee, house percentage, etc.
 - The system calculates the tournament prizes and house cut and displays the information
 - The Actor clicks a button to start the tournament
 - The system creates matches for the tournament.
 - The system stores the tournament as the active tournament in the database.
 - The system returns to the previous GUI outside the tournament setup (the manager home screen)

*Scenarios, Alternative Event Sequence (Actor updates tournament information):* 

 - The Actor enters the application and selects their role as Manager.
 - The Actor clicks a button to set up a Tournament
 - The system displays a GUI to enter tournament information such as players enrolled in the tournament, entry fee, house percentage, etc.
 - The system calculates the tournament prizes and house cut and displays the information
 - The Actor clicks a button to start the tournament
 - The system displays a warning that the wrong number of players are enrolled in the tournament
 - The system does not start the tournament but remains on the tournament setup GUI for the Actor to adjust the number of players.

*Scenarios, Exceptional Event Sequence (existing Active Tournament):* 

 - The Actor enters the application and selects their role as Manager.
 - The Actor clicks a button to set up a Tournament.
 - The system displays warning that an active tournament already exists and a new tournament cannot be created.
 - The system returns to a the Manger home screen.

#### View House Profits

*Requirements:* This Use Case shows the historical house profits to the Actor

*Pre-conditions:* There must not be an Active Tournament.
The Actor must be Manager.

*Post-conditions:* There is no state change to the system as a result of this action but the post condition is a GUI change displaying the information described in the Requirements.

*Scenarios, Normal Event Sequence:* 

 - The Actor enters the application and selects their role as Manager.
 - The Actor clicks a button to View House Profits
 - The system changes the GUI to a list of house profits

*Scenarios, Exceptional Event Sequence (a tournament is ongoing):*

 - The Actor enters the application and selects their role as Manager.
 - There is no button to view House Profits, this use case cannot be performed

*Scenarios, Exceptional Event Sequence (no tournaments have been completed, no house profits):*

 - The Actor enters the application and selects their role as Manager.
 - The Actor clicks a button to View House Profits
 - The system changes the GUI to a list of house profits, this list may be blank or empty
