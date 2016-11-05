package com.cntt.dbom.loveapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.dbom.loveapp.Entity.Relationship;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    private String selectedImagePath;
    Bitmap bitmap;
    Button btnSetDate;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0, DILOG_ID_MEN = 1, DILOG_ID_WOMEN = 2;
    TextView txtBdateMen, txtBdateWomen, txtDStart;
    private boolean pickForMen=false;
    // image
    ImageView imgMan, imgWomen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgMan = (com.cntt.dbom.loveapp.CircleImageView) findViewById(R.id.image_avatar_man);
        imgWomen = (com.cntt.dbom.loveapp.CircleImageView) findViewById(R.id.image_avatar_women) ;

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
        imgMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), PICK_IMAGE_REQUEST);
                pickForMen=true;
                //imgMan.setImageBitmap(bitmap);
            }
        });
        imgWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), PICK_IMAGE_REQUEST);
                pickForMen=false;
                //imgMan.setImageBitmap(bitmap);
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            if (pickForMen) imgMan.setImageURI(uri);
            else imgWomen.setImageURI(uri);
        }
    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            if (requestCode == PICK_IMAGE_REQUEST) {
//                Uri selectedImageUri = data.getData();
//                selectedImagePath = getPath(selectedImageUri);
//
//            }
//        }
//    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
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
            txtBdateMen.setText(day_x +"/" + month_x + "/" + year_x);
            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dWomenPickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x= month+1;
            day_x = dayOfMonth;
            txtBdateWomen.setText(day_x +"/" + month_x + "/" + year_x );
            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dBeginPickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x= month+1;
            day_x = dayOfMonth;
            btnSetDate.setText(day_x +"/" +  month_x + "/" + year_x);
            //Toast.makeText(ProfileActivity.this,year_x +"/" + + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };


}
