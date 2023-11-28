package org.smart_laboratory.desktop.purdue_spot;


import android.widget.RadioButton;
import android.widget.Spinner;

public class FiltersClass {

    private String sId;
    private String sName;
    private static String sPrint;
    private static String sSound;

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
}