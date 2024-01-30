// 定义请求返回的数据类型
interface ApiResponse<T> {
    data: T;
    errorMsg?: string;
    errorCode: number
}

// 封装的 fetch 函数
export async function commonFetch<T>(url: string, options?: RequestInit): Promise<ApiResponse<T>> {
    try {
        const response: Response = await fetch(url, options);
        const data: ApiResponse<T> = await response.json();

        if (!response.ok || !data.data) {
            throw new Error(data.errorMsg || '请求失败');
        }

        return data;
    } catch (error) {
        return {data: T, errorCode: -1, errorMsg: error.message};
    }
}
