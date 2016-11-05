package com.cntt.dbom.loveapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.dbom.loveapp.Entity.Relationship;

import java.util.Calendar;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Button btnSetDate;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0, DILOG_ID_MEN = 1, DILOG_ID_WOMEN = 2;

    TextView txtBdateMen, txtBdateWomen, txtDStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Spinner spRela=(Spinner) findViewById(R.id.relationship);
        List<Relationship> lst=Relationship.getList();
        ArrayAdapter<Relationship> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spRela.setAdapter(adapter);

        //Khoi tao ngay thang mac dinh
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
    }
    public void showDialogOnButtonClick(){
        btnSetDate = (Button) findViewById(R.id.dateBtn);
        txtBdateMen = (TextView) findViewById(R.id.Bday_Men);
        txtBdateWomen = (TextView) findViewById(R.id.Bday_Women);
        txtDStart = (TextView) findViewById(R.id.date_start);
        txtBdateMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID_MEN);
            }
        });
        txtBdateWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID_WOMEN);
            }
        });
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        });

    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==DILOG_ID_MEN){
            return  new DatePickerDialog(this, dMenPickerListner, year_x, month_x, day_x);
        }
        if(id==DILOG_ID_WOMEN){
            return  new DatePickerDialog(this, dWomenPickerListner, year_x, month_x, day_x);
        }
        if(id==DILOG_ID){
            return  new DatePickerDialog(this, dBeginPickerListner, year_x, month_x, day_x);
        }
        return  null;
    }
    private DatePickerDialog.OnDateSetListener dMenPickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x= month +1;
            day_x = dayOfMonth;
            txtBdateMen.setText(year_x +"/" + + month_x + "/" + day_x);
            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dWomenPickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x= month;
            day_x = dayOfMonth;
            txtBdateWomen.setText(year_x +"/" + + month_x + "/" + day_x);
            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dBeginPickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x= month;
            day_x = dayOfMonth;
            txtDStart.setText(year_x +"/" + + month_x + "/" + day_x);
            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };
}
