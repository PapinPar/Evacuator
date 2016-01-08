package com.evacuator.uses.evacuator.maps.entity.apies;

import com.evacuator.uses.evacuator.maps.entity.address.Results;
import com.evacuator.uses.evacuator.maps.entity.direction.GeocodedWaypoints;

import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by root on 08.01.16.
 */
public interface AddresApi {
    //http://maps.googleapis.com/maps/api/

    @POST("geocode/json?language=ru&sensor=true&key=AIzaSyAHydF6G-yLHtjRBq6HvDouy7RXjYF1vR8")
    Call<Results> get(@Query("latlng")String latlng);
//
    /*  @POST("directions/json?units=metric&origin={or}1&mode=driving&alternatives=false&sensor=false&language=ru&destination={dest}")
    Call<GeocodedWaypoints> get(@Path("or")String or,@Path("dest")String dest)
    ;
     */
}
