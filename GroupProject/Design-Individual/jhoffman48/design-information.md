1. The tournament is organized as a single elimination tournament with third place playoff.
* The application has two modes: tournament manager and tournament player. You can assume that the mode is selected when the application starts, with no authentication involved.
* The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.  
*Modeled by the ManagementSystem class. This management class is the representation of the
tournament system used by both regular users and administrators. The system uses
methods in the Tournament class to gather the required prizes and profits. It also
provides methods to create and delete users. The single elimination and third place
playoff are both handled by the "schedule rounds" method of the Tournament class.
It will schedule and maintain each individual round of matches based off of the
specifications*
* The tournament players use the system to (1) view the match list and (2) view the total player prizes.  
*Similar to the functionality for managers, the PlayerSystem is a system
management class that allows them to view the current matches and their prizes
via the getMatchList and getUsersWinningTotal methods*
* The app has an underlying database to save persistent information across runs (e.g., players in the system, status of ongoing tournaments).
*Implementation detail*
* A player in the system is characterized by the following information:
    1. Name
    * Unique alphanumeric username
    * Numeric phone number
    * A deck choice, from a list of deck options  
    *Described by the User class. Has public properties describing the required
    attributes, and a reference to the User's current deck*
* The tournament manager can add a player to and remove a player from the system.  
*The ManagementSystem class has methods to add a user and remove by username*
* There can only be one ongoing tournament at any given time.  
*The ManagementSystem class only has a single "activeTournament" and the association
line is a single relationship*
* To start a tournament:
    1. The tournament manager will enter the house cut.
    * The tournament manager will enter the entry price.
    * The tournament manager will enter all player usernames. For simplicity, let’s assume that there will be either 8 or 16 players in a tournament.
    * When the tournament manager has entered the above information, the system will display, in addition to the player names, the potential prizes and profit, as calculated in the TourneyCalc app that you developed for Assignment 4. The tournament manager will then be able to double check the information and start the tournament.  
    *Many of the steps are an implementation detail of the form that the manager
    will be using to create the tournament. The ManagementSystem class provides a method
    to create a tournament with the required information, and then add users as necessary*
* When there is no ongoing tournament, the player mode will show totals for every player in the system as a list sorted by total.  
*The PlayerSystem provides a getUserWinningTotals method that analyzes the list
of users and provides the requested information*
* When a tournament is ongoing, the player mode will show a match list. The match list displays a list of players paired with other players representing ongoing matches, matches ready to be played, and results from completed matches.
*The PlayerSystem uses its reference to the activeTournament to show the match list.
The Tournament class provides a method to get the current matches, which provides
additional management options. Each Match maintains a list of players currently playing;
as per spec at the moment this will only be 2 players, but may be expanded later.*
* When a tournament is ongoing, the manager mode will also show a match list. In this case, however, the tournament manager will be able to:
    1. Start a match ready to be played by selecting it from the list. The system will then mark the game between the specified players as started.
    * End an ongoing match and specify a result for it.
    * End the tournament. If the tournament is ended early, money is refunded.  
    *The ManagementSystem is able to get a reference to the current tournament, which
    in turn allows the manager to access the current matches. The Match class provides
    methods to start and finish a match. Tournament will also allow the manager to
    end the current tournament. In that case, money is refunded and a TournamentResult
    is not created. If the Tournament finishes successfully, a tournament result
    object is created and saved which will contain the winnings per user as well as
    the amount of profit for the house*
* After a tournament is completed, prizes for the winning player, the second place player, and the third place player (who wins the third place playoff) will be recorded in the underlying database .  
*Player rankings for a tournament are stored in order in a list that contains the
user themself as well as a number associated with their winnings from that particular
tournament. First, second, and third will be the first three elements of the list.*
* After a tournament is completed, the house profit will also be recorded in the underlying database.
*The house profit is maintained by and is accessible from the TournamentResult object.
This object will also be responsible for persistence to the underlying DB*
* When there is no ongoing tournament, the tournament manager can:
    1. View totals for every player in the system as a list sorted by total. From there, the manager can also view a list of the player’s individual prizes by selecting the player from the list.
    * View the list of past house profits in chronological order and the total.  
    *The totals for a given player are calculated from TournamentResult objects
    using the getUserWinningTotals(User) method (not to be confused with the
    getUsersWinningTotal method which does not take an argument)*
* The User Interface (UI) must be intuitive and responsive.  
*Not considered because it does not affect the design directly*
