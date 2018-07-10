# react-native-devtodev
React Native bridge to the DevToDev(https://www.devtodev.com/) on Android.

## Installation

1. `npm install --save react-native-devtodev`
2. `react-native link react-native-devtodev`

## Usage

```js
import DevToDev from 'react-native-devtodev';

// Init
DevToDev.init('APP_ID', 'SECRET_KEY');
