package org.smart_laboratory.desktop.purdue_spot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class spotAdapter extends RecyclerView.Adapter<spotAdapter.ViewHolder> {
    private List<myDBModel> spotList;
    //private DBHelper dbHelper;

    //Getter for list
    public spotAdapter(List<myDBModel> dataList) {this.spotList = dataList;}

    //Create viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot, parent, false);
        return new ViewHolder(view);
    }

    //Set values for position in recycle viewer
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        myDBModel spotRow = spotList.get(position);

        //FiltersClass fc = new FiltersClass();
        holder.sName.setText(spotRow.getName());//
        holder.sSound.setText(spotRow.getSound());
        holder.sPrint.setText(spotRow.getPrint());
    }

    //Find list size
    @Override
    public int getItemCount() {
        return spotList.size();
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
