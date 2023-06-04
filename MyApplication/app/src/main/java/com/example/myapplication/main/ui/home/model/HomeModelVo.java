package com.example.myapplication.main.ui.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModelVo {

    @SerializedName("curPage")
    private Integer curPage;
    @SerializedName("datas")
    private List<DatasDTO> datas;
    @SerializedName("offset")
    private Integer offset;
    @SerializedName("over")
    private Boolean over;
    @SerializedName("pageCount")
    private Integer pageCount;
    @SerializedName("size")
    private Integer size;
    @SerializedName("total")
    private Integer total;

    public static class DatasDTO {
        @SerializedName("adminAdd")
        private Boolean adminAdd;
        @SerializedName("apkLink")
        private String apkLink;
        @SerializedName("audit")
        private Integer audit;
        @SerializedName("author")
        private String author;
        @SerializedName("canEdit")
        private Boolean canEdit;
        @SerializedName("chapterId")
        private Integer chapterId;
        @SerializedName("chapterName")
        private String chapterName;
        @SerializedName("collect")
        private Boolean collect;
        @SerializedName("courseId")
        private Integer courseId;
        @SerializedName("desc")
        private String desc;
        @SerializedName("descMd")
        private String descMd;
        @SerializedName("envelopePic")
        private String envelopePic;
        @SerializedName("fresh")
        private Boolean fresh;
        @SerializedName("host")
        private String host;
        @SerializedName("id")
        private Integer id;
        @SerializedName("isAdminAdd")
        private Boolean isAdminAdd;
        @SerializedName("link")
        private String link;
        @SerializedName("niceDate")
        private String niceDate;
        @SerializedName("niceShareDate")
        private String niceShareDate;
        @SerializedName("origin")
        private String origin;
        @SerializedName("prefix")
        private String prefix;
        @SerializedName("projectLink")
        private String projectLink;
        @SerializedName("publishTime")
        private Long publishTime;
        @SerializedName("realSuperChapterId")
        private Integer realSuperChapterId;
        @SerializedName("selfVisible")
        private Integer selfVisible;
        @SerializedName("shareDate")
        private Long shareDate;
        @SerializedName("shareUser")
        private String shareUser;
        @SerializedName("superChapterId")
        private Integer superChapterId;
        @SerializedName("superChapterName")
        private String superChapterName;
        @SerializedName("tags")
        private List<?> tags;
        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private Integer type;
        @SerializedName("userId")
        private Integer userId;
        @SerializedName("visible")
        private Integer visible;
        @SerializedName("zan")
        private Integer zan;
    }
}
