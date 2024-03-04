export interface CollectionData {
    curPage: number
    datas: CollectionItem[]
    offset: number
    over: boolean
    pageCount: number
    size: number
    total: number
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
