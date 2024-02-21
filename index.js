import React from 'react';
import {AppRegistry} from 'react-native';
import App from "./app/pages/collection";
import APP_MESSAGE from "./app/pages/message";

AppRegistry.registerComponent(
    'my_collection',
    () => App,
);
AppRegistry.registerComponent(
    'my_message',
    () => APP_MESSAGE,
);
