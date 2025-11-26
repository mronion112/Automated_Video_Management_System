package org.example.Module.Entity.Config;

import java.io.File;
import java.util.List;

public class UserConf {

    private String nameUser;
    private String front;
    private String colorSetting;
    private boolean reSize;             // If user open full screan this option will true and else set the GUI in middle
    private List<String> reOpenTab;     // If there didn't have any tab on user GUI this will be false
    // else each time user open this , autoOpen will open the last process for user


    public UserConf(String nameUser, String front, boolean reSize, String colorSetting, List<String> reOpenTab) {
        this.nameUser = nameUser;
        this.front = front;
        this.reSize = reSize;
        this.colorSetting = colorSetting;
        this.reOpenTab = reOpenTab;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getColorSetting() {
        return colorSetting;
    }

    public void setColorSetting(String colorSetting) {
        this.colorSetting = colorSetting;
    }

    public boolean isReSize() {
        return reSize;
    }

    public void setReSize(boolean reSize) {
        this.reSize = reSize;
    }

    public List<String> getReOpenTab() {
        return reOpenTab;
    }

    public void setReOpenTab(List<String> reOpenTab) {
        this.reOpenTab = reOpenTab;
    }

    @Override
    public String toString() {
        return "UserConf{" +
                "nameUser='" + nameUser + '\'' +
                ", front='" + front + '\'' +
                ", colorSetting='" + colorSetting + '\'' +
                ", reSize=" + reSize +
                ", reOpenTab=" + reOpenTab +
                '}';
    }
}

