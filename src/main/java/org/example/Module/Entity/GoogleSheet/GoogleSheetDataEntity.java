package org.example.Module.Entity.GoogleSheet;

import org.example.Module.Entity.Data.LocalDataEntity;
import org.example.Module.Entity.GoogleDrive.GoogleDriveDataEntity;

import java.util.HashMap;

public class GoogleSheetDataEntity {
    private GoogleDriveDataEntity googleDriveDataEntity;
    private int position;

    public GoogleSheetDataEntity(GoogleDriveDataEntity googleDriveDataEntity, int position) {
        this.googleDriveDataEntity = googleDriveDataEntity;
        this.position = position;
    }

    public GoogleDriveDataEntity getGoogleDriveDataEntity() {
        return googleDriveDataEntity;
    }

    public void setGoogleDriveDataEntity(GoogleDriveDataEntity googleDriveDataEntity) {
        this.googleDriveDataEntity = googleDriveDataEntity;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "GoogleSheetDataEntity{" +
                "googleDriveDataEntity=" + googleDriveDataEntity +
                ", position=" + position +
                '}';
    }
}
