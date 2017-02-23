# Use Case Model

*This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: \<person or team name\>

## 1 Use Case Diagram

*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*

![use case diagram](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/Docs/images/UseCaseDiagram.png)

## 2 Use Case Descriptions

*For each use case in the use case diagram, this section should contain a description, with the following elements:*

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
*Note it is asusmed that no option to start match will be displayed for a match that cannot be started therefore this use case cannot be entered for a match in an invalid state

*Scenarios, Normal Event Sequence, End Tournament:* 

 - The Actor just completed the Normal Sequence for View Match List
 - The Actor clicks a button not associated with a match indicating the Actor would like to end the tournament
 - The system changes the state of the match to finished
 - If all matches were completed, the system updates the player prizes and house profits and stores the match results
 - If all matches were not completed, teh system refunds players (if required) cancels or completes any outstanding matches
 - The system returns to a home GUI outside of the View Match List GUI which is no longer valid as there is no active tournament.


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

#### View Player Winnings

*Requirements:* 

*Pre-conditions:* 

*Post-conditions:* 

*Scenarios:*

#### Remove Player

*Requirements:* 

*Pre-conditions:* 

*Post-conditions:* 

*Scenarios:*

#### Add Player

*Requirements:* 

*Pre-conditions:* 

*Post-conditions:* 

*Scenarios:*

#### Create Tournament

*Requirements:* 

*Pre-conditions:* 

*Post-conditions:* 

*Scenarios:*


# Instructions, will delete once complete
#### View House Profits

- *Requirements: High-level description of what the use case must allow the user to do.*
- *Pre-conditions: Conditions that must be true before the use case is run.*
- *Post-conditions Conditions that must be true once the use case is run.*
- *Scenarios: Sequence of events that characterize the use case. This part may include multiple scenarios, for normal,
 alternate, and exceptional event sequences. These scenarios may be expressed as a list of steps in natural language or as sequence diagrams.*

