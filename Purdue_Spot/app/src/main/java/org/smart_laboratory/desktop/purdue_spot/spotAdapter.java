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

/*
spotAdapter
This is used to set the recyclerView format, calling the item_spot XML file
and doing the action listener of the view more button, to call the SpotActivity
for the clicked Spot ID
*/
public class spotAdapter extends RecyclerView.Adapter<spotAdapter.ViewHolder> {
    private List<myDBModel> spotList;
    private Context context;

    public spotAdapter(Context context, List<myDBModel> dataList) {
        this.context=context;
        this.spotList = dataList;
    }

    // Create ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Set XML file
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot, parent, false);
        return new ViewHolder(view);
    }

    // Set values in XML
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        myDBModel spotRow = spotList.get(position);

        // Setting Text
        holder.sName.setText(spotRow.getName());
        holder.sLocation.setText(spotRow.getLocation());

        // Action Listener for View More Button, moved to SpotActivity
        holder.sViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Put ID into intent to be called by SpotActivity
                String cSpotId = spotRow.getId();
                Intent spotIntent = new Intent(context, SpotActivity.class);
                spotIntent.putExtra("SPOT_ID", cSpotId);
                context.startActivity(spotIntent);
            }
        });
    }
    // Get List length
    @Override
    public int getItemCount() {
        return spotList.size();
    }

    //Instantiation
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sName, sLocation;
        Button sViewMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Instantiate Adapter Elements
            sName = itemView.findViewById(R.id.sName);
            sLocation = itemView.findViewById(R.id.sLocation);
            sViewMore = itemView.findViewById(R.id.infoBtn);
        }
    }
}
