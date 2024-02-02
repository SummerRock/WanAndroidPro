import React, {useEffect, useState} from 'react';
import {FlatList, StyleSheet, Text, View} from 'react-native';
import {CollectionData} from "./interface";
import {commonFetch, NetStatus} from "../../common/network";
import ApiStatus from "../../common/component/apiStatus";

const App = () => {

    const [responseData, setResponseData] = useState<CollectionData>(null);
    const [status, setStatus] = useState<NetStatus>(NetStatus.LOADING);

    const fetchData = async () => {
        try {
            setStatus(NetStatus.LOADING)
            const result = await commonFetch<CollectionData>('https://www.wanandroid.com/lg/collect/list/0/json/');
            setStatus(NetStatus.SUCCESS)
            setResponseData(result.data);
        } catch (error) {
            setStatus(NetStatus.FAILED)
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        // 这里可以执行副作用操作，类似于 componentDidMount 和 componentDidUpdate
        console.log('page-tag', 'componentDidMount')
        fetchData()
        return () => {
            // 在组件卸载时执行一些清理操作，类似于 componentWillUnmount
        };
    }, [/* dependencies */]);

    // 渲染每一项的函数
    const renderItemView = ({item, index}) => (
        <View style={styles.item}>
            <Text style={{color: '#000000'}}>{index + ': ' + item.title}</Text>
        </View>
    );

    return (
        <View style={styles.container}>
            <Text>我的收藏</Text>
            <ApiStatus apiStatus={status}/>
            {
                (responseData?.datas || []).length > 0 && <FlatList
                    data={responseData.datas}
                    keyExtractor={item => item.id}
                    renderItem={renderItemView}
                />
            }
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
