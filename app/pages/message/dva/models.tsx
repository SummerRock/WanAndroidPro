import {commonFetch} from "../../../common/network";
import {MessageData} from "../model";

export interface MessageState {
    readMessageData: MessageData
    pageName: string
}

const defaultState: MessageState = {
    readMessageData: null,
    pageName: ''
} as MessageState

export default {
    namespace: 'example',
    state: {
        readMessageData: null,
    },
    reducers: {
        save(state, {payload}) {
            const {data} = payload
            return {
                ...state,
                readMessageData: data
            };
        },
    },
    effects: {
        * fetchData({payload}, {call, put}) {
            const page = parseInt(payload || '1')
            // 异步请求数据的逻辑
            const url = `https://wanandroid.com/message/lg/readed_list/${page}/json`;
            const response = yield call(commonFetch, url, null);
            // 将数据保存到 state 中
            yield put({type: 'save', payload: {data: response?.data}});
        },
    },
};

// 模拟一个异步函数，返回一个 Promise
async function fetchDataFromApi(payload: any) {
    // 这里可以是实际的异步操作，例如发起网络请求等
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve({data: 'Some data'});
        }, 1000);
    });
}
