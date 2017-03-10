# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: 6300Spring17Team07

## 1 Testing Strategy

### 1.1 Overall strategy


*The testing process strategy will be split into different phases:*

*1) Component Unit Testing*
*2) System Testing*
*3) Stress Testing*

*The component unit testing will evaluate the basic functionality for each component. JUnit will be used for component unit testing.*

*The system and stress testing will have the objective of evaluating the response of the application to a variety of inputs, including corner cases.*

*The system test will use our general-purpose test cases with an expected outcome. These test cases will mainly focus on the functional requirements of the application. The execution of the system test cases for the application will be through the actual GUI interaction.*

*The stress test of the application will evaluate the functional/code coverage of a variety of random inputs, include some corner cases. The application is expected to correctly handle this type of input.*

*The regression testing of the application will consist of the successful execution of both the system and stress test cases.*

### 1.2 Test Selection

*In our design, we are going to include Component Unit Testing, System Testing and Stress Testing. We will use both black-box techniques and white-box techniques. Black-box techniques will be primarily used for functional testing in the early design to catch logic defects at all levels. It does not require the code. Once the software app is designed, we will employ while-box techniques for structural testing to test the code and logic branch via Junit and Android Studio. This technique can also be used to compare test suits and allows for covering the coded behaviors .*

### 1.3 Adequacy Criterion

*The functional test will be used at the early stage of the design at all levels to catch potential logic defects. The test data selection will depend on identifying testable features, identifying categories and partitioning categories into choices and identifying constraints among choices etc. In addition, there will be stress test that randomly enter values to test reliability and stability of the app. Once the app is designed, structural methods will be employed to measure the performance of the code.*

### 1.4 Bug Tracking

Our implementation of Tourney Manager will simply use the GitHub issue tracking system for
Bug Tracking. Once identified, the user will create a new issue on GitHub, which will then
send an email to the development team. The GitHub issue tracking system easily allows us
to reference pull requests that resolved specific issues and report to users that the
problem has been resolved.

### 1.5 Technology

*JUnit will be used to test the various component testcases. Actual GUI interaction or script inputs will handle system and stress tests.*

## 2 Test Cases

###Player UI

| Purpose		| Steps     | Expected Results | Actual Results | Result (P/F)| Notes |
| :----------| :------- | :----------------| :--------------| :-----------| :------|
| Test app works	| Open App 	| App opened | App opened | P | |
| Test player UI	| Choose player mode	| Player UI | Player UI  | P  | |
| View totals for every player in the system | | A list sorted by totals showed | A list sorted by totals showed | P | When there is no tournament onging | 
| View match list| | List of player matches and status | List of player matches and status | P | When there is tournament onging |

###Manager UI

| Purpose		| Steps    | Expected Results | Actual Results | Result (P/F)| Notes |
| :----------| :------- | :----------------| :--------------| :-----------| :------| 
| Test app works	| Open App 	| App opened | App opened | P | |
| Test manager UI	| Choose manager mode	| Manager UI | Manager UI   | P  | |
| Add player to the system	| Enter Name, username, phone number, deck choice | Player was created successfully | Player was created successfully | P | |
| Add duplicate player to the system | Enter Name, username, phone number, deck choice | Player has exist | Player has exist | P | Player info has already entered |
| Add username already taken	| Enter Name, username, phone number, deck choice | Player has exist | has exist | P | Player username is used |
| Delete player	| Detele player | The current player was deleted | The current player was deleted | P | |
| Creat tournament | Enter house cut, entry fee, add players	| | | P | |
| Review tournament details | Review tournament details | List of player names, house cut, entry fee, 1st/2nd/3rd prize | List of player names, house cut, entry fee,1st/2nd/3rd prize | P | |
| Start tournament| Start tournament | Tournament started | Tournament started | P | |
| View match list| View match list | List of player matches and status | List of player matches and status | P | |
| Start match | Choose a match from the list and click start | The status changed to started | The status changed to started| P | When a tournament is onging |
| End match | End the onging match | Specify a winner for the ended match | Specify a winner for the ended match | P | When a tournament is onging |
| End match | End a match that is not ongoing | Match is not in started state | Match is not in started state | P | When a tournament is onging |
| Finish tournament | Finish tournament | Tournament is ended | Tournament is ended | P | Finish tournament when all matches are completed |
| Finish tournament | Finish tournament | The tournament is not finished, complete all matches or quit | The tournament is not finished, complete all matches or quit | P | Finish tournament that some of the matches is not completed |
| Quit tournament | Quit tournament | The tournament is quit without saving results | The tournament is quit without saving results | P | |
| View totals for every player in the system | Click view player list | A list sorted by totals showed | A list sorted by totals showed | P | When there is no tournament onging |  
| View a list of a player’s individual prizes | Click a player from the list | A list of the player's individual prizes showed | A list of the player's individual prizes showed | P | When there is no tournament onging |  
| View house profit |  View house profit | A list of past house profits in chronological order | A list of past house profits in chronological order | P | When there is no tournament onging |   
