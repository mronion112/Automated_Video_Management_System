package org.example;

import com.google.api.client.http.javanet.NetHttpTransport;
import org.example.Controller.PermissionController;
import org.example.Controller.ProjectController;
import org.example.Controller.VideoController;
import org.example.Module.Entity.Data.Video.LocalVideoEntity;
import org.example.Module.Entity.Data.Video.WebVideoEntity;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        ProjectController projectController = new ProjectController();
        projectController.setTypeProject("Video");


        File storageFolder = new File("ProjectData/VideoProject/Project1/VideoStorage");
        projectController.setStorageFolder(storageFolder);

        File processJson = new File("ProjectData/VideoProject/Project1/ProjectData/Process.json");
        projectController.setProcessJson(processJson);

        File DataVideoScrapJson = new File("ProjectData/VideoProject/Project1/VideoStorage/DataVideoScrap.json");
        projectController.setDataScrapJson(DataVideoScrapJson);

        projectController.connectDataBaseJson();
        HashMap<String, LocalVideoEntity> dataScrapJsonData = projectController.getListLocalVideoEntities();
        HashMap<String, WebVideoEntity> processJsonData = projectController.getListWebVideoEntities();

        projectController.connectGoogleAPI();
        String tokenFilePath = projectController.getTokenFilePath();
        String credentialPath = projectController.getCredentialFilePath();


        NetHttpTransport transport = new NetHttpTransport();
        PermissionController permissionController = new PermissionController();
        permissionController.connectCredentialPermission(transport,  tokenFilePath, credentialPath);
        permissionController.createCredentialPermission();

        VideoController videoController = new VideoController(storageFolder.getAbsolutePath());

        HashMap<String, WebVideoEntity> listVideoDownloaded = new HashMap<>();



        HashMap<String, WebVideoEntity> listVideoWeb = videoController.scrappingVideoChannel("https://space.bilibili.com/1415932354/upload/video");
        System.out.println(listVideoWeb);


        final AtomicInteger idFolderCounter = new AtomicInteger(0);
        for(String nameVideo : listVideoWeb.keySet() ){
//            System.out.println(listVideoWeb.get(nameVideo));
//            listVideoDownloaded.put(nameVideo,videoController.downLoadVideoFromName(nameVideo, listVideoDownloaded, listVideoWeb));

            executorService.execute(() -> {
                int currentId = idFolderCounter.incrementAndGet();
                System.out.println(listVideoWeb.get(nameVideo));
                listVideoDownloaded.put(nameVideo, videoController.downLoadVideoFromName(nameVideo, listVideoDownloaded, listVideoWeb, currentId));

            });

        }

        executorService.shutdown();





        videoController.writeToProcessJson(processJson, listVideoWeb);







    }

}
