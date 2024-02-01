// 定义请求返回的数据类型
import NetworkModule from "./native/module";

interface ApiResponse<T> {
    data: T;
    errorMsg?: string;
    errorCode: number
}

// 封装的 fetch 函数
export async function commonFetch<T>(url: string, options?: RequestInit): Promise<ApiResponse<T>> {
    try {
        const { cookie } = await NetworkModule.requestNetworkCookieStr('test_token');
        console.log('cookie-tag', cookie)
        const response: Response = await fetch(url, {
            ...options,
            headers: {
                'Content-Type': 'application/json',
                'Cookie': cookie, // 在这里添加Cookie
            },
        });
        const data: ApiResponse<T> = await response.json();

        if (!response.ok) {
            console.error('network-err', 'request-failed')
            throw new Error(data.errorMsg || '网络请求失败');
        }

        if (data.errorCode === -1001) {
            console.error('network-err', 'login-failed')
            throw new Error(data.errorMsg || '请先登录！');
        }

        if (!data.data) {
            console.error('network-err', 'no-data')
            throw new Error(data.errorMsg || '未返回数据！');
        }

        return data;
    } catch (error) {
        return {data: T, errorCode: -1, errorMsg: error.message};
    }
}
