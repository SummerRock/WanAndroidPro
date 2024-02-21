export interface MessageData {
    curPage: number
    datas: MessageItem[]
    offset: number
    over: boolean
    pageCount: number
    size: number
    total: number
}

export interface MessageItem {
    category: number;
    date: number;
    fromUser: string;
    fromUserId: number;
    fullLink: string;
    id: number;
    isRead: number;
    link: string;
    message: string;
    niceDate: string;
    tag: string;
    title: string;
    userId: number;
}
