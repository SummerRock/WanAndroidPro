import React from 'react';
import {AppRegistry, StyleSheet, Text, View} from 'react-native';

const HelloWorld = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.hello}>Hello, World</Text>
        </View>
    );
};

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
    () => HelloWorld,
);
AppRegistry.registerComponent(
    'MyReactNativeAppV2',
    () => HelloWorldV2,
);
