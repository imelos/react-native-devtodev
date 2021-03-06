# react-native-devtodev
React Native bridge to the DevToDev(https://www.devtodev.com/) on Android.
Not all methods is supported yet

## Installation

1. `npm install --save react-native-devtodev`
2. `react-native link react-native-devtodev`

## Usage

```js
import DevToDev from 'react-native-devtodev';

// Initialize the library
DevToDev.init('APP_ID', 'SECRET_KEY');

// Method allows to initialize the user. It applies when SDK initialization or user relogin.
DevToDev.setUserId(activeUserId: string);

// Method sets the current user level. Using this method allows to actualize the SDK user data in game cross-platform applications.
DevToDev.setCurrentLevel(currentLevel: number);

// Custom event with params object
DevToDev.customEvent(eventName: string, { [string]: string | number });

// Player has reached a new level
DevToDev.levelUp(level: number);
