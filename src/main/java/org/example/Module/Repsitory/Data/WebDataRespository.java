package org.example.Module.Repsitory.Data;

import org.example.Module.Entity.Data.WebDataEntity;

import java.io.IOException;
import java.util.HashMap;

public class WebDataRespository {
    private HashMap<String, WebDataEntity> listWebData = new HashMap<>();


    //CRUD : Create, Read, Update, Delete
    public void addListWebData(String nameData, WebDataEntity webDataEntity){
            listWebData.put(nameData, webDataEntity);
    }

    public WebDataEntity getWebDataByName(String nameWebVideo) throws IOException
    {
        return listWebData.get(nameWebVideo);
    }

    public HashMap<String, WebDataEntity> getListWebData(){
        return listWebData;
    }

    public void updateWebDataByName(String nameWebVideo, WebDataEntity webDataEntity) throws IOException
    {
        listWebData.replace(nameWebVideo, webDataEntity);
    }
    public void updateWebData(HashMap<String, WebDataEntity> listWebData){
        this.listWebData = listWebData;
    }

    public void deleteWebDataByName(String nameWebVideo) throws IOException{
        listWebData.remove(nameWebVideo);
    }






}
