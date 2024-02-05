import {commonFetch} from "../../../common/network";
import {MessageData} from "../model";

export default {
    namespace: 'example',
    state: {
        data: [],
        readMessageData: null,
    },
    reducers: {
        save(state, {payload}) {
            const { data } = payload
            console.log('xiayan-data', data.data)
            return {...state, ...payload};
        },
    },
    effects: {
        * fetchData({payload}, {call, put}) {
            // 异步请求数据的逻辑
            const data = yield call(fetchDataFromApi);
            // 将数据保存到 state 中
            console.log('xiayan-fetchData', data)
            yield put({type: 'save', payload: {data}});
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
