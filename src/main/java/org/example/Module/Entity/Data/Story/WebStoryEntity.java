package org.example.Module.Entity.Data.Story;

import org.example.Module.Entity.Data.WebDataEntity;

import java.util.List;

public class WebStoryEntity extends WebDataEntity {
    private String urlStory;
    private String nameStory;
    private List<String> types;
    private String author;
    private int numberChapter;
    private String status;


    public WebStoryEntity(String urlStory, String nameStory, List<String> types, String author, int numberChapter, String status) {
        super(urlStory, nameStory);
        this.types = types;
        this.author = author;
        this.numberChapter = numberChapter;
        this.status = status;
    }

    public String getUrlStory() {
        return urlStory;
    }

    public void setUrlStory(String urlStory) {
        this.urlStory = urlStory;
    }

    public String getNameStory() {
        return nameStory;
    }

    public void setNameStory(String nameStory) {
        this.nameStory = nameStory;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(int numberChapter) {
        this.numberChapter = numberChapter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WebStoryEntity{" +
                "urlStory='" + urlStory + '\'' +
                ", nameStory='" + nameStory + '\'' +
                ", types=" + types +
                ", author='" + author + '\'' +
                ", numberChapter=" + numberChapter +
                ", status='" + status + '\'' +
                '}';
    }
}
