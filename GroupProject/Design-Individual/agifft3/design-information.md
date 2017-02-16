Assignment 5 UML Diagram
========================

Design Information document
---------------------------

 

This document goes through each requirement in the assignment and lists how a
system described with my UML diagram meets each of these requirements.  In this
document I italicize the orginal requirements and bold references to explicit
class / attribute / operation / or relation names.

 

Requirement 
------------

*1.The tournament is organized as a single elimination tournament with third
place playoff.*

To realize this requirement I created a class **Tournament** .

 

*2. The application has two modes: tournament manager and tournament player. You
can assume that the mode is selected when the application starts, with no
authentication involved.*

To realize this requirement, I added class **ManagerUI** and **UserUI** ,to meet
this requirement no attributes or operations are required, merely a container.
Due to many uses in common between a tournament player and tournament manager I
generalized the Player interface to User and added a relationship between
**UserUI** and **ManagerUI**

 

*3. The tournament manager uses the system to (1) add players, (2) run
tournaments, and (3) display prizes and profits.*

To realize this requirement a new classes **Player** was added.  The existing
**ManagerUI** added the operation **addPlayer** to address (1) and operation
**startTournament** to address (2).   In or to realize requirement (3), the
operation **getPlayersByWinnings** and **getPlayerPrizes** was added to the
UserUI class, note this requirement is expanded upon in requirement 15.  To
address (3) A new operation was added to **ManagerUI** called **getProfits**
 which will return the information needed to display profits.

 

*4. The tournament players use the system to (1) view the match list and (2)
view the total player prizes.*

To realize this requirement two new operations were added to the **UserUI**
 class.  To meet requirement (1), **getMatchList** was added.  Requirement (2)
is already met from the **getPlayersByWinnings** operation in the **UserUI** which
will return both the players and the winnings

 

*5. The app has an underlying database to save persistent information across
runs (e.g., players in the system, status of ongoing tournaments).*

To realize this requirement I added class **Database** with attributes **Players**
which will be a list of Player classes, **ActiveTournament** which points to a
**Tournament** class.  I do not explicitly encode a tournament state within the
tournament object.  The state of the tournament is dependent on where the
tournament object is pointed to.  If the object is pointed to by
**ActiveTournament** in the database then it is the one active tournament, If it
is pointed to the **DraftTournaement** in **MangerUI** it is setup, and if the
tournament object is not pointed to anywhere (no longer exists) then it is over.

 

*6. A player in the system is characterized by the following information:*

*6.a. Name*

The attribute **Name** was added to class **Player**

 

*6.b. Unique alphanumeric username*

To realize this requirement, the attribute **Username** and operation
**getUsername** was added to class **Player**.  Note in order to meet the
requirement that the username is unique, logic should be added to the
**addPlayer** operation that already exists in the **MangerUI**.  In order for
the **addPlayer** operation to have access to the list of Player usernames, I
added a Database attribute to the **UserUI** class that will point to the
database class where the list of **Players** is stored and can be accessed with
the operation **getPlayers**

 

6.c. Numeric phone number

The attribute **PhoneNumber** was added to class **Player**

 

6.d.A deck choice, from a list of deck options

To realize this requirement the attribute Decks was added to the class Database.
I also added operations **getDecks** and **setDecks** to class Database.  Lastly
the deck preference attribute Deck was added to class Player and corresponding
**getDeckChoice** operation to retrieve this preference

 

*7. The tournament manager can add a player to and remove a player from the
system.*

To realize this requirement I added an operation **removePlayer** to the
**ManagerUI** class and an operation **removePlayer** from the **Database**
class that stores the list of players.  The requirement for tournament manager
to be able to add players was already addressed in the operations **addPlayer**
 in the **MangerUI** class and **Database** class.

 

*8. There can only be one ongoing tournament at any given time.*

To realize this requirement I added the **ActiveTournament** attribute to the
Database class that will point to a Tournament object that is active.  I also
added operation **setActiveTournament** and **getActiveTournament** to the
**Database** class in order to change this attribute.  Additionally operation
**getActiveTournament** was added to the **UserUI** class.

 

*9. To start the tournament:*

To realize this requirement an operation **startTournament** was added to
**MangerUI**

 

*9.a. The tournament manager will enter the house cut.*

To realize this requirement an attribute **HouseCut** was added to the
**Tournament** class.

 

*9.b. The tournament manager will enter the entry price.*

