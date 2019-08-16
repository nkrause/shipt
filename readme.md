# Shipt - TicTacTo

Two users are able to play a game of TicTacToe

## Getting Started 

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Android Studio
* Virtual Device API >= 16

## Running the app

Navigate to app/java/com/nkrause/shipt/MainActivity, right click Run ‘Main Activity’

## Running the tests

Navigate to app/java/com (androidTest)/nkrause/shipt/tests/TestSuite, right click Run ‘Test Suite’

### Instrumented Tests

The test suite covers:
* Basic initialization of the app
* Starting a new game
* X winning the game
* Draw game

## Assumptions/Constraints

* The two users are human, one isn't an AI
* A new game needs a dialog for confirmation
* I didn't allow the device to be rotated since handling configuration changes seems outside the scope of this project
* The image for O looks bigger than X and trying to resize it took too much time so I decided to leave it as is
* I only tested on the emulator with API 16 and 28 and assumed it would work for all the APIs in between and on a physical device

## Built With

* Android Studio 3.4.2
* Android Gradle Plugin Version 3.4.2
* Gradle Version 5.1.1

## Acknowledgments

* Board Layout - https://owlcation.com/stem/tictactoe
* TestMatchers - https://medium.com/@dbottillo/android-ui-test-espresso-matcher-for-imageview-1a28c832626f
* Testing - https://developer.android.com/training/testing/unit-testing/instrumented-unit-tests
