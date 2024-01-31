import React, {useEffect, useState} from 'react';
import {View, Text, FlatList, StyleSheet} from 'react-native';
import {DataResp} from "./interface";
import {commonFetch} from "../../common/network";
import NetworkModule from "../../common/native/module";

const App = () => {

    const [responseData, setResponseData] = useState<DataResp[]>([]);

    const fetchData = async () => {
        try {
            const { cookie } = await NetworkModule.requestNetworkCookieStr('test_token');
            console.log('page-tag', cookie)
            const result = await commonFetch<DataResp[]>('https://www.wanandroid.com/project/tree/json/');
            setResponseData(result.data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        // 这里可以执行副作用操作，类似于 componentDidMount 和 componentDidUpdate
        console.log('page-tag', 'componentDidMount')
        fetchData()
        NetworkModule.printLog();
        return () => {
            // 在组件卸载时执行一些清理操作，类似于 componentWillUnmount
        };
    }, [/* dependencies */]);

    // 渲染每一项的函数
    const renderItemView = ({item, index}) => (
        <View style={styles.item}>
            <Text style={{color: '#000000'}}>{index + ': ' + item.name}</Text>
        </View>
    );

    return (
        <View style={styles.container}>
            <Text>项目分类</Text>
            <FlatList
                data={responseData}
                keyExtractor={item => item.id}
                renderItem={renderItemView}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        paddingTop: 22
    },
    item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
});

export default App;
