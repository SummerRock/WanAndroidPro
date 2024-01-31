export interface DataResp {
    articleList: any[]
    author: string
    children: any[]
    courseId: number
    cover: string
    desc: string
    id: number
    lisense: string
    lisenseLink: string
    name: string
    order: number
    parentChapterId: number
    type: number
    userControlSetTop: boolean
    visible: number
}

export interface CollectionData {
    curPage: number
    datas: CollectionItem[]
}

export interface CollectionItem {
    author: string
    chapterId: number
    chapterName: string
    courseId: number
    desc: string
    envelopePic: string
    id: number
    link: string
    niceDate: string
    origin: string
    originId: number
    publishTime: number
    title: string
    userId: number
    visible: number
    zan: number
}
