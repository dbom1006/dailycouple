package com.cntt.dbom.loveapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cntt.dbom.loveapp.Entity.Activity;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        List<Activity> lst=Activity.getList2();
        ActivityAdapter adapter = new ActivityAdapter(this, R.layout.list_activities,lst);
        ListView listView = (ListView) findViewById(R.id.listHistory);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    class ActivityAdapter extends ArrayAdapter<Activity> {
        public List<Activity> data;

        public ActivityAdapter(Context context, int resource, List<Activity> objects) {
            super(context, resource, objects);
            this.data=objects;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.list_activities,null);
            if(position==0 || !data.get(position).getDate().equals(data.get(position-1).getDate())){
                TextView txtHeader=(TextView) view.findViewById(R.id.txtHeader);
                String[] s=data.get(position).getDate().split("/");
                String t="Ngày "+s[0]+" Tháng "+s[1]+" Năm "+s[2];
                txtHeader.setText(t);
                txtHeader.setVisibility(View.VISIBLE);
            }
            TextView txtStatus=(TextView) view.findViewById(R.id.txtStatus);
            TextView txtTime=(TextView) view.findViewById(R.id.txtTime);
            ImageView img=(ImageView) view.findViewById(R.id.ImageEmoticon);
            txtStatus.setText(data.get(position).getStatus());
            txtTime.setText(data.get(position).getTime());
            img.setImageResource(data.get(position).getIcon());
            return view;
        }
    }
}
