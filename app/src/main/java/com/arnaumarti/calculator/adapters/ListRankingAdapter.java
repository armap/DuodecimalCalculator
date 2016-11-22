package com.arnaumarti.calculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arnaumarti.calculator.R;
import com.arnaumarti.calculator.model.UserPoints;

import java.util.ArrayList;

/**
 * Created by arnau on 24/10/16.
 */
public class ListRankingAdapter extends ArrayAdapter<UserPoints> {
    
    public ListRankingAdapter(Context context, ArrayList<UserPoints> users) {
        super(context, 0, users);
    }

    // View lookup cache
    private static class ViewHolder {
        TextView textViewNum;
        TextView textViewName;
        TextView textViewPoints;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UserPoints user = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_ranking_list, parent, false);
            viewHolder.textViewNum = (TextView) convertView.findViewById(R.id.textViewNum);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.textViewPoints = (TextView) convertView.findViewById(R.id.textViewPoints);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.textViewNum.setText(String.valueOf(position));
        if (user.getName()!=null) {
            viewHolder.textViewName.setText(user.getName());
        } else {
            viewHolder.textViewName.setText("");
        }
        if (user.getPoints()!=null) {
            viewHolder.textViewPoints.setText(user.getPoints());
        } else {
            viewHolder.textViewPoints.setText("");
        }
        // Return the completed view to render on screen
        return convertView;
    }

}