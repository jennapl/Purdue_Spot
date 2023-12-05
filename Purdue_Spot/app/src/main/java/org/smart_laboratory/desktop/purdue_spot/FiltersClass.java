package org.smart_laboratory.desktop.purdue_spot;


import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class FiltersClass {

    private String sId;
    private String sName;
    private static String sPrint, sLocation, sCrowd, sLight, sFood, sQuiet, sComp, sOpen;

    public FiltersClass() {
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

    public void setSelectedFood(RadioButton selected) {
        if (selected.isChecked()) {
            FiltersClass.sFood = "Yes";
        } else {
            FiltersClass.sFood = "No";
        }
    }

    public String getSelectedFood() {
        String print = FiltersClass.sFood;
        return print;
    }

    public void setSelectedQuiet(RadioButton selected) {
        if (selected.isChecked()) {
            FiltersClass.sQuiet = "Yes";
        } else {
            FiltersClass.sQuiet = "No";
        }
    }

    public String getSelectedQuiet() {
        String print = FiltersClass.sQuiet;
        return print;
    }

    public void setSelectedComp(RadioButton selected) {
        if (selected.isChecked()) {
            FiltersClass.sComp = "Yes";
        } else {
            FiltersClass.sComp = "No";
        }
    }

    public String getSelectedComp() {
        String print = FiltersClass.sComp;
        return print;
    }

    public void setSelectedOpen(RadioButton selected) {
        if (selected.isChecked()) {
            FiltersClass.sOpen = "True";
        } else {
            FiltersClass.sOpen = "False";
        }
    }

    public String getSelectedOpen() {
        String print = FiltersClass.sOpen;
        return print;
    }

}