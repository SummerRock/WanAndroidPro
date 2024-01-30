import React, {useEffect, useState} from 'react';
import {View, Text, Image, ScrollView, TextInput} from 'react-native';
import {DataResp} from "./interface";
import {commonFetch} from "../../common/network";

const App = () => {

    const [responseData, setResponseData] = useState<DataResp[]>([]);

    const fetchData = async () => {
        try {
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
        return () => {
            // 在组件卸载时执行一些清理操作，类似于 componentWillUnmount
        };
    }, [/* dependencies */]);

    return (
        <ScrollView>
            <Text>我的收藏</Text>
            <View>
                <Text>{responseData?.length}</Text>
                <Image
                    source={{
                        uri: 'https://reactnative.dev/docs/assets/p_cat2.png',
                    }}
                    style={{width: 200, height: 200}}
                />
            </View>
            <TextInput
                style={{
                    height: 40,
                    borderColor: 'gray',
                    borderWidth: 1
                }}
                defaultValue="You can type in me"
            />
        </ScrollView>
    );
}

export default App;
