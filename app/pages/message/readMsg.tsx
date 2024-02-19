import React, {useEffect} from 'react';
import {FlatList, Linking, StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import {MessageData, MessageItem} from "./model";
import {connect} from 'react-redux';
import example from "./dva/models";

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 22
    },
    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
});

const renderItemView = ({item, index}: { item: MessageItem }) => (
    <TouchableOpacity onPress={() => {
        Linking.openURL(item.fullLink).catch((err) => console.error('An error occurred', err));
    }}>
        <View style={styles.item}>
            <Text style={{color: '#000000'}}>{index + ': ' + item.title}</Text>
        </View>
    </TouchableOpacity>
);

const FlatListBasics = ({dispatch, dataV2} : {davaV2: MessageData}) => {
    const data: MessageData = dataV2
    useEffect(() => {
        dispatch({
            type: 'example/fetchData',
            payload: '1'
        })
        return () => {
            // 在组件卸载时执行一些清理操作，类似于 componentWillUnmount
        };
    }, [/* dependencies */]);
    return (
        <View style={styles.container}>
            {(data?.datas || []).length > 0 && <FlatList
                data={data?.datas}
                renderItem={renderItemView}
            />}
        </View>
    );
}

export default connect(({example}) => {
    return {
        dataV2: example?.readMessageData,
    };
})(FlatListBasics);
