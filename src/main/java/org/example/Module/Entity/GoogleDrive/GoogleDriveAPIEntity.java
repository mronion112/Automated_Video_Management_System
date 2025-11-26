package org.example.Module.Entity.GoogleDrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;

public class GoogleDriveAPIEntity {

    private NetHttpTransport netHttpTransport;
    private String linkDriveId;
    private Credential credential;
    private String credentialFilePath;
    private String tokenFilePath;

    public GoogleDriveAPIEntity(NetHttpTransport netHttpTransport, String linkDriveId, String credentialFilePath, Credential credential, String tokenFilePath) {
        this.netHttpTransport = netHttpTransport;
        this.linkDriveId = linkDriveId;
        this.credentialFilePath = credentialFilePath;
        this.credential = credential;
        this.tokenFilePath = tokenFilePath;
    }

    public NetHttpTransport getNetHttpTransport() {
        return netHttpTransport;
    }

    public void setNetHttpTransport(NetHttpTransport netHttpTransport) {
        this.netHttpTransport = netHttpTransport;
    }

    public String getLinkDrive() {
        return linkDriveId;
    }

    public void setLinkDrive(String linkDrive) {
        this.linkDriveId = linkDrive;
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
        return "GoogleDriveEntity{" +
                ", linkDrive='" + linkDriveId + '\'' +
                '}';
    }
}
