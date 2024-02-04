import React, {ReactNode} from 'react';
import {View, Text, TouchableOpacity, StyleSheet, ActivityIndicator} from 'react-native';
import {NetStatus} from "../network";

const ApiStatus = (props: {
    apiStatus: NetStatus,
    failCallback: () => void,
    errorMsg?: string,
    component?: ReactNode
}) => {

    return (
        <View style={styles.container}>
            {props.apiStatus === NetStatus.LOADING && (
                <View>
                    <ActivityIndicator size="large" color="#0000ff"/>
                    <Text style={styles.loadingText}>Loading...</Text>
                </View>
            )}

            {props.apiStatus === NetStatus.SUCCESS && (
                <View>{props.component}</View>
            )}

            {props.apiStatus === NetStatus.FAILED && (
                <TouchableOpacity onPress={() => {
                    if (props.failCallback) {
                        props.failCallback()
                    }
                }}>
                    <Text style={styles.errorText}>{props.errorMsg || 'Request failed. Please try again.'}</Text>
                </TouchableOpacity>
            )}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
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
