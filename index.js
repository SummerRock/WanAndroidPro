import React from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';
import App from "./app/pages/collection";

const HelloWorldV2 = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.hello}>V2,Hello, World</Text>
        </View>
    );
};
const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
    },
    hello: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
});

AppRegistry.registerComponent(
    'my_collection',
    () => App,
);
AppRegistry.registerComponent(
    'MyReactNativeAppV2',
    () => HelloWorldV2,
);
