package org.example.Controller;

import org.example.Module.Entity.Data.Video.LocalVideoEntity;
import org.example.Module.Entity.Data.Video.WebVideoEntity;
import org.example.Module.Repsitory.Data.Video.LocalVideoRepository;
import org.example.Module.Repsitory.Data.Video.WebVideoRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class VideoController {

    public enum Status {
        SUCCESS, NOT_FOUND_Url, ALREADY_EXISTS, Download_Error, CREATE_Folder_Video_FAILED, ERROR
    }

    public  HashMap<String, WebVideoEntity> listVideoWeb = new HashMap<>();
//    public  LocalDataRespository localDataRespository = new LocalDataRespository();
//    public  WebDataRespository webDataRespository = new WebVideoRepository();

    public String StorageFolderDir;
    public  WebVideoRepository webVideoRepository ;
    public  LocalVideoRepository localVideoRepository = new LocalVideoRepository();

    public int id = 1;

    public VideoController(String StorageFolderDir) {
        this.StorageFolderDir = StorageFolderDir;
        this.webVideoRepository = new WebVideoRepository(new File(StorageFolderDir));
    }


    public WebVideoEntity downLoadVideoFromName(String nameVideo, HashMap<String, WebVideoEntity> localVideoEntities, HashMap<String, WebVideoEntity> processJsonData, int idFolder) {
        try {
            return webVideoRepository.downloadData(nameVideo, localVideoEntities, processJsonData, idFolder);
        } catch (IOException e) {
            System.out.println("Can't connect to the server");
        } catch (InterruptedException e) {
            System.out.println("Download Error : InterruptedException");
        }

        return null;
    }


    public HashMap<String, WebVideoEntity> scrappingVideoChannel(String urlChannel){

         return webVideoRepository.scrappingVideoChannel(urlChannel);

    }

    public void writeToProcessJson(File processJson, HashMap<String, WebVideoEntity> listVideoDownloaded) {
        try {
            webVideoRepository.wirteProcessJson(processJson, listVideoDownloaded);
        }catch (IOException e){
            System.out.println("Error to write file processJson");
        }
    }


    public void removeVideoFormList(String nameVideo) throws IOException {
        webVideoRepository.deleteWebVideoByName(nameVideo);
    }

    public void scanDataVideoScrap(String dataVideoSrapDir){

    }

    public void downloadVideoToLocal(String ouPutFolder, String folderId){


    }













}
