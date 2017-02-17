# Individual designs

## agifft3

### cons
1) Do not need Database class. That will be handled by the implementation of the other classes
2) The ManagerUI and UserUI classes are simply miss named; can be called "Manager"
and "User" instead
3) Manager needs a relation to Match
4) Missing function parameters

### pros


## ddong31

### cons
1) Missing function parameters
2) Classes look like functions; should be named more as a class e.g. "AddPlayer()" should
be "Player"
3) Inside "Player" should have add/remove
4) `TournamentManager` should have a list of `Players` and `Tournaments`
5) Communication implied between interfaces via the database

### pros


## jhoffman48

### cons
1) `startMatch` should be a method in tournament
2) Need relationship between `PlayerSystem` and `TournamentResult`
3) Missing relationship between `Match` and `User`
4) Need a clearer way to handle refunds and ending the tournament early

### pros
1) clear
2) yours is definitely more complex but also more "complete" in that it has more
relations and describes most of the relations and gives qty's for everything which i liked



## yichen

### cons
1) Some confusion between `StartTournament` class and `StartTournament` method
2) Doesn't need relations between classes for every method

### pros
1) Good use of bold text makes it easy to read


# Team Design

### reasoning
1) Clear
2) Simple, so easier to make changes to