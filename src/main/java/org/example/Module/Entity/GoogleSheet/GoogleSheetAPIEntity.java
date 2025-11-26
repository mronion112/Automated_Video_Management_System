package org.example.Module.Entity.GoogleSheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;

public class GoogleSheetAPIEntity {

    private String sheetName;
    private String linkSheet;
    private String linkDriveId;
    private NetHttpTransport netHttpTransport;
    private Credential credential;
    private String credentialFilePath;
    private String tokenFilePath;


    public GoogleSheetAPIEntity(String sheetName, String linkSheet, String linkDriveId, NetHttpTransport netHttpTransport, Credential credential, String credentialFilePath, String tokenFilePath) {
        this.sheetName = sheetName;
        this.linkSheet = linkSheet;
        this.linkDriveId = linkDriveId;
        this.netHttpTransport = netHttpTransport;
        this.credential = credential;
        this.credentialFilePath = credentialFilePath;
        this.tokenFilePath = tokenFilePath;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getLinkSheet() {
        return linkSheet;
    }

    public void setLinkSheet(String linkSheet) {
        this.linkSheet = linkSheet;
    }

    public String getLinkDriveId() {
        return linkDriveId;
    }

    public void setLinkDriveId(String linkDriveId) {
        this.linkDriveId = linkDriveId;
    }

    public NetHttpTransport getNetHttpTransport() {
        return netHttpTransport;
    }

    public void setNetHttpTransport(NetHttpTransport netHttpTransport) {
        this.netHttpTransport = netHttpTransport;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
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
        return "GoogleSheetEntity{" +
                "linkSheet='" + linkSheet + '\'' +
                ", sheetName='" + sheetName + '\'' +
                ", linkDriveId='" + linkDriveId + '\'' +
                '}';
    }
}
