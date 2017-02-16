1.The tournament is organized as a single elimination tournament with third place playoff.

2.The application has two modes: tournament manager and tournament player. 

3.The tournament manager uses the system to (1) add players, (2) run tournaments, and (3) display prizes and profits.

4.The tournament players use the system to (1) view the match list and (2) view the total player prizes.

5.The app use the addInfo/ remove methods in the AddPlayer class and saveResult/ pastProfit etc. methods in the Display class under the manager mode to save data in the database.

6.AddPlayer class under manager mode will save players info.

7.Same as 6.

8. One boolean variable to indicate the status of the tournament.

9.The getInfo method will get the player name; TourneyCalc class will get the profit and 1st /2nd / 3rd prize

10. When the tournament is not ongoing, the isOngoing variable will be set to false for the player mode, the playerTotalScore method from Display class will be used to display totals for every player in the system as a list sorted by total.

11.When the tournament is ongoing, the isOngoing variable will be set to true for the player mode, the matchList method from Display class will be used.

12.When the tournament is ongoing, the isOngoing variable will be set to true for the  Manager mode.The manager can use:

a. startMatch method to start a match and change the status of the game between the specific players, that is the value of matchList variable.

b. endMatch method to end a match and change the value of matchList variable.

c. end Tournament to end the tournament. If it is ended early, isEndEarly will be true, and refunded method will be triggered.

13.saveResult method will save the prizes for the winning player, the second place player, and the third place player.

14.pastProfit method will save the profit in the database.

15.When the tournament is not ongoing, the isOngoing variable will be set to false for the manager mode.The manager can use:

a. playerTotalScore method to see totals for every players. saveResult method to see the prizes for individual player.

b.pastProfit method to see list of past house profits.

16. The User Interface (UI) must be intuitive and responsive.
Not considered because it does not affect the design directly.