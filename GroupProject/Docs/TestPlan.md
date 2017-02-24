# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: \<person or team name\>

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

*Describe how bugs and enhancement requests will be tracked.*

### 1.5 Technology

*JUnit will be used to test the various component testcases. Actual GUI interaction or script inputs will handle system and stress tests.*

## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*
| Purpose		| Steps    | Expected Results | Actual Results | Result (P/F)| Notes |
| :----------| :------- | :----------------| :--------------| :-----------| :------| 
| Test app	| Open App 	| App opened | App opened | P | |
