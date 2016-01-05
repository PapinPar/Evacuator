package com.evacuator.uses.evacuator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Papin on 05.01.2016.
 */
public class Users
{
    @SerializedName("car_type")
    @Expose
    private String car;
    @SerializedName("address")
    @Expose
    private String adress;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Users(String adress, String car, String phone) {
        this.adress = adress;
        this.car = car;
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
