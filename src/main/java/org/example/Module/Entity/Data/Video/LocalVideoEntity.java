package org.example.Module.Entity.Data.Video;

import com.github.kokorin.jaffree.nut.StreamHeader;
import org.example.Module.Entity.Data.LocalDataEntity;

public class LocalVideoEntity {
    private int idVideo;
    private String nameVideo;
    private String duration;
    private String viewCount;
    private String commentCount;
    private String timeRelease;

    public LocalVideoEntity(int idVideo, String nameVideo, String duration, String viewCount, String commentCount, String timeRelease) {
        this.idVideo = idVideo;
        this.nameVideo = nameVideo;
        this.duration = duration;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.timeRelease = timeRelease;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
        return "LocalStoryEntity{" +
                "idVideo='" + idVideo + '\'' +
                ", nameVideo='" + nameVideo + '\'' +
                ", duration='" + duration + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", timeRelease='" + timeRelease + '\'' +
                '}';
    }


}
