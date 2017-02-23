# Project Plan

**Author**: Team 07

## 1 Introduction

TourneyManager is an Android app built to give event organizers a simple way to manage
8 and 16 player tournaments. TourneyManager gives the host a simple way to view total prizes 
collected and paid out, as well as a history of each player's winnings. It will also allow
tournament patrons to view their winnings in all of the tournaments they have participated in.

## 2 Process Description


### add player (manager)
#### Description
#### Entrance criteria
#### Exit Criteria

## Manager Processes

### Add player
#### Description
Manager can add players to the system, making them available to be added to tournaments.
#### Entrance criteria
The following information about the new player must be available: Name, unique username
phone number, deck choice.
#### Exit Criteria
New player successfully added to the database.



### Remove player
#### Description
Managers can remove players from the system.
#### Entrance criteria
Player username must exist in the database.
#### Exit Criteria
Data associated with the given user has been removed from the application.



### View totals for every player in the system
#### Description
Display to the manager a list of all of the players in the system and their lifetime prize
amounts. This list is sorted by the totals.
#### Entrance criteria
At least one player must exist in the system.
#### Exit Criteria
Player list has been displayed to the manager.



### View player details
#### Description
After viewing the list of players and totals, the manager may select one of the users
to view details about that user. This display will show the manager each of the individual
prizes the player has won.
#### Entrance criteria
At least one player must exist to be selected.
#### Exit Criteria
Requested data has been displayed to the manager.



### View past house profits
#### Description
Display to the manager a list of all of the house profits sorted by either chronological
order or total
#### Entrance criteria
At least one tournament should have been completed.
#### Exit Criteria
List of all house profits has been displayed to the manage.



### Create tournament
#### Description
Allow the manager to create a new tournament.
#### Entrance criteria
Manager must enter the house cut, entry price per player, and the 8 or 16 player usernames.
#### Exit Criteria
A view containing the potential prizes and profits will be displayed. The manager will
then be able to accept the values or make changes. If accepted, the tournament will be
created in the database.



### View match list
#### Description
Display a list of available matches.
#### Entrance criteria
Tournament has been started and is not completed.
#### Exit Criteria
List of matches has been displayed to the manager.



### Start match
#### Description
Start a match that is ready to be played from the match list.
#### Entrance criteria
The tournament system has determined match pairings, and the selected match has not yet
been started.
#### Exit Criteria
Selected match has been marked as started.



### End match
#### Description
End a match selected from the match list.
#### Entrance criteria
Selected match is in the started state and a result must be specified.
#### Exit Criteria
Selected match has been marked as complete and the result saved.



### End tournament
#### Description
End the entire tournament. If the tournament was ended before all matches have been 
completed, refunds will be issued.
#### Entrance criteria
Tournament must have been started.
#### Exit Criteria
Tournament marked as closed, and the final result saved; final result can either be
calculated winnings for each winning (1st, 2nd, 3rd place) player and the house cut, 
or refunds for the players if ended before all matches have been completed.




## Player Processes

### View match list
#### Description
Display a list of available matches including match pairings in ongoing matches,
matches ready to be played, and results from completed matches.
#### Entrance criteria
Tournament has been started and is not completed.
#### Exit Criteria
List of matches has been displayed to the manager.



### View totals for every player in the system
#### Description
Display a list of all of the players in the system and their lifetime prize amounts.
This list is sorted by the totals.
#### Entrance criteria
At least one player must exist in the system and no tournament is ongoing.
#### Exit Criteria
Player list has been displayed to the manager.



## 3 Team

*Describe the team and their roles (there may be more roles than there are team members)*

### Team Members
- Yichen Zhu
- Dongxia Dong
- Andrew Gifft
- Josh Hoffman

### Roles


#### Project Manager
Responsible for ensuring that the project is completed on time and that all functional
and non-functional requirements are implemented to spec. Contributes to creating test
documentaiton as well as specification documentation.
#### Quality Assurance
Verifies the correctness of each activity.
#### Developer
Implements the software as per-spec.
#### Business Analyst
Gathers requirements and creates specification documentation.

- *Table showing which team member(s) has which role(s)*
