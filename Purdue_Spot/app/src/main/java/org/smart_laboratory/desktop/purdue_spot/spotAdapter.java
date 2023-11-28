package org.smart_laboratory.desktop.purdue_spot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class spotAdapter extends RecyclerView.Adapter<spotAdapter.ViewHolder> {
    private List<myDBModel> spotList;
    private Context context;
    //private DBHelper dbHelper;

    //Getter for list
    public spotAdapter(Context context, List<myDBModel> dataList) {
        this.context=context;
        this.spotList = dataList;
    }

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
        String clickedId = spotRow.getId();
        //FiltersClass fc = new FiltersClass();
        //holder.sId.setText(spotRow.getId());
        holder.sName.setText(spotRow.getName());//
        holder.sSound.setText(spotRow.getSound());
        holder.sPrint.setText(spotRow.getPrint());

        holder.sViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                //int clickedItemId = getItemId(position);  // or however you get the item ID
                //openPageWithId(clickedItemId);
                Intent aboutIntent = new Intent(context, SpotActivity.class);
                context.startActivity(aboutIntent);
            }
        });

    }

    //Find list size
    @Override
    public int getItemCount() {
        return spotList.size();
    }

    //Instantiation
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sId, sName, sPrint, sSound;
        Button sViewMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sName = itemView.findViewById(R.id.sName);
            sPrint = itemView.findViewById(R.id.sPrint);
            sSound = itemView.findViewById(R.id.sSound);
            sViewMore = itemView.findViewById(R.id.infoBtn);
        }
    }
}
