package org.example.Module.Entity.GoogleDrive;

import org.example.Module.Entity.Data.LocalDataEntity;

import java.util.HashMap;
import java.util.Map;

public class GoogleDriveDataEntity {
    private LocalDataEntity localDataEntity;
    private String linkDrive;

    public GoogleDriveDataEntity(LocalDataEntity localDataEntity, String linkDrive) {
        this.localDataEntity = localDataEntity;
        this.linkDrive = linkDrive;
    }

    public LocalDataEntity getLocalDataEntity() {
        return localDataEntity;
    }

    public void setLocalDataEntity(LocalDataEntity localDataEntity) {
        this.localDataEntity = localDataEntity;
    }

    public String getLinkDrive() {
        return linkDrive;
    }

    public void setLinkDrive(String linkDrive) {
        this.linkDrive = linkDrive;
    }

    @Override
    public String toString() {
        return "GoogleDriveDataEntity{" +
                "localDataEntity=" + localDataEntity +
                ", linkDrive='" + linkDrive + '\'' +
                '}';
    }
}
