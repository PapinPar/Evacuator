package com.evacuator.uses.evacuator.maps.entity.apies;

import com.evacuator.uses.evacuator.maps.entity.direction.GeocodedWaypoints;

import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by root on 08.01.16.
 */
public interface MapApi
{

    //http://maps.googleapis.com/maps/api/

    @POST("directions/json?units=metric&&mode=driving&alternatives=false&sensor=false&language=ru")
    Call<GeocodedWaypoints> get(@Query("origin")String origin,@Query("destination")String destination);
//
    /*  @POST("directions/json?units=metric&origin={or}1&mode=driving&alternatives=false&sensor=false&language=ru&destination={dest}")
    Call<GeocodedWaypoints> get(@Path("or")String or,@Path("dest")String dest)
    ;
     */
}
