package com.evacuator.uses.evacuator;

import retrofit.Call;
import retrofit.http.POST;

public interface MyApi {


    @POST("order/create?car_type=1&address=123&phone=4213")
    Call<Users> getOrder();

}