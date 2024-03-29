// 定义请求返回的数据类型
import NetworkModule from "./native/module";

export enum NetStatus {
    LOADING,
    SUCCESS,
    FAILED
}

interface ApiResponse<T> {
    data: T;
    errorMsg?: string;
    errorCode: number
}

export class NetworkError extends Error {
    // 新增一个错误码属性
    private readonly errorCode: number;

    constructor(message: string, errorCode: number) {
        // 调用父类的构造函数，并传入错误消息
        super(message);


        // 设置错误的名称，这是可选的
        this.name = 'NetworkError';

        // 设置错误码
        this.errorCode = errorCode;

        // 设置堆栈跟踪信息，这也是可选的
        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, NetworkError);
        }
    }

    // 添加一个方法用于获取错误码
    getErrorCode(): number {
        return this.errorCode;
    }
}


// 封装的 fetch 函数
export async function commonFetch<T>(url: string, options?: RequestInit): Promise<ApiResponse<T>> {
    const {cookie} = await NetworkModule.requestNetworkCookieStr('test_token');
    // console.log('cookie-tag', cookie)
    const response: Response = await fetch(url, {
        ...options,
        headers: {
            'Content-Type': 'application/json',
            'Cookie': cookie, // 在这里添加Cookie
        },
        credentials: 'omit',
        // 在 Fetch API 中，credentials 是一个可选的配置参数，用于控制在请求中是否包含凭据（credentials）。
        // 凭据通常包括 Cookie、HTTP 认证以及客户端 SSL 证书等信息。credentials 参数有三个可能的值：
        // "omit"（默认值）： 表示不包含任何凭据信息。在跨域请求时，浏览器默认不会发送 Cookie 等凭据信息，以确保安全性。
        // "same-origin"： 表示在同源请求中包含凭据信息，但是在跨域请求中不包含。这可以确保在同源请求中能够使用 Cookie 等凭据，但不会在跨域请求中泄漏凭据。
        // "include"： 表示在同源请求和跨域请求中都包含凭据信息。这意味着浏览器会发送包括 Cookie 在内的凭据信息，确保在跨域请求时可以使用凭据。
        // 这里使用omit omit能发送完整的cookie到网络请求中，其他的不可以，原因未知
    });
    const data: ApiResponse<T> = await response.json();

    if (!response.ok) {
        console.error('network-err', 'request-failed')
        throw new NetworkError(data.errorMsg || '网络请求失败', data.errorCode);
    }

    if (data.errorCode === -1001) {
        console.error('network-err', 'login-failed')
        throw new NetworkError(data.errorMsg || '请先登录！', data.errorCode);
    }

    if (!data.data) {
        console.error('network-err', 'no-data')
        throw new NetworkError(data.errorMsg || '未返回数据！', -1);
    }

    return data;
}
