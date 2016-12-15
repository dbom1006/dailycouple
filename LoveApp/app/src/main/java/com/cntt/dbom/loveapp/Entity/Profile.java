package com.cntt.dbom.loveapp.Entity;

import java.util.Date;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class Profile {
    private String nameX;
    private  String nameY;
    private String birthdayX;
    private String birthdayY;
    private String dateBegin;
    private String relationship;
    private String imgX;
    private String imgY;

    public String getImgX() {
        return imgX;
    }

    public void setImgX(String imgX) {
        this.imgX = imgX;
    }

    public String getImgY() {
        return imgY;
    }

    public void setImgY(String imgY) {
        this.imgY = imgY;
    }

    public String getNameX() {
        return nameX;
    }

    public void setNameX(String nameX) {
        this.nameX = nameX;
    }

    public String getNameY() {
        return nameY;
    }

    public void setNameY(String nameY) {
        this.nameY = nameY;
    }

    public String getBirthdayX() {
        return birthdayX;
    }

    public void setBirthdayX(String birthdayX) {
        this.birthdayX = birthdayX;
    }

    public String getBirthdayY() {
        return birthdayY;
    }

    public void setBirthdayY(String birthdayY) {
        this.birthdayY = birthdayY;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Profile(String nameX, String dateBegin, String relationship) {
        this.nameX = nameX;
        this.dateBegin = dateBegin;
        this.relationship = relationship;
    }
    public Profile(){

    }
    public Profile(String nameX, String nameY, String birthdayX, String birthdayY, String dateBegin, String relationship,String imgX, String imgY) {
        this.nameX = nameX;
        this.nameY = nameY;
        this.birthdayX = birthdayX;
        this.birthdayY = birthdayY;
        this.dateBegin = dateBegin;
        this.relationship = relationship;
        this.imgX = imgX;
        this.imgY = imgY;
    }

}
