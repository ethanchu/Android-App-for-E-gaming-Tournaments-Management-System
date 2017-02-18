# Individual designs

## agifft3
![agifft3](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/GroupProject/Design-Team/agifft3.png)
### cons
1) Do not need Database class. That will be handled by the implementation of the other classes
2) The ManagerUI and UserUI classes are simply miss named; can be called "Manager"
and "User" instead
3) Manager needs a relation to Match
4) Missing function parameters

### pros
1) Simple and clear format
2) Met nearly all requirements in assignment description
3) Detailed description (md) file.

## ddong31
![ddong31](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/GroupProject/Design-Team/ddong31.png)
### cons
1) Missing function parameters
2) Classes look like functions; should be named more as a class e.g. "AddPlayer()" should
be "Player"
3) Inside "Player" should have add/remove
4) `TournamentManager` should have a list of `Players` and `Tournaments`
5) Communication implied between interfaces via the database

### pros
1) Simple to understand.
2) Different interface for manager and players.
3) Synchronize via underlying database.

## jhoffman48
![jhoffman48](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/GroupProject/Design-Team/jhoffman48.png)
### cons
1) `startMatch` should be a method in tournament
2) Need relationship between `PlayerSystem` and `TournamentResult`
3) Missing relationship between `Match` and `User`
4) Need a clearer way to handle refunds and ending the tournament early

### pros
1) clear
2) yours is definitely more complex but also more "complete" in that it has more
relations and describes most of the relations and gives qty's for everything which i liked



## yzhu421
![yzhu421](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/GroupProject/Design-Team/yzhu421.png)
### cons
1) Some confusion between `StartTournament` class and `StartTournament` method
2) Doesn't need relations between classes for every method

### pros
1) Good use of bold text makes it easy to read
2) detailed decription for each method.
3) Meet the assignment requirements.


# Team Design
![Team Design](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team07/blob/master/GroupProject/Design-Team/team-design.png)
### reasoning
1) Clear
2) Simple, so easier to make changes to


### Summary

There were a lot of lessons we learned from our group discussion.  But first we were all very happy with our team members and their deliverables.  We had active participation from everyone and it was a positive experience. 

 
On the design side we found a lot of value in comparing independently created UML diagrams.  There was a lot of commonality between our designs and that gave us confidence in a clear baseline that covered the majority of the requirements.  With a solid foundation we were able to focus mostly on the small differences in implementation.

 
The differences in the individual designs also gave us some learning experiences.  These helped to highlight requirements that were hard to interpret or open ended and let do discussions on figuring out how to resolve those requirements.  Some we decided were not directly applicable to a UML and would be more in the implementation such as using a databse.  Other requirements we had to figure out how to resolve across our different UML’s such as giving refunds.
 

For the experience within our teamwork we learned the value of feedback.  None of our designs were perfect, they all had things the designer forgot or implemented in a way that may not meet the requirements.  Our team design is definitely better than any individuals a a result of our discussions.  We also did a good job of both accepting and forming critiques in individual designs which helped to keep the discussions positive, kept us focused on the goals, and left more time for our team design. 
