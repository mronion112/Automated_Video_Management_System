package org.example;

import org.example.Controller.VideoController;
import org.example.Module.Entity.Data.Video.WebVideoEntity;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws IOException {

        VideoController videoController = new VideoController();

        HashMap<String, WebVideoEntity> listVideoWeb = videoController.scrappingVideoChannel("https://space.bilibili.com/1415932354/upload/video");

        System.out.println(listVideoWeb);



    }

}
