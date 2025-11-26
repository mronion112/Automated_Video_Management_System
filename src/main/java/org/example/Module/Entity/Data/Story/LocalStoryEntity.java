package org.example.Module.Entity.Data.Story;

import org.example.Module.Entity.Data.LocalDataEntity;

import java.util.List;

public class LocalStoryEntity extends LocalDataEntity {
    private int idStory;
    private String nameStory;
    private List<String> types;
    private String author;
    private int numberChapter;
    private String status;

    public LocalStoryEntity(int idStory, String nameStory, List<String> types, String author, int numberChapter, String status) {
        super(idStory, nameStory);
        this.types = types;
        this.author = author;
        this.numberChapter = numberChapter;
        this.status = status;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(int numberChapter) {
        this.numberChapter = numberChapter;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "LocalStoryEntity{" +
                "idStory='" + idStory + '\'' +
                ", nameStory='" + nameStory + '\'' +
                ", types=" + types +
                ", author='" + author + '\'' +
                ", numberChapter=" + numberChapter +
                ", status='" + status + '\'' +
                '}';
    }
}
