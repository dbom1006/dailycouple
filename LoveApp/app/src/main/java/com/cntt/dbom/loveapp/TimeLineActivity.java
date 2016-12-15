package com.cntt.dbom.loveapp;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.dbom.loveapp.DAL.ActivityDAO;
import com.cntt.dbom.loveapp.DAL.EventDAO;
import com.cntt.dbom.loveapp.DAL.ProfileDAO;
import com.cntt.dbom.loveapp.Entity.Activity;
import com.cntt.dbom.loveapp.Entity.Event;
import com.cntt.dbom.loveapp.Entity.Profile;
import com.cntt.dbom.loveapp.adapter.SlidingMenuAdapter;
import com.cntt.dbom.loveapp.design.CircleImageView;
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
    private ImageView iconEmotion,iconEvent;
    private EditText txtStatus;
    private Button btnInsertStatus;
    private ReactionView reactionView;
    private ListView listView;
    private List<Activity> lst;
    private Event event;
    private ActivityAdapter adapter;
    private TextView timeLineDays,eventName,eventDate;
    private ProgressBar progressBar;
    private CircleImageView avatarX,avatarY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        initView();
        lst= ActivityDAO.getListToday(TimeLineActivity.this);
        adapter = new ActivityAdapter(this,R.layout.list_activities,lst);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog=AskOption(lst.get(position),TimeLineActivity.this);
                dialog.show();
                return false;
            }
        });
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
            finish();
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
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        event= EventDAO.getList(TimeLineActivity.this).get(0);
        eventName=(TextView) findViewById(R.id.txtEventName);
        eventName.setText(event.getName());
        eventDate=(TextView) findViewById(R.id.txtEventDate);
        eventDate.setText(event.getFullDate());
        iconEvent=(ImageView) findViewById(R.id.iconEvent);
        iconEvent.setImageResource(event.getIcon());
        timeLineDays=(TextView) findViewById(R.id.timeLineDays);
        timeLineDays.setText("0");
        avatarX=(CircleImageView) findViewById(R.id.image_avatar_man);
        avatarY=(CircleImageView) findViewById(R.id.image_avatar_women);
        progressBar.setProgress(365-(int)event.getDaysLeft());
        Profile profile=ProfileDAO.getInformation(TimeLineActivity.this);
        if(profile!=null){
            avatarX.setImageURI(Uri.parse(profile.getImgX()));
            avatarY.setImageURI(Uri.parse(profile.getImgY()));
            Date dateNow=new Date();
            Date date;
            String stDate=profile.getDateBegin();
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
                    Toast.makeText(TimeLineActivity.this,"Đăng thành công",Toast.LENGTH_SHORT);
                    resetLoadScreen();
                }
                else{
                   Toast.makeText(TimeLineActivity.this,"Không thể đăng Status. Cảm nghĩ không được bỏ trống!",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    public void resetLoadScreen(){
        event= EventDAO.getList(TimeLineActivity.this).get(0);
        eventName.setText(event.getName());
        eventDate.setText(event.getFullDate());
        progressBar.setProgress(365-(int)event.getDaysLeft());
        iconEvent.setImageResource(event.getIcon());
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
    private AlertDialog AskOption(final Activity ac, final Context context)
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Xóa Status")
                .setMessage("Bạn có muốn xóa status này?\n"+ac.getStatus()+"\n"+ac.getTime())
                .setIcon(R.drawable.calendar_delete)
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityDAO.Delete(ac,context);
                        Toast.makeText(TimeLineActivity.this,  "Deleted!" , Toast.LENGTH_SHORT).show();
                        resetLoadScreen();
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}
