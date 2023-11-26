package org.smart_laboratory.desktop.purdue_spot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class spotAdapter extends RecyclerView.Adapter<spotAdapter.ViewHolder> {
    private List<Map<String, String>> logList;
    private DBHelper dbHelper;

    //Getter for list
    public spotAdapter(List<Map<String, String>> songList) {this.logList = songList;}

    //Create viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot, parent, false);
        return new ViewHolder(view);
    }

    //Set values for position in recycle viewer
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map<String, String> logData = logList.get(position);

        FiltersClass fc = new FiltersClass();
        holder.sPrint.setText(fc.getPrint());
        holder.sSound.setText(fc.getSound());
    }

    //Find list size
    @Override
    public int getItemCount() {
        return logList.size();
    }

    //Instantiation
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sName, sPrint, sSound;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sName = itemView.findViewById(R.id.sName);
            sPrint = itemView.findViewById(R.id.sPrint);
            sSound = itemView.findViewById(R.id.sSound);
        }
    }
}
