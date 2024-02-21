// In App.js in a new project

import * as React from 'react';
import {View, Text, Button} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import FlatListBasics from "./readMsg";
import { Provider } from 'react-redux';
import { create } from 'dva-core';
import model from "./dva/models";

const app = create();

// 注册 Dva.js models
app.model(model)
app.start(); // 实例初始化
const store = app._store; // 获取redux的store对象供react-redux使用

const Tab = createBottomTabNavigator();

function HomeScreen({navigation}) {
    return (
        <Provider store={store}>
            <Tab.Navigator>
                <Tab.Screen name="已读消息" component={FlatListBasics}/>
                <Tab.Screen name="未读消息" component={UnreadMessage}/>
            </Tab.Navigator>
        </Provider>
    );
}

function UnreadMessage({navigation}) {
    return (
        <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
            <Text>Home Screen</Text>
            <Button
                title="未读消息"
                onPress={() => navigation.navigate('Details')}
            />
        </View>
    );
}

function DetailsScreen({navigation}) {
    return (
        <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
            <Text>Details Screen</Text>
            <Button
                title="Go to Details... again"
                onPress={() => navigation.push('Details')}
            />
        </View>
    );
}

const Stack = createNativeStackNavigator();

function App() {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home" screenOptions={{ // 这里统一配置Title bar等UI风格
                headerStyle: {
                    backgroundColor: '#2BA245',
                },
                headerTintColor: '#fff',
                headerTitleStyle: {
                    fontWeight: 'bold',
                },
            }}>
                <Stack.Screen name="Home" component={HomeScreen} options={{
                    title: '消息中心',
                    headerShown: false
                }}/>
                <Stack.Screen name="Details" component={DetailsScreen}/>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

export default App;
