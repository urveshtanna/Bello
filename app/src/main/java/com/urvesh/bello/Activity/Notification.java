package com.urvesh.bello.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.urvesh.bello.R;
import com.urvesh.bello.helper.ParseUtils;
import com.urvesh.bello.helper.PrefManager;
import com.urvesh.bello.model.Message;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private ListView listView;
    private List<Message> listMessages = new ArrayList<>();
    private MessageAdapter adapter;
    private PrefManager pref;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new MessageAdapter(this);
        pref = new PrefManager(getApplicationContext());

        listView.setAdapter(adapter);

        String email = pref.getEmail();
        if (email != null) {
            ParseUtils.subscribeWithEmail(pref.getEmail(),"ComputerScience");
        } else {
            Log.e(TAG, "Email is null. Not subscribing to parse!");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("message");

        Message m = new Message(message, System.currentTimeMillis());
        listMessages.add(0, m);
        adapter.notifyDataSetChanged();
    }

    private class MessageAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public MessageAdapter(Activity activity) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return listMessages.size();
        }

        @Override
        public Object getItem(int position) {
            return listMessages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.list_row, null);
            }

            TextView txtMessage = (TextView) view.findViewById(R.id.message);
            TextView txtTimestamp = (TextView) view.findViewById(R.id.timestamp);

            Message message = listMessages.get(position);
            txtMessage.setText(message.getMessage());

            CharSequence ago = DateUtils.getRelativeTimeSpanString(message.getTimestamp(), System.currentTimeMillis(),
                    0L, DateUtils.FORMAT_ABBREV_ALL);

            txtTimestamp.setText(String.valueOf(ago));

            return view;
        }
    }


}
