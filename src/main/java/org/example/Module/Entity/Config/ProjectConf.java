package org.example.Module.Entity.Config;


import java.io.File;
import java.util.List;

public class ProjectConf {

    //tokenFile alway in : ~/Properties/Token

    private String linkWebsite;
    private String outPutVideoDirectory;
    private String dataScrapDirectory;
    private String tableDataJson;
    private String linkDrive;
    private String linkSheet;
    private String nameSheet;
    private List<String> listCollumn;
    private String credentialFilePath;
    private String tokenFilePath;

    public ProjectConf(String linkWebsite, String outPutVideoDirectory, String dataScrapDirectory, String tableDataJson, String linkDrive, String linkSheet, String nameSheet, List<String> listCollumn, String credentialFilePath, String tokenFilePath) {
        this.linkWebsite = linkWebsite;
        this.outPutVideoDirectory = outPutVideoDirectory;
        this.dataScrapDirectory = dataScrapDirectory;
        this.tableDataJson = tableDataJson;
        this.linkDrive = linkDrive;
        this.linkSheet = linkSheet;
        this.nameSheet = nameSheet;
        this.listCollumn = listCollumn;
        this.credentialFilePath = credentialFilePath;
        this.tokenFilePath = tokenFilePath;
    }

    public String getLinkWebsite() {
        return linkWebsite;
    }

    public void setLinkWebsite(String linkWebsite) {
        this.linkWebsite = linkWebsite;
    }

    public String getOutPutVideoDirectory() {
        return outPutVideoDirectory;
    }

    public void setOutPutVideoDirectory(String outPutVideoDirectory) {
        this.outPutVideoDirectory = outPutVideoDirectory;
    }

    public String getDataScrapDirectory() {
        return dataScrapDirectory;
    }

    public void setDataScrapDirectory(String dataScrapDirectory) {
        this.dataScrapDirectory = dataScrapDirectory;
    }

    public String getTableDataJson() {
        return tableDataJson;
    }

    public void setTableDataJson(String tableDataJson) {
        this.tableDataJson = tableDataJson;
    }

    public String getLinkSheet() {
        return linkSheet;
    }

    public void setLinkSheet(String linkSheet) {
        this.linkSheet = linkSheet;
    }

    public String getLinkDrive() {
        return linkDrive;
    }

    public void setLinkDrive(String linkDrive) {
        this.linkDrive = linkDrive;
    }

    public String getNameSheet() {
        return nameSheet;
    }

    public void setNameSheet(String nameSheet) {
        this.nameSheet = nameSheet;
    }

    public List<String> getListCollumn() {
        return listCollumn;
    }

    public void setListCollumn(List<String> listCollumn) {
        this.listCollumn = listCollumn;
    }

    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    public void setCredentialFilePath(String credentialFilePath) {
        this.credentialFilePath = credentialFilePath;
    }

    public String getTokenFilePath() {
        return tokenFilePath;
    }

    public void setTokenFilePath(String tokenFilePath) {
        this.tokenFilePath = tokenFilePath;
    }

    @Override
    public String toString() {
        return "ProjectConf{" +
                "linkWebsite='" + linkWebsite + '\'' +
                ", outPutVideoDirectory='" + outPutVideoDirectory + '\'' +
                ", dataScrapDirectory='" + dataScrapDirectory + '\'' +
                ", tableDataJson='" + tableDataJson + '\'' +
                ", linkDrive='" + linkDrive + '\'' +
                ", linkSheet='" + linkSheet + '\'' +
                ", nameSheet='" + nameSheet + '\'' +
                ", listCollumn=" + listCollumn +
                ", credentialFilePath='" + credentialFilePath + '\'' +
                ", tokenFilePath='" + tokenFilePath + '\'' +
                '}';
    }
}
