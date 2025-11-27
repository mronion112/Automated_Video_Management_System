package org.example.Module.Repsitory.GoogleSheet;

import org.example.Module.Entity.GoogleDrive.GoogleDriveDataEntity;

import java.util.HashMap;

public class GoogleDriveRepository {

    //Integer here will be the id of folder on Drive = id Data
    HashMap<Integer, GoogleDriveDataEntity> listGoogleDriveData = new HashMap<>();


    //CRUD : Create, Read, Update, Delete
    public HashMap<Integer, GoogleDriveDataEntity> getListGoogleDriveData() {
        return listGoogleDriveData;
    }

    public void addNewGoogleDriveData(GoogleDriveDataEntity googleDriveDataEntity) {
        listGoogleDriveData.put(listGoogleDriveData.size()+1, googleDriveDataEntity);
    }

    public GoogleDriveDataEntity getGoogleDriveDataById(int idData){
        return  listGoogleDriveData.get(idData);
    }

    public void updateGoogleDriveDataById(int idData,GoogleDriveDataEntity googleDriveDataEntity) {
        listGoogleDriveData.replace(idData,googleDriveDataEntity);

    }

    public void deleteGoogleDriveDataById(int idData){
        listGoogleDriveData.remove(idData);
    }


}
