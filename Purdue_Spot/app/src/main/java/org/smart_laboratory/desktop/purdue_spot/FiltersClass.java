package org.smart_laboratory.desktop.purdue_spot;


import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class FiltersClass {

    private String sId;
    private String sName;
    private static String sPrint, sSound, sLocation, sCrowd, sLight;

    public FiltersClass() {
    }


    public void setSelectedSound(Spinner selected) {
        FiltersClass.sSound = selected.getSelectedItem().toString();
    }

    public String getSelectedSound() {
        String sound = FiltersClass.sSound;
        return sound;
    }

    public void setSelectedPrint(RadioButton selected) {
        if (selected.isChecked()) {
            FiltersClass.sPrint = "Yes";
        } else {
            FiltersClass.sPrint = "No";
        }
    }

    public String getSelectedPrint() {
        String print = FiltersClass.sPrint;
        return print;

    }

    public void setSelectedLocation(Spinner selected) {
        FiltersClass.sLocation = selected.getSelectedItem().toString();
    }

    public String getSelectedLocation() {
        String print = FiltersClass.sLocation;
        return print;
    }

    public void setSelectedCrowd(Spinner selected) {
        FiltersClass.sCrowd = selected.getSelectedItem().toString();
    }

    public String getSelectedCrowd() {
        String print = FiltersClass.sCrowd;
        return print;
    }

    public void setSelectedLight(Spinner selected) {
        FiltersClass.sLight = selected.getSelectedItem().toString();
    }

    public String getSelectedLight() {
        String print = FiltersClass.sLight;
        return print;
    }
}