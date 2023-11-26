package org.smart_laboratory.desktop.purdue_spot;

import android.widget.RadioButton;
import android.widget.Spinner;

public class FiltersClass {
    static String sPrint;
    static String sSound;

    //SETTERS
    public void setSound(Spinner selected){
        sSound = selected.getSelectedItem().toString();
    }
    public void setPrint(RadioButton selected){
        if (selected.isChecked()){
            sPrint = "Yes";
        } else {
            sPrint = "No";
        }
    }

    //GETTERS
    public String getSound(){
        String sound = FiltersClass.sSound;
        return sound;
    }

    public String getPrint(){
        String print = FiltersClass.sPrint;
        return print;
    }
}
