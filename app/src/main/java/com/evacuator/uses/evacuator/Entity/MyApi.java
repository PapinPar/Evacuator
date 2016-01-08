package com.evacuator.uses.evacuator.Entity;

import com.evacuator.uses.evacuator.Entity.Brand.NewBrands;
import com.evacuator.uses.evacuator.Entity.Model.NewModels;
import com.evacuator.uses.evacuator.Users;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface MyApi
{
    @POST("order/create?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS")
    Call<Users>get(@Body Users users);


    @GET("reference/brands?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS&all=true&carType=1")
    Call<List<NewBrands>> getBrand();

    @GET("reference/models?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS&all=true&brandId=6&carType=1")
    Call<List<NewModels>>getModel();

}