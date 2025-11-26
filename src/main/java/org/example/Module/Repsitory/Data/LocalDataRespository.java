package org.example.Module.Repsitory.Data;

import org.example.Module.Entity.Data.LocalDataEntity;
import org.example.Module.Entity.Data.Story.LocalStoryEntity;

import java.util.HashMap;

public class LocalDataRespository {
    private HashMap<Integer, LocalDataEntity> listLocalData = new HashMap<>();

    //CURD : Creaate, Updaate, Read, Delete

    public void addNewData(LocalDataEntity localDataEntity) {
        listLocalData.put(listLocalData.size()+1, localDataEntity);
    }

    public LocalDataEntity getLocalDataById(int id) {
        return listLocalData.get(id);
    }

    public HashMap<Integer, LocalDataEntity> getListLocalData() {
        return listLocalData;
    }

    public void setListLocalData(HashMap<Integer, LocalDataEntity> listLocalData) {
        this.listLocalData = listLocalData;
    }

    public void updateListLocalData(HashMap<Integer, LocalDataEntity> listLocalData) {
        this.listLocalData = listLocalData;
    }
    public void updateListLocalDataById(int id, LocalDataEntity localDataEntity) {
        listLocalData.replace(id, localDataEntity);
    }
    public void deleteLocalDataById(int id) {
        listLocalData.remove(id);
    }


}
