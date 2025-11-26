package org.example.Controller;

import org.example.Module.Entity.Data.Video.WebVideoEntity;
import org.example.Module.Repsitory.Data.LocalDataRespository;
import org.example.Module.Repsitory.Data.Video.LocalVideoRepository;
import org.example.Module.Repsitory.Data.Video.WebVideoRepository;
import org.example.Module.Repsitory.Data.WebDataRespository;
import org.example.Service.VideoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoController {

    public enum Status {
        SUCCESS, NOT_FOUND_Url, ALREADY_EXISTS, Download_Error, CREATE_Folder_Video_FAILED, ERROR
    }

    public  HashMap<String, WebVideoEntity> listVideoWeb = new HashMap<>();
    public  LocalDataRespository localDataRespository = new LocalDataRespository();
    public  WebDataRespository webDataRespository = new WebVideoRepository();


    public  WebVideoRepository webVideoRepository = new WebVideoRepository();
    public  LocalVideoRepository localVideoRepository = new LocalVideoRepository();

    public VideoController() {}



    public HashMap<String, WebVideoEntity> scrappingVideoChannel(String urlChannel){

         return webVideoRepository.scrappingVideoChannel(urlChannel);

    }


    public void removeVideoFormList(String nameVideo) throws IOException {
        webVideoRepository.deleteWebVideoByName(nameVideo);
    }










}
