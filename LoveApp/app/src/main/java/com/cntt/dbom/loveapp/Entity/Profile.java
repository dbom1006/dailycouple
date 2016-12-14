package com.cntt.dbom.loveapp.Entity;

import java.util.Date;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class Profile {
    private String nameX;
    private  String nameY;
    private Date birthdayX;
    private Date birthdayY;
    private Date dateBegin;
    private String relationship;

    public Profile(String nameX, Date dateBegin, String relationship) {
        this.nameX = nameX;
        this.dateBegin = dateBegin;
        this.relationship = relationship;
    }
    public Profile(){

    }
    public Profile(String nameX, String nameY, Date birthdayX, Date birthdayY, Date dateBegin, String relationship) {
        this.nameX = nameX;
        this.nameY = nameY;
        this.birthdayX = birthdayX;
        this.birthdayY = birthdayY;
        this.dateBegin = dateBegin;
        this.relationship = relationship;
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

    public Date getBirthdayX() {
        return birthdayX;
    }

    public void setBirthdayX(Date birthdayX) {
        this.birthdayX = birthdayX;
    }

    public Date getBirthdatY() {
        return birthdayY;
    }

    public void setBirthdatY(Date birthdatY) {
        this.birthdayY = birthdatY;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
