package org.example.Module.Entity.Data;

public class WebDataEntity {
    private String urlData;
    private String nameData;

    public WebDataEntity(String urlData, String nameData) {
        this.urlData = urlData;
        this.nameData = nameData;
    }

    public String getNameData() {
        return nameData;
    }

    public void setNameData(String nameData) {
        this.nameData = nameData;
    }

    public String getUrlData() {
        return urlData;
    }

    public void setUrlData(String urlData) {
        this.urlData = urlData;
    }

    @Override
    public String toString() {
        return "WebDataEntity{" +
                "urlData='" + urlData + '\'' +
                ", nameData='" + nameData + '\'' +
                '}';
    }
}
