import React from 'react';
import {View, Text, TouchableOpacity, StyleSheet} from 'react-native';
import {NetStatus} from "../network";

const ApiStatus = (props: { apiStatus: NetStatus }) => {

    return (
        <View style={styles.container}>
            {props.apiStatus === NetStatus.LOADING && (
                <Text style={styles.loadingText}>Loading...</Text>
            )}

            {props.apiStatus === NetStatus.SUCCESS && (
                <Text style={styles.successText}>Request successful!</Text>
            )}

            {props.apiStatus === NetStatus.FAILED && (
                <Text style={styles.errorText}>Request failed. Please try again.</Text>
            )}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#fff',
        padding: 20,
        alignItems: 'center',
        justifyContent: 'center',
    },
    loadingText: {
        fontSize: 16,
        fontWeight: 'bold',
        color: '#333',
    },
    successText: {
        fontSize: 16,
        fontWeight: 'bold',
        color: 'green',
    },
    errorText: {
        fontSize: 16,
        fontWeight: 'bold',
        color: 'red',
    },
    button: {
        backgroundColor: '#007BFF',
        padding: 10,
        borderRadius: 5,
        marginTop: 20,
    },
    buttonText: {
        color: '#fff',
        fontSize: 16,
        fontWeight: 'bold',
    },
});

export default ApiStatus;
