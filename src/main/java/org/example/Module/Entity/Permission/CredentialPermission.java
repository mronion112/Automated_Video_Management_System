package org.example.Module.Entity.Permission;

import com.google.api.client.http.javanet.NetHttpTransport;

//tokenFile alway in : ~/Properties/Token

public class CredentialPermission {
    private NetHttpTransport httpTransport;
    private String credentialFilePath;
    private String tokenFilePath;


    public CredentialPermission(NetHttpTransport httpTransport, String tokenFilePath, String credentialFilePath) {
        this.httpTransport = httpTransport;
        this.credentialFilePath = credentialFilePath;
        this.tokenFilePath = tokenFilePath;
    }

    public NetHttpTransport getHttpTransport() {
        return httpTransport;
    }

    public void setHttpTransport(NetHttpTransport httpTransport) {
        this.httpTransport = httpTransport;
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
        return "CredentialPermission{" +
                "httpTransport=" + httpTransport +
                ", credentialFilePath='" + credentialFilePath + '\'' +
                ", tokenFilePath='" + tokenFilePath + '\'' +
                '}';
    }
}