To realize this requirement an attribute **EntryPrice** was added to the
**Tournament** class.

 

*9.c. The tournament manager will enter all player usernames.*

To realize this requirement an attribute **Players** was added to the
**Tournament** class which will contain a list of **Player** objects that are
participating in the tournament.

 

*9.d. When the tournament manager has entered the above information, the system
will display, in addition to the player names, the potential prizes and profit,
as calculated in the TourneyCalc app that you developed for Assignment 4. The
tournament manager will then be able to double check the information and start
the tournament.*

To realize this requirement an attribute **DraftTournament** was added to the
**MangerUI** class and an operation **createTournament** the rationale is the
user will enter this data and the tournament object will be created.  Then the
**Tournament** operations **getPrizes** and **getHouseProfit** can be called to
review the information.  If the user is happy with the results then the
**ManagerUI**  operation **startTournament** can be called which uses existing
operation to put the tournament in the database under **ActiveTournament**.

 

*10. When there is no ongoing tournament, the player mode will show totals for
every player in the system as a list sorted by total.*

This requirement can be realized with the existing **getPlayersByWinnings** 
operation that is part of the **UserUI** class.  This operation should return
a sorted list as described above.

 

*11. When a tournament is ongoing, the player mode will show a match list. The
match list displays a list of players paired with other players representing
ongoing matches, matches ready to be played, and results from completed
matches.*

This requirement can be realized by the existing **getMatchList** operation that
is part of the **Tournament** class. This operation should return a list of
**Match** classes.  To realize the the details of the match list, a class
**Match** was created with the attributes **Player1** , **Player2** , **Winner**
and **Loser**.  Each of these four attributes should point to **Player**
objects or null.  An additional attribute **State** was added to class Match
which will contain the state the match is in (ready, completed, ongoing,
waiting, etc…)

 

*12. When a tournament is ongoing, the manager mode will also show a match list.
In this case, however, the tournament manager will be able to:*

To realize this requirement the existing **getMatchList** operation in **UserUI**
can be used.

 

*12.a. Start a match ready to be played by selecting it from the list. The
system will then mark the game between the specified players as started.*

To realize this requirement an operation **startMatch** was added to the
**ManagerUI** class, additionally an operation **startMatch** was added to the
**Match** class

 

*12.b.End an ongoing match and specify a result for it.*

To realize this requirement an operation **setMatchWinner** was added to the
**MangerUI** class and an operation **setMatchWInner** was added to the **Match**
class.

 

*12.c. End the tournament. If the tournament is ended early, money is refunded.*

To realize this requirement I added an operation **cancelTournament** to the
**MangerUI** class that will delete the tournament without transferring any of
the prizes or profits to the database

 

*13. After a tournament is completed, prizes for the winning player, the second
place player, and the third place player (who wins the third place playoff) will
be recorded in the underlying database .*

To realize this requirement, an attribute **Prizes** was added to the
**Player** class, this will be a list of floats of the prizes the player has
won.  Additionally an operation **AddPrize** was added to the **Player**
class.  Since the list of **Players** is part of the database, their
attributes are recorded in the database.

 

*14. After a tournament is completed, the house profit will also be recorded in
the underlying database.*

To realize this requirement an attribute **HouseProfits** was added to the
**Database** class as well as an operation **addHouseProfit** that will append
the value of **HouseProfit** from the completed tournament to **HouseProfits**.

 

*15. When there is no ongoing tournament, the tournament manager can:*

The system can determine if there is no ongoing tournament by seing if the
**ActiveTournament** attribute is null.

 

*15.a. View totals for every player in the system as a list sorted by total.
From there, the manager can also view a list of the player’s individual prizes
by selecting the player from the list.*

The first part of this requirement is realized from the existing
**getPlayersByWinnings** operation under the **UserUI**.  TO realize the second
part a new operation **getPlayerPrizes** was added to the **MangerUI** which can
get the list of and sum the result of **Player** ’s **getPrizes** operation.

 

*15.b. View the list of past house profits in chronological order and the
total.*

To realize this requirement a new operation **getHouseProfits** was added to
class **Database** this gets the list **HouseProfits** that is an attribute of
the **Database** class.  Note since we only provide an **addHouseProfit**
attribute to the **Database** class we know the returned list is in
chronological order (newest last).  We do not need any additional attributes
storing date or time to get the order.

 

*16. The User Interface (UI) must be intuitive and responsive.*

Not considered because it does not affect the class structure.

 

