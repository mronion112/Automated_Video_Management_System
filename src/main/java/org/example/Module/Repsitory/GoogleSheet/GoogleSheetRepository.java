package org.example.Module.Repsitory.GoogleDrive;

import org.example.Module.Entity.GoogleDrive.GoogleDriveDataEntity;
import org.example.Module.Entity.GoogleSheet.GoogleSheetDataEntity;

import java.util.HashMap;

public class GoogleSheetRepository {

    HashMap<Integer, GoogleSheetDataEntity> listGoogleSheetData = new HashMap<>();

    //CRUD : Create, Read, Update, Delete

    public HashMap<Integer, GoogleSheetDataEntity> getListGoogleSheetData() {
        return listGoogleSheetData;
    }
    public void setListGoogleSheetData(HashMap<Integer, GoogleSheetDataEntity> listGoogleSheetData) {
        this.listGoogleSheetData = listGoogleSheetData;
    }

    public void addGoogleSheetDataById(int idSheet, GoogleSheetDataEntity googleSheetDataEntity) {
        listGoogleSheetData.replace(idSheet, googleSheetDataEntity);
    }

    public GoogleSheetDataEntity getGoogleSheetDataById(int idSheet) {
        return listGoogleSheetData.get(idSheet);
    }

    public void updateGoogleSheetDataById(int idSheet, GoogleSheetDataEntity googleSheetDataEntity) {
        listGoogleSheetData.replace(idSheet, googleSheetDataEntity);
    }
    public void deleteGoogleSheetDataById(int idSheet) {
        listGoogleSheetData.remove(idSheet);
    }




}
