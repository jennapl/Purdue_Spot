package org.smart_laboratory.desktop.purdue_spot;

public class myDBModel {
    private String sId, sName, sPrint, sSound, sLocation;

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


    //SETTERS
    /*public void setSound(Spinner selected){
        sSound = selected.getSelectedItem().toString();
    }

    public String getSound(){
        String sound = FiltersClass.sSound;
        return sound;
    }

    public void setPrint(RadioButton selected){
        if (selected.isChecked()){
            sPrint = "Yes";
        } else {
            sPrint = "No";
        }
    }

    public String getPrint(){
        String print = FiltersClass.sPrint;
        return print;
    }*/

}
