package com.urvesh.bello.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.urvesh.bello.Item;
import com.urvesh.bello.LectureDetails;
import com.urvesh.bello.R;

import java.util.ArrayList;

/**
 * Created by Urvesh on 11-Dec-15.
 */
public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.TimeHolder> {
    ArrayList<Item> timeList;

    public LectureAdapter(ArrayList<Item> timeList) {
        this.timeList = timeList;
    }


    @Override
    public TimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lecture_details, parent, false);
        return new TimeHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeHolder holder, int position) {
        LectureDetails lectureDetails = (LectureDetails) timeList.get(position);
        if(lectureDetails.getFacultyName()!=null){
            if(lectureDetails.getSubject().equals("break")){
                holder.txtSubject.setGravity(Gravity.CENTER);
                holder.txtSubject.setText("BREAK");
                holder.txtTime.setGravity(Gravity.CENTER);
                holder.txtTime.setText(lectureDetails.getTime());
                holder.detailLayout.setBackgroundResource(R.color.breakColor);

                holder.linearLayout.setVisibility(View.GONE);
                holder.txtFaculty.setVisibility(View.GONE);
                holder.txtRoom.setVisibility(View.GONE);
            }
            else{
                holder.txtFaculty.setText("Faculty: "+lectureDetails.getFacultyName());
                holder.linearLayout.setBackgroundResource(lectureDetails.getLecColor());
                holder.txtTime.setText("Timing: " + lectureDetails.getTime());
                holder.txtSubject.setText(lectureDetails.getSubject());
            }
        }
        if(lectureDetails.getSubject()==null){
            holder.linearLayout.setBackgroundResource(android.R.color.white);
            holder.detailLayout.setGravity(Gravity.CENTER);
            holder.txtFaculty.setText("No lectures :)");
            holder.linearLayout.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public static class TimeHolder extends RecyclerView.ViewHolder {
        TextView txtTime, txtSubject, txtFaculty, txtRoom;
        LinearLayout linearLayout,detailLayout;

        public TimeHolder(View itemView) {
            super(itemView);
            txtRoom = (TextView) itemView.findViewById(R.id.txtRoom);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSubject);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            txtFaculty = (TextView) itemView.findViewById(R.id.txtFaculty);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.cardLayout);
            detailLayout = (LinearLayout) itemView.findViewById(R.id.detailsLayout);

        }
    }
}
