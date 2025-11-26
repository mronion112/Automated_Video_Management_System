package org.example.Controller;

import java.io.File;
import java.io.IOException;

public class ProjectController {

    public static File currentProjectFolder;
    public static File projectDataFolder;
    public static File storageFolder;

    public static File googleDriveJson;
    public static File googleSheetJson;
    public static File processJson;

    public static File dataScrapJson;

    public enum Status {
        SUCCESS, NOT_FOUND, ALREADY_EXISTS, MKDIR_FAILED, CREATE_FILE_FAILED, ERROR
    }


    public static Status createNewProject(String projectPath, String typeNewProject) {
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


    public static Status getProject(String projectPath, String typeProject) {
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

    private static boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}