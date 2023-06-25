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

    public Integer getCurPage() {
        return curPage;
    }

    public List<DatasDTO> getDatas() {
        return datas;
    }

    public Integer getOffset() {
        return offset;
    }

    public Boolean getOver() {
        return over;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotal() {
        return total;
    }

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

        public Boolean getAdminAdd() {
            return adminAdd;
        }

        public String getLink() {
            return link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public Long getPublishTime() {
            return publishTime;
        }

        public Integer getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public Integer getSelfVisible() {
            return selfVisible;
        }

        public Long getShareDate() {
            return shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public Integer getSuperChapterId() {
            return superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public List<?> getTags() {
            return tags;
        }

        public String getTitle() {
            return title;
        }

        public Integer getType() {
            return type;
        }

        public Integer getUserId() {
            return userId;
        }

        public Integer getVisible() {
            return visible;
        }

        public Integer getZan() {
            return zan;
        }

        public String getApkLink() {
            return apkLink;
        }

        public Integer getAudit() {
            return audit;
        }

        public String getAuthor() {
            return author;
        }

        public Boolean getCanEdit() {
            return canEdit;
        }

        public Integer getChapterId() {
            return chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public Boolean getCollect() {
            return collect;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public String getDesc() {
            return desc;
        }

        public String getDescMd() {
            return descMd;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public Boolean getFresh() {
            return fresh;
        }

        public String getHost() {
            return host;
        }

        public Integer getId() {
            return id;
        }
    }
}
