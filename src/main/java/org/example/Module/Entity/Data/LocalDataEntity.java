package org.example.Module.Entity.Data;

public class LocalDataEntity {
    private int idData;
    private String nameData;

    public LocalDataEntity(int idData, String nameData ) {
        this.nameData = nameData;
        this.idData = idData;
    }

    public int getIdData() {
        return idData;
    }
    public void setIdData(int idData) {
        this.idData = idData;
    }
    public String getNameData() {
        return nameData;
    }
    public void setNameData(String nameData) {
        this.nameData = nameData;
    }

    @Override
    public String toString() {
        return "LocalDataEntity{" +
                "idData=" + idData +
                ", nameData='" + nameData + '\'' +
                '}';
    }
}
