package com.cntt.dbom.loveapp;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.dbom.loveapp.DAL.ActivityDAO;
import com.cntt.dbom.loveapp.DAL.ProfileDAO;
import com.cntt.dbom.loveapp.Entity.Activity;
import com.cntt.dbom.loveapp.adapter.SlidingMenuAdapter;
import com.cntt.dbom.loveapp.design.ReactionView;
import com.cntt.dbom.loveapp.model.ItemSlideMenu;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeLineActivity extends ActionBarActivity {
    private ArrayList<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter slidingMenuAdapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView iconEmotion;
    private EditText txtStatus;
    private Button btnInsertStatus;
    private ReactionView reactionView;
    private ListView listView;
    private List<Activity> lst;
    private ActivityAdapter adapter;
    private TextView timeLineDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        initView();
        lst= ActivityDAO.getListToday(TimeLineActivity.this);
        adapter = new ActivityAdapter(this,R.layout.list_activities,lst);
        listView.setAdapter(adapter);
        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.ic_favorite_white_48dp, "HOME"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_settings_applications_white_48dp,"SETTINGS"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_event_white_48dp, "HISTORY"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_assignment_ind_white_48dp, "ABOUT"));
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

                setTitle("HOME");
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
                intent = new Intent(TimeLineActivity.this,AboutActivity.class);
                break;
            default:

                break;
        }
        if(intent!=null) {
            startActivity(intent);
            this.finish();
        }
//        if(null!=fragment) {
//            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.drawer_layout, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//       }

    }

    public void initView() {

        timeLineDays=(TextView) findViewById(R.id.timeLineDays);
        timeLineDays.setText("0");
        if(ProfileDAO.getInformation(TimeLineActivity.this)!=null){
            Date dateNow=new Date();
            Date date;
            String stDate=ProfileDAO.getInformation(TimeLineActivity.this).getDateBegin();
            try{
                date=new SimpleDateFormat("dd/MM/yyyy").parse(stDate);
            }
            catch (Exception e){
                date=dateNow;
            }
            long days=Math.round((dateNow.getTime()-date.getTime())/(1000.0*60*60*24));
            timeLineDays.setText(days+"");
        }
        listView = (ListView) findViewById(R.id.ListActivities);
        iconEmotion = (ImageView) findViewById(R.id.iconTimeLineEmotion);
        iconEmotion.setTag(R.drawable.em_love);
        reactionView = (ReactionView) findViewById(R.id.view_reaction);
        reactionView.setVisibility(View.INVISIBLE);
        iconEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reactionView.show(v);
            }
        });
        btnInsertStatus= (Button) findViewById(R.id.btnInsertStatus);
        txtStatus = (EditText) findViewById(R.id.txtEditStatus);
        btnInsertStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtStatus.getText().equals("")){
                    Date date=new Date();
                    String stDate=new SimpleDateFormat("dd/MM/yyyy").format(date);
                    String stTime=new SimpleDateFormat("hh:mm").format(date);
                    ActivityDAO.Insert(new Activity(txtStatus.getText().toString(),stDate,stTime,(Integer)iconEmotion.getTag()),TimeLineActivity.this);
                    Toast.makeText(TimeLineActivity.this,"Đăng thành công",Toast.LENGTH_LONG);
                    resetLoadScreen();
                }
                else{
                   Toast.makeText(TimeLineActivity.this,"Không thể đăng Status. Cảm nghĩ không được bỏ trống!",Toast.LENGTH_LONG);
                }
            }
        });
    }

    public void resetLoadScreen(){
        iconEmotion.setImageResource(R.drawable.em_love);
        iconEmotion.setTag(R.drawable.em_love);
        txtStatus.setText(null);
        lst=ActivityDAO.getListToday(TimeLineActivity.this);
        adapter.clear();
        adapter.addAll(lst);
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
