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

*Here you should discuss how you are going to select your test cases, that is, which black-box and/or white-box techniques you will use. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

### 1.3 Adequacy Criterion

*Define how you are going to assess the quality of your test cases. Typically, this involves some form of functional or structural coverage. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*

### 1.5 Technology

*JUnit will be used to test the various component testcases. Actual GUI interaction or script inputs will handle system and stress tests.*

## 2 Test Cases

*This section should be the core of this document. You should provide a table of test cases, one per row. For each test case, the table should provide its purpose, the steps necessary to perform the test, the expected result, the actual result (to be filled later), pass/fail information (to be filled later), and any additional information you think is relevant.*
