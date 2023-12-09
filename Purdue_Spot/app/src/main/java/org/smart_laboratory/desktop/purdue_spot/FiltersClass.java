package org.smart_laboratory.desktop.purdue_spot;

import android.widget.RadioButton;
import android.widget.Spinner;

/*
FiltersClass
This class is the getters and setters for all of the filters
that were selected by the user. This is executed when the search button
is clicked in the FiltersActivity.

The values set in this equal to the strings in the DB for the
SQL queries in the ListActivity.
*/
public class FiltersClass {

    private static String sPrint, sLocation, sCrowd, sLight, sFood, sQuiet, sComp, sOpen;

    public FiltersClass() {
    }

    // Sets the value of the printer filter
    public void setSelectedPrint(RadioButton selected) {
        if (selected.isChecked()) {
            FiltersClass.sPrint = "Yes";
        } else {
            FiltersClass.sPrint = "No";
        }
    }

    // Gets the value of the printer filter
    public String getSelectedPrint() {
        String print = FiltersClass.sPrint;
        return print;

    }

    // Sets the value of the location filter
    public void setSelectedLocation(Spinner selected) {
        FiltersClass.sLocation = selected.getSelectedItem().toString();
    }

    // get the value of the location filter
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