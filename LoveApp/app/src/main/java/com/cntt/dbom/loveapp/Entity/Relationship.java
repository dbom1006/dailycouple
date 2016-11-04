package com.cntt.dbom.loveapp.Entity;

import com.cntt.dbom.loveapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bac Nice on 11/4/2016.
 */
public class Relationship {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    private int id;
    private String name;
    private int idImage;
    public Relationship(int id,String name,int idImage){
        setName(name);
        setId(id);
        setIdImage(idImage);
    }
    public static List<Relationship> getList(){
        List<Relationship> lst=new ArrayList<>();
        lst.add(new Relationship(1,"Forever Alone", R.drawable.avatar_ahri));
        lst.add(new Relationship(2,"Couple Love", R.drawable.avatar_ahri));
        lst.add(new Relationship(3,"Couple Married", R.drawable.avatar_ahri));
        return lst;
    }
    public String toString(){
        return name;
    }
}
