package org.smart_laboratory.desktop.purdue_spot;

public class myDBModel {
    private String sId, sName, sPrint, sSound, sLocation, sCrowd, sLight;

    public myDBModel() {
    }

    // Constructor with parameters - is this needed?
    public myDBModel(String spotID, String spotName, String spotPrinting, String spotSoundLevel, String spotLocation) {
        this.sId = spotID;
        this.sName = spotName;
        this.sPrint = spotPrinting;
        this.sSound = spotSoundLevel;
        this.sLocation = spotLocation;
    }

    public String getId() {
        return sId;
    }

    public void setId(String id) {
        this.sId = id;
    }

    public String getSound() {
        return sSound;
    }

    public void setSound(String sound) {

        this.sSound = sound;
    }

    public String getPrint() {
        return sPrint;
    }

    public void setPrint(String print) {
        this.sPrint = print;
    }

    public String getName() {
        return sName;
    }

    public void setName(String name) {
        this.sName = name;
    }

    public String getLocation() {
        return sLocation;
    }

    public void setLocation(String loc) {
        this.sLocation = loc;
    }

    public String getCrowd() {
        return sCrowd;
    }

    public void setCrowd(String crowd) {
        this.sCrowd = crowd;
    }

    public String getLight() {
        return sLight;
    }

    public void setLight(String light) {
        this.sLight = light;
    }

}
