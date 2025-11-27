package org.example.Controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.example.Module.Entity.Data.LocalDataEntity;
import org.example.Module.Entity.Data.Story.LocalStoryEntity;
import org.example.Module.Entity.Data.Story.WebStoryEntity;
import org.example.Module.Entity.Data.Video.LocalVideoEntity;
import org.example.Module.Entity.Data.Video.WebVideoEntity;
import org.example.Module.Entity.Data.WebDataEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ProjectController {

    public File currentProjectFolder;
    public File projectDataFolder;
    public File storageFolder;

    public File googleDriveJson;
    public File googleSheetJson;
    public File processJson;

    public File dataScrapJson;
    public String typeProject;

    private String credentialFilePath;
    private String tokenFilePath = "GoogleAPI";

    public enum Status {
        SUCCESS, NOT_FOUND, ALREADY_EXISTS, MKDIR_FAILED, CREATE_FILE_FAILED, ERROR
    }


    public HashMap<String, WebVideoEntity> listWebVideoEntities;
    public HashMap<String, LocalVideoEntity> listLocalVideoEntities;

    public HashMap<String, WebStoryEntity> listWebStoryEntities;
    public HashMap<String, LocalStoryEntity> listLocalStoryEntities;

    public ProjectController(){}

    public void connectDataBaseJson() {
        Gson gson = new Gson();

        try {

            FileReader processJsonReader = new FileReader(processJson);
            FileReader dataScrapJsonReader = new FileReader(dataScrapJson);
            if (typeProject.equals("Video")) {
                Type typeWebVideo = new TypeToken<HashMap<String, WebVideoEntity>>() {
                }.getType();
                Type typeLocalVideo = new TypeToken<HashMap<String, LocalVideoEntity>>() {
                }.getType();

                listWebVideoEntities = gson.fromJson(processJsonReader, typeWebVideo);
                listLocalVideoEntities = gson.fromJson(dataScrapJsonReader, typeLocalVideo);

            } else {
                Type typeWebStory = new TypeToken<HashMap<String, WebStoryEntity>>() {
                }.getType();
                Type typeLocalStory = new TypeToken<HashMap<String, LocalStoryEntity>>() {
                }.getType();

                listWebStoryEntities = gson.fromJson(processJsonReader, typeWebStory);
                listLocalStoryEntities = gson.fromJson(dataScrapJsonReader, typeLocalStory);


            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        catch (JsonSyntaxException e) {
            System.out.println("JsonSyntaxException");
        }
    }

    public Status connectGoogleAPI() {

        File listFile = new File(tokenFilePath);

        for(File file : listFile.listFiles()){

            if(file.getName().endsWith(".json")){

                credentialFilePath = file.getAbsolutePath();
            }

        }

        return Status.SUCCESS;


    }



    public  Status createNewProject(String projectPath, String typeNewProject) {
        typeProject = typeNewProject;
        currentProjectFolder = new File(projectPath);
        projectDataFolder = new File(currentProjectFolder, "ProjectData");

        googleDriveJson = new File(projectDataFolder, "GoogleDrive.json");
        googleSheetJson = new File(projectDataFolder, "GoogleSheet.json");
        processJson = new File(projectDataFolder, "Process.json");

        if (typeNewProject.equalsIgnoreCase("Video")) {
            storageFolder = new File(currentProjectFolder, "VideoStorage");
            dataScrapJson = new File(storageFolder, "DataVideoScrap.json");

        } else if (typeNewProject.equalsIgnoreCase("Story")) {
            storageFolder = new File(currentProjectFolder, "StoryStorage");
            dataScrapJson = new File(storageFolder, "DataStoryScrap.json");

        } else {
            System.out.println("Unknown Project Type: " + typeNewProject);
            return Status.ERROR;
        }

        if (currentProjectFolder.exists()) {
            return Status.ALREADY_EXISTS;
        }

        if (!currentProjectFolder.mkdirs()) return Status.MKDIR_FAILED;
        if (!projectDataFolder.mkdir()) return Status.MKDIR_FAILED;
        if (!storageFolder.mkdir()) return Status.MKDIR_FAILED;


        if (!createFile(googleDriveJson)) return Status.CREATE_FILE_FAILED;
        if (!createFile(googleSheetJson)) return Status.CREATE_FILE_FAILED;
        if (!createFile(processJson)) return Status.CREATE_FILE_FAILED;

        if (!createFile(dataScrapJson)) return Status.CREATE_FILE_FAILED;

        System.out.println("Project created successfully at: " + currentProjectFolder.getAbsolutePath());
        return Status.SUCCESS;
    }


    public Status getProject(String projectPath, String typeProject) {
        this.typeProject = typeProject;
        currentProjectFolder = new File(projectPath);

        if (!currentProjectFolder.exists() || !currentProjectFolder.isDirectory()) {
            return Status.NOT_FOUND;
        }

        projectDataFolder = new File(currentProjectFolder, "ProjectData");
        googleDriveJson = new File(projectDataFolder, "GoogleDrive.json");
        googleSheetJson = new File(projectDataFolder, "GoogleSheet.json");
        processJson = new File(projectDataFolder, "Process.json");

        if (!projectDataFolder.exists()) return Status.NOT_FOUND;

        if (typeProject.equalsIgnoreCase("Video")) {
            storageFolder = new File(currentProjectFolder, "VideoStorage");
            dataScrapJson = new File(storageFolder, "DataVideoScrap.json");

        } else if (typeProject.equalsIgnoreCase("Story")) {
            storageFolder = new File(currentProjectFolder, "StoryStorage");
            dataScrapJson = new File(storageFolder, "DataStoryScrap.json");

        } else {
            return Status.ERROR;
        }

        if (!storageFolder.exists()) {
            System.out.println("Missing storage folder!");
            return Status.NOT_FOUND;
        }


        return Status.SUCCESS;
    }

    private boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, LocalStoryEntity> getListLocalStoryEntities() {
        return listLocalStoryEntities;
    }

    public void setListLocalStoryEntities(HashMap<String, LocalStoryEntity> listLocalStoryEntities) {
        this.listLocalStoryEntities = listLocalStoryEntities;
    }

    public HashMap<String, WebStoryEntity> getListWebStoryEntities() {
        return listWebStoryEntities;
    }

    public void setListWebStoryEntities(HashMap<String, WebStoryEntity> listWebStoryEntities) {
        this.listWebStoryEntities = listWebStoryEntities;
    }

    public HashMap<String, LocalVideoEntity> getListLocalVideoEntities() {
        return listLocalVideoEntities;
    }

    public void setListLocalVideoEntities(HashMap<String, LocalVideoEntity> listLocalVideoEntities) {
        this.listLocalVideoEntities = listLocalVideoEntities;
    }

    public HashMap<String, WebVideoEntity> getListWebVideoEntities() {
        return listWebVideoEntities;
    }

    public void setListWebVideoEntities(HashMap<String, WebVideoEntity> listWebVideoEntities) {
        this.listWebVideoEntities = listWebVideoEntities;
    }

    public String getTypeProject() {
        return typeProject;
    }

    public void setTypeProject(String typeProject) {
        this.typeProject = typeProject;
    }

    public  File getCurrentProjectFolder() {
        return currentProjectFolder;
    }

    public  void setCurrentProjectFolder(File currentProjectFolder) {
        this.currentProjectFolder = currentProjectFolder;
    }

    public  File getProjectDataFolder() {
        return projectDataFolder;
    }

    public  void setProjectDataFolder(File projectDataFolder) {
        this.projectDataFolder = projectDataFolder;
    }

    public  File getStorageFolder() {
        return storageFolder;
    }

    public  void setStorageFolder(File storageFolder) {
        this.storageFolder = storageFolder;
    }

    public  File getGoogleDriveJson() {
        return googleDriveJson;
    }

    public  void setGoogleDriveJson(File googleDriveJson) {
        this.googleDriveJson = googleDriveJson;
    }

    public  File getGoogleSheetJson() {
        return googleSheetJson;
    }

    public  void setGoogleSheetJson(File googleSheetJson) {
        this.googleSheetJson = googleSheetJson;
    }

    public  File getProcessJson() {
        return processJson;
    }

    public  void setProcessJson(File processJson) {
        this.processJson = processJson;
    }

    public  File getDataScrapJson() {
        return dataScrapJson;
    }

    public  void setDataScrapJson(File dataScrapJson) {
        this.dataScrapJson = dataScrapJson;
    }

    public String getTokenFilePath() {
        return tokenFilePath;
    }

    public void setTokenFilePath(String tokenFilePath) {
        this.tokenFilePath = tokenFilePath;
    }

    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    public void setCredentialFilePath(String credentialFilePath) {
        this.credentialFilePath = credentialFilePath;
    }
}