package com.urvesh.bello.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.urvesh.bello.Adapter.LectureAdapter;
import com.urvesh.bello.Item;
import com.urvesh.bello.LectureDetails;
import com.urvesh.bello.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekDay extends Fragment {
    int[] color = new int[]{R.color.faculty_0,R.color.faculty_1,R.color.faculty_3,R.color.faculty_4,R.color.faculty_5,R.color.faculty_6};
    int page;
    RecyclerView recyclerView;
    ArrayList<Item> lecDetails = new ArrayList<>();
    ArrayList<Item> lecDetails2 = new ArrayList<>();

    String[] monday = new String[]{"DBMS","AC","break","AC Practicals (Batch I)","Linux Practicals (Batch I)"};//4
    String[] mondayTiming = new String[]{"9:30AM - 11:10AM","11:20AM - 12:56PM","12:56AM - 1:20PM","2:00PM - 4:00PM","4:00PM - 6:00PM"};
    int[] mondayId = new int[]{0,1,5,1,1};//4


    String[] tuesday = new String[]{"DCN","Java Practicals (Batch I)"};//2
    String[] tuesdayTiming = new String[]{"9:34AM - 11:10AM","11:30AM - 1:30PM"};
    int[] tuesdayId = new int[]{2,3};//2

    String[] wednesday = new String[]{"OS","Java Practicals (Batch II)","break","AC Practicals (Batch II)"};//3
    String[] wednesdayTiming = new String[]{"9:34AM - 11:10AM","11:30AM - 1:30PM","1:30PM - 2:00PM","2:00PM - 4:00PM"};
    int[] wednesdayId = new int[]{4,3,5,1};//3

    String[] thursday = new String[]{"DCN","Project Session","break","Linux Practicals (Batch II)"};//3
    String[] thursdayTiming = new String[]{"9:34AM - 11:10AM","11:30AM - 1:30PM","1:30PM - 2:00PM","2:30PM - 4:30PM"};
    int[] thursdayId = new int[]{2,5,5,1};//3



    String[] friday = new String[]{"AC","Java","DBMS","break","Project Session"};//4
    String[] fridayTiming = new String[]{"8:00AM - 9:30AM","9:34AM - 11:10AM","11:20AM - 12:56AM","12:56PM - 1:20PM","1:30PM - 3:30PM"};
    int[] fridayId = new int[]{1,3,0,5,5};//4

    String[] saturday = new String[]{"OS","Java"};//2
    String[] saturdayTiming = new String[]{"9:34AM - 11:10AM","11:20AM - 12:56PM"};
    int[] saturdayId = new int[]{5,3};//2

    String[] faculties = new String[]{"Swati Ma'am","Nizam Sir","Jitesh Sir","Poonam Ma'am","Marielia Ma'am","NA"};//5
    public WeekDay() {
        // Required empty public constructor
    }

    public WeekDay(int page) {
        this.page = page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week_day, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.lectureRecycler);
        Void[] params = null;
        new BackgroundTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
        addListener();
        return view;
    }

    public void addListener() {
        recyclerView.setAdapter(new LectureAdapter(lecDetails));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            lecDetails2 = new ArrayList<>();
            lecDetails2 = getLecture();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            addListener();
            int size = lecDetails.size();
            lecDetails.addAll(lecDetails2);
            Log.e("onPostExecution", "");
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public ArrayList<Item> getLecture() {
        ArrayList<Item> lecDetails = new ArrayList<>();
        switch (page) {
            case 0:
                for (int i = 0; i < monday.length; i++) {
                    lecDetails.add(new LectureDetails(faculties[mondayId[i]], monday[i], mondayTiming[i], 500 + i,color[mondayId[i]]));
                }
                break;
            case 1:
                for (int i = 0; i < tuesday.length; i++) {
                    lecDetails.add(new LectureDetails(faculties[tuesdayId[i]], tuesday[i], tuesdayTiming[i], 500 + i,color[tuesdayId[i]]));
                }
                break;
            case 2:
                for (int i = 0; i < wednesday.length; i++) {
                    lecDetails.add(new LectureDetails(faculties[wednesdayId[i]], wednesday[i], wednesdayTiming[i], 500 + i,color[wednesdayId[i]]));
                }
                break;
            case 3:
                for (int i = 0; i < thursday.length; i++) {
                    lecDetails.add(new LectureDetails(faculties[thursdayId[i]], thursday[i], thursdayTiming[i], 500 + i,color[thursdayId[i]]));
                }
                break;
            case 4:
                for (int i = 0; i < friday.length; i++) {
                    lecDetails.add(new LectureDetails(faculties[fridayId[i]], friday[i], fridayTiming[i], 500 + i,color[fridayId[i]]));
                }
                break;
            case 5:
                for (int i = 0; i < saturday.length; i++) {
                    lecDetails.add(new LectureDetails(faculties[saturdayId[i]], saturday[i], saturdayTiming[i], 500 + i,color[saturdayId[i]]));
                }
                break;
            case 6:
                lecDetails.add(new LectureDetails(null, null, null, 0, android.R.color.black));
                break;
        }
        return lecDetails;
    }
}
