package com.cntt.dbom.loveapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.dbom.loveapp.DAL.EventDAO;
import com.cntt.dbom.loveapp.Entity.Event;
import com.cntt.dbom.loveapp.adapter.EventAdapter;

import java.util.Calendar;
import java.util.List;

public class EventActivity extends AppCompatActivity{
    public EventAdapter adapter;
    public List<Event> lst;
    public ListView listView;
    int year_x, month_x, day_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        lst=EventDAO.getList(EventActivity.this);
        adapter = new EventAdapter(this, R.layout.list_events,lst);
        listView = (ListView) findViewById(R.id.listEvent);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog=AskOption(lst.get(position),EventActivity.this);
                dialog.show();
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    private void Finish(){
        Intent intent=new Intent(EventActivity.this,TimeLineActivity.class);
        startActivity(intent);
    }

    @Override
    public void finish() {
        Finish();
        super.finish();
    }
    public void openDialog(Context context) {
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog_create_event, null);
        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createEvent(layout,EventActivity.this);
                lst=EventDAO.getList(EventActivity.this);
                adapter.clear();
                adapter.addAll(lst);
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addEvent) {
            openDialog(this);
            return true;
        }
        if (id == R.id.img_close) {
            EventActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createEvent(View view,Context context){
        TextView txtName=(TextView) view.findViewById(R.id.txtCreateEventName);
        TextView txtDate=(TextView) view.findViewById(R.id.txtCreateEventDate);
        CheckBox chkIcon=(CheckBox) view.findViewById(R.id.chkCreateEventImportant);
        CheckBox chkType=(CheckBox) view.findViewById(R.id.chkCreateEventEveryYear);
        String name=txtName.getText().toString();
        String date=txtDate.getText().toString();
        int icon=chkIcon.isChecked()?R.drawable.calendar_important:R.drawable.calendar_love;
        int type=chkType.isChecked()?1:2;
        Event e=new Event(name,date,type,icon);
        String error = EventDAO.Insert(e,context);
        if(error==null)Toast.makeText(EventActivity.this,  "Success!" , Toast.LENGTH_LONG).show();
        else Toast.makeText(EventActivity.this,  "Faded!" , Toast.LENGTH_LONG).show();
    }
//    @Override
//    protected Dialog onCreateDialog(int id){
//        if(id==DILOG_ID_DATE){
//            return  new DatePickerDialog(this, dMenPickerListner, year_x, month_x, day_x);
//        }
//
//        return  null;
//    }
//    private DatePickerDialog.OnDateSetListener dMenPickerListner
//            = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            year_x = year;
//            month_x= month +1;
//            day_x = dayOfMonth;
//            txtBdateMen.setText(day_x +"/" + month_x + "/" + year_x);
//            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
//        }
//    };
    private AlertDialog AskOption(final Event e,final Context context)
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Xóa sự kiện")
                .setMessage("Bạn có muốn xóa sự kiện này?\n"+e.getName()+" - "+e.getFullDate())
                .setIcon(R.drawable.calendar_delete)
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String error = EventDAO.Delete(e,context);
                        if(error==null)Toast.makeText(EventActivity.this,  "Deleted!" , Toast.LENGTH_LONG).show();
                        else Toast.makeText(EventActivity.this,  "Faded!" , Toast.LENGTH_LONG).show();
                        lst=EventDAO.getList(EventActivity.this);
                        adapter.clear();
                        adapter.addAll(lst);

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}
