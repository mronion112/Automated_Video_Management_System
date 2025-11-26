package org.example.Module.Entity.Data.Video;

import org.example.Module.Entity.Data.WebDataEntity;

public class WebVideoEntity  {
    private String urlVideo;
    private String nameVideo;
    private String urlThumbnail;
    private String duration;
    private String viewCount;
    private String commentCount;
    private String timeRelease;

    public WebVideoEntity(String urlVideo, String urlThumbnail, String nameVideo, String duration, String viewCount, String commentCount, String timeRelease) {
        this.urlVideo = urlVideo;
        this.nameVideo = nameVideo;
        this.urlThumbnail = urlThumbnail;
        this.duration = duration;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.timeRelease = timeRelease;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getTimeRelease() {
        return timeRelease;
    }

    public void setTimeRelease(String timeRelease) {
        this.timeRelease = timeRelease;
    }

    @Override
    public String toString() {
        return "WebStoryEntity{" +
                "urlVideo='" + urlVideo + '\'' +
                ", urlThumbnail='" + urlThumbnail + '\'' +
                ", nameVideo='" + nameVideo + '\'' +
                ", duration='" + duration + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", timeRelease='" + timeRelease + '\'' +
                "}\n";
    }
}
