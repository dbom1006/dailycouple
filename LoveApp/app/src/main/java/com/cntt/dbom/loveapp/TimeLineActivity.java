package com.cntt.dbom.loveapp;

import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cntt.dbom.loveapp.Entity.Activity;
import com.cntt.dbom.loveapp.adapter.SlidingMenuAdapter;
import com.cntt.dbom.loveapp.fragment.Fragment3;
import com.cntt.dbom.loveapp.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends ActionBarActivity {
    private ArrayList<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter slidingMenuAdapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mobileArray);
        List<Activity> lst=Activity.getList();
        ActivityAdapter adapter = new ActivityAdapter(this,R.layout.list_activities,lst);
        ListView listView = (ListView) findViewById(R.id.ListActivities);
        listView.setAdapter(adapter);


        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.ic_favorite_white_48dp, "HOME"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_setting,"SETTINGS"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_event_white_48dp, "HISTORY"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_about, "ABOUT"));
        slidingMenuAdapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(slidingMenuAdapter);

        //Display icon to open/ close sliding list
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Display fragment 1 when start
        replaceFragment(0);
        //Hanlde on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set title
                setTitle(listSliding.get(position).getTitle());
                //item selected
                listViewSliding.setItemChecked(position, true);
                //Replace fragment
                replaceFragment(position);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.viewEvent) {

            Intent intent = new Intent(this,EventActivity.class);
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //Create method replace fragment

    private void replaceFragment(int pos) {
        Fragment fragment = null;
        Intent intent=null;
        switch (pos) {
            case 0:
                break;
            case 1:
                intent = new Intent(TimeLineActivity.this,ProfileActivity.class);
                break;
            case 2:
                intent = new Intent(TimeLineActivity.this,HistoryActivity.class);
                break;
            case 3:

                break;
            default:

                break;
        }
        if(intent!=null)
            startActivity(intent);
//        if(null!=fragment) {
//            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.main_content, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
    }


    class ActivityAdapter extends ArrayAdapter<Activity>{
        public List<Activity> data;
        public ActivityAdapter(Context context, int resource, List<Activity> objects) {
            super(context, resource, objects);
            this.data=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.list_activities,null);
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
