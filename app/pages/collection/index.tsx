import React, {useEffect, useState} from 'react';
import {ActivityIndicator, FlatList, Linking, Image, StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import {CollectionData, CollectionItem} from "./interface";
import {commonFetch, NetStatus} from "../../common/network";
import ApiStatus from "../../common/component/apiStatus";
import {Constants, ListInterActStatus} from "../../common/constants";
import {CloseActivityModule} from "../../common/native/module";

const App = (initialProps) => {

    const [responseData, setResponseData] = useState<CollectionData>(null);
    const [status, setStatus] = useState<NetStatus>(NetStatus.LOADING);
    const [errorMsg, setErrorMsg] = useState<string>('请求失败，请重试或重新登陆');
    const [listInterActStatus, setListInterActStatus] = useState(ListInterActStatus.normal);

    const fetchData = async () => {
        try {
            if (!responseData) {
                setStatus(NetStatus.LOADING)
            }
            const page = responseData?.curPage || 0
            const url = `https://www.wanandroid.com/lg/collect/list/${page}/json/`;
            const result = await commonFetch<CollectionData>(url);
            // console.log(result)
            setStatus(NetStatus.SUCCESS)
            setResponseData(result.data);
        } catch (error: Error) {
            setStatus(NetStatus.FAILED)
            setErrorMsg(error.message)
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        // 在组件挂载后，获取 initialProperties 中的数据
        const key1 = initialProps[Constants.App_Version] || 'test_key1';
        // 在这里进行处理
        console.log('Received initial properties:', key1);

        // 这里可以执行副作用操作，类似于 componentDidMount 和 componentDidUpdate
        fetchData()
        return () => {
            // 在组件卸载时执行一些清理操作，类似于 componentWillUnmount
        };
    }, [/* dependencies */]);

    // 渲染每一项的函数
    const renderItemView = ({item, index}: { item: CollectionItem }) => (
        <TouchableOpacity onPress={() => {
            Linking.openURL(item.link).catch((err) => console.error('An error occurred', err));
        }}>
            <View style={styles.item}>
                <Text style={{color: '#000000'}}>{index + ': ' + item.title}</Text>
            </View>
        </TouchableOpacity>
    );

    const onRefresh = () => {
        if (listInterActStatus != ListInterActStatus.normal) {
            return
        }
        setListInterActStatus(ListInterActStatus.refreshing);
        // 刷新操作
        fetchData().then(() => {
            setListInterActStatus(ListInterActStatus.normal);
        })
            .catch(error => {
                setListInterActStatus(ListInterActStatus.normal);
            });
    };

    const onLoadMore = () => {
        console.log('onLoadMore', listInterActStatus)
        if (listInterActStatus != ListInterActStatus.normal) {
            return
        }
        setListInterActStatus(ListInterActStatus.loadMore);
        // 模拟加载更多操作
        fetchData().then(() => {
            setListInterActStatus(ListInterActStatus.normal);
        })
            .catch(error => {
                setListInterActStatus(ListInterActStatus.normal);
            });
    };

    const listView = () => {
        if ((responseData?.datas || []).length > 0) {
            return <FlatList
                data={responseData.datas}
                keyExtractor={item => item.id}
                renderItem={renderItemView}
                onRefresh={onRefresh}
                refreshing={listInterActStatus === ListInterActStatus.refreshing}
                onEndReachedThreshold={0.05}
                ListFooterComponent={responseData.over ? <ActivityIndicator/> : null}
            />
        } else {
            return null;
        }
    }

    return (
        <View style={styles.container}>
            <View style={{ flexDirection: 'row', alignItems: 'center'}}>
                <TouchableOpacity onPress={()=> {
                    CloseActivityModule.closeActivity()
                }}>
                    <Image source={require('../collection/image/arrow_black.png')} style={styles.tinyImage} />
                </TouchableOpacity>
                <Text>我的收藏-React Native</Text>
            </View>
            <ApiStatus apiStatus={status}
                       failCallback={fetchData}
                       errorMsg={errorMsg}
                       component={listView()}/>

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
    tinyImage: {
        width: 18,
        height: 18,
    },
});

export default App;
