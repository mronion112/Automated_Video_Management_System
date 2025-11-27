package org.example.Controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.example.Module.Repsitory.Permission.PermissionRepository;

import java.io.IOException;

public class PermissionController {

    NetHttpTransport httpTransport;
    String tokenFilePath;
    String credentialFilePath;
    public PermissionController(){}

    PermissionRepository permissionRepository;

    public void connectCredentialPermission(NetHttpTransport httpTransport, String tokenFilePath, String credentialFilePath) throws IOException {
        System.out.println("connectCredentialPermission");
        this.httpTransport = httpTransport;
        this.tokenFilePath = tokenFilePath;
        this.credentialFilePath = credentialFilePath;
    }

    public Credential createCredentialPermission(){

        permissionRepository = new PermissionRepository(httpTransport,tokenFilePath,credentialFilePath);
        try {
            Credential credential =  permissionRepository.createCredentialPermission();
            System.out.println("Create CredentialPermission Sucessfull");
            return credential;
        } catch (IOException e) {
            System.out.println("Permission Creation Failed");
            System.out.println("Can't connect to GoogleAPI");
        }

        return null;

    }



}
