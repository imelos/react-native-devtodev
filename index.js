// @flow

import { NativeModules } from 'react-native';
const { DevToDev } = NativeModules;

export default {
    init(appId: string, secretKey: string) {
        DevToDev.init(appId, secretKey);
    },
    setCurrentLevel(currentLevel: number) {
        DevToDev.setCurrentLevel(currentLevel);
    },
    setUserId(activeUserId: string) {
        DevToDev.setUserId(activeUserId);
    },
};
