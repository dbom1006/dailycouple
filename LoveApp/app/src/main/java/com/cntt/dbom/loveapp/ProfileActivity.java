package com.cntt.dbom.loveapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cntt.dbom.loveapp.DAL.EventDAO;
import com.cntt.dbom.loveapp.DAL.ProfileDAO;
import com.cntt.dbom.loveapp.Entity.Event;
import com.cntt.dbom.loveapp.Entity.Profile;
import com.cntt.dbom.loveapp.Entity.Relationship;
import com.cntt.dbom.loveapp.design.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfileActivity  extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    private String selectedImagePath;
    Bitmap bitmap;
    Button btnSetDate, btnDone;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0, DILOG_ID_MEN = 1, DILOG_ID_WOMEN = 2;
    TextView txtBdateMen, txtBdateWomen, txtDStart;
    EditText edtX, edtY;
    private boolean pickForMen=false;
    // image
    ImageView imgMan, imgWomen;
    Spinner spRela;
    public String uriX="",uriY="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgMan = (CircleImageView) findViewById(R.id.image_avatar_man);
        imgWomen = (CircleImageView) findViewById(R.id.image_avatar_women) ;
        btnSetDate = (Button) findViewById(R.id.dateBtn);
        txtBdateMen = (TextView) findViewById(R.id.Bday_Men);
        txtBdateWomen = (TextView) findViewById(R.id.Bday_Women);
        txtDStart = (TextView) findViewById(R.id.date_start);
        spRela=(Spinner) findViewById(R.id.relationship);
        edtX = (EditText) findViewById(R.id.Name_Men);
        edtY = (EditText) findViewById(R.id.Name_Women);
        btnDone = (Button) findViewById(R.id.btnDone);
        List<Relationship> lst=Relationship.getList();
        ArrayAdapter<Relationship> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spRela.setAdapter(adapter);
        // Set data cho Settings (nếu đã có database)
        Profile pf= ProfileDAO.getInformation(ProfileActivity.this);

        if(pf!=null){

            edtX.setText(pf.getNameX());
            edtY.setText(pf.getNameY());

            if (!pf.getRelationship().equals(null)) {

            }
            txtBdateMen.setText(pf.getBirthdayX().toString());
            txtBdateWomen.setText(pf.getBirthdayY().toString());
            btnSetDate.setText(pf.getDateBegin().toString());
//            Log.d("Haha", pf.getImgX().trim()+","+uriY );
            //imgMan.setImageURI(Uri.fromFile(new File(pf.getImgX()) ));
            //imgWomen.setImageURI(Uri.parse(pf.getImgY()));
        }

        //Khoi tao ngay thang mac dinh
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ProfileActivity.this,  spRela.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if(!edtX.getText().toString().equals("") && !btnSetDate.getText().toString().equals("") && !edtY.getText().toString().equals("")){
                    String _nameX = edtX.getText().toString();
                    String _nameY = edtY.getText().toString();
                    //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String _birthdayX = txtBdateMen.getText().toString();
                    String _birthdayY = txtBdateWomen.getText().toString();
                    String _dateBegin = btnSetDate.getText().toString();
                    String _relationship = spRela.getSelectedItem().toString();
                    Event e1=new Event("Sinh nhật "+_nameX,_birthdayX,3,R.drawable.calendar_important);
                    Event e2=new Event("Sinh nhật "+_nameY,_birthdayY,3,R.drawable.calendar_important);
                    Event e3=new Event("Kỷ niệm ngày yêu",_dateBegin,3,R.drawable.calendar_love);
                    EventDAO.DeleteByName(e1.getName(),ProfileActivity.this);
                    EventDAO.DeleteByName(e2.getName(),ProfileActivity.this);
                    EventDAO.DeleteByName(e3.getName(),ProfileActivity.this);
                    EventDAO.Insert(e1,ProfileActivity.this);
                    EventDAO.Insert(e2,ProfileActivity.this);
                    EventDAO.Insert(e3,ProfileActivity.this);
                    try {
                        Profile spf = new Profile(_nameX,_nameY,_birthdayX,_birthdayY,_dateBegin,_relationship, uriX,uriY);

                        String error = ProfileDAO.update(ProfileActivity.this,spf);
                        if (error==null)Toast.makeText(ProfileActivity.this,  "Success!" , Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(ProfileActivity.this,  "Faded!" , Toast.LENGTH_LONG).show();
                            Log.d("Erorr: ",error);
                        }
                        ProfileActivity.this.finish();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this,  e +"" , Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(ProfileActivity.this,  "Vui lòng nhập đủ thông tin!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.img_close) {
            ProfileActivity.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
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
            if (pickForMen) {
                uriX = uri.toString();
                imgMan.setImageURI(uri);
                //Toast.makeText(ProfileActivity.this,uri +"" , Toast.LENGTH_LONG).show();
                Log.d("KQ",uri +"," + uriX );
            }
            else{
                imgWomen.setImageURI(uri);
                uriY = uri.toString();
            }
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
//    public String getPath(Uri uri) {
//        // just some safety built in
//        if( uri == null ) {
//            // TODO perform some logging or show user feedback
//            return null;
//        }
//        // try to retrieve the image from the media store first
//        // this will only work for images selected from gallery
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        if( cursor != null ){
//            int column_index = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        }
//        // this is our fallback here
//        return uri.getPath();
//    }


    public String getRealPathFromURI( Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
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
