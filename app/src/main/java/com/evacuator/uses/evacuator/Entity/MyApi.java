package com.evacuator.uses.evacuator.Entity;

import com.evacuator.uses.evacuator.Entity.Brand.NewBrands;
import com.evacuator.uses.evacuator.Entity.Model.NewModels;
import com.evacuator.uses.evacuator.Entity.Tarifs.Tarif;
import com.evacuator.uses.evacuator.Users;
import com.evacuator.uses.evacuator.maps.entity.driver.location.Example;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface MyApi
{
    @POST("order/create?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS")
    Call<Users>get(@Body Users users);

    @GET("reference/brands?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS&all=true")
    Call<List<NewBrands>> getBrand(@Query("carType")String car);

    @GET("reference/models?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS&all=true")
    Call<List<NewModels>>getModel(@Query("brandId")String brandID,@Query("carType")String car);

    //https://app.bb-evacuator.ru/api
    @GET("tariffs?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS")
    Call<List<Tarif>>getTarif();


    @POST("order/by-key?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS&expand=model,brand,driverer,statistics")
    Call<Example> getDriverInfo(@Query("key")String key);

}