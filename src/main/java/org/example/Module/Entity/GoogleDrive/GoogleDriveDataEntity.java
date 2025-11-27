package org.example.Module.Entity.GoogleDrive;

import org.example.Module.Entity.Data.LocalDataEntity;

import java.util.HashMap;
import java.util.Map;

public class GoogleDriveDataEntity {
    private LocalDataEntity localDataEntity;

    public GoogleDriveDataEntity(LocalDataEntity localDataEntity) {
        this.localDataEntity = localDataEntity;
    }

    public LocalDataEntity getLocalDataEntity() {
        return localDataEntity;
    }

    public void setLocalDataEntity(LocalDataEntity localDataEntity) {
        this.localDataEntity = localDataEntity;
    }

    @Override
    public String toString() {
        return "GoogleDriveDataEntity{" +
                "localDataEntity=" + localDataEntity +
                '}';
    }
}
