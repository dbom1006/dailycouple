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

import com.cntt.dbom.loveapp.Entity.Event;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        List<Event> lst=Event.getList();
        EventAdapter adapter = new EventAdapter(this, R.layout.list_events,lst);
        ListView listView = (ListView) findViewById(R.id.listEvent);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }
    class EventAdapter extends ArrayAdapter<Event> {
        public List<Event> data;

        public EventAdapter(Context context, int resource, List<Event> objects) {
            super(context, resource, objects);
            this.data=objects;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.list_events,null);
            if(position==0 || !data.get(position).getMonthYear().equals(data.get(position-1).getMonthYear())){
                TextView txtListEventHeader=(TextView) view.findViewById(R.id.txtListEventHeader);
                String[] s=data.get(position).getMonthYear().split("/");
                String t="Tháng "+s[0]+" Năm "+s[1];
                txtListEventHeader.setText(t);
                txtListEventHeader.setVisibility(View.VISIBLE);
            }
            TextView txtName=(TextView) view.findViewById(R.id.txtListEventName);
            TextView txtDate=(TextView) view.findViewById(R.id.txtListEventDate);
            TextView txtDaysLeft=(TextView) view.findViewById(R.id.txtListEventDaysLeft);
            ImageView img=(ImageView) view.findViewById(R.id.iconListEvent);
            txtDaysLeft.setText("Còn "+data.get(position).getDaysLeft()+" ngày");
            txtName.setText(data.get(position).getName());
            txtDate.setText(data.get(position).getDate());
            img.setImageResource(data.get(position).getIcon());
            return view;
        }
    }
}
