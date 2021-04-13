package fr.iscgroup.isc.view.graphics;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.iscgroup.isc.R;
import fr.iscgroup.isc.model.trials.Trial;
import fr.iscgroup.isc.view.activity.IsoActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //RecyclerViewAdapter class that binds the members
    //of class Trial to their corresponding position
    //on the RecyclerView

    private List<Trial> data;
    private LayoutInflater inflater;


    //Data is passed into the constructor
    public RecyclerViewAdapter(Context context, List<Trial> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    //Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    //Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trial ti = data.get(position);
        holder.Trial.setText(IsoActivity.resources.getString(R.string.trial, ti.getTrial()));
        holder.Details.setText(IsoActivity.resources.getString(R.string.difficulty_time, ti.getDifficulty(), ti.getTime()));
    }

    //Returns total number of rows
    @Override
    public int getItemCount() {
        return data.size();
    }

    //Maps each TextView to its corresponding component on the row layout.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Trial;
        TextView Details;
        ViewHolder(View itemView) {
            super(itemView);
            Trial = itemView.findViewById(R.id.TrialNumber);
            Details = itemView.findViewById(R.id.DifficultyAndTime);
        }

    }

}
