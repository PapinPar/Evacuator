package com.evacuator.uses.evacuator;

import com.evacuator.uses.evacuator.Users;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MyApi
{
    @POST("order/create?brand_id={brand_id}&model_id={model_id}&receive_date={receive_date}&weight={weight}&blocked_wheels={blocked_wheels}&blocked_wheels_cnt={blocked_wheels_cnt}" +
            "&blocked_steering_wheel={blocked_steering_wheel}&low_landing={low_landing}&phone={phone}&car_type={car_type}&gps_longitude={gps_longitude}&gps_latitude={gps_latitude}&" +
            "address={address}&manipulator_required={manipulator_required}&destination_address={destination_address}&comment={comment}&" +
            "from_website={from_website}&commission={commission}&payment_type={payment_type}&agent_commission={agent_commission}&created_at={created_at}&updated_at={updated_at}&price={price}" +
            "&ukey={ukey}&status={status}&tariff_found={tariff_found}&tariff_name={tariff_name}&tariff_price_loading={tariff_price_loading}&tariff_price_km={tariff_price_km}" +
            "&tariff_price_minute={tariff_price_minute}&tariff_price_blocked_wheel={tariff_price_blocked_wheel}&tariff_price_blocked_steering_wheel={tariff_price_blocked_steering_wheel}" +
            "&tariff_included_mileage={tariff_included_mileage}&tariff_discount_website={tariff_discount_website}&" +
            "id={id}&distance={distance}&current_user_id={current_user_id}&status_name={status_name}&is_cancelable={is_cancelable}&is_payed={is_payed}&from_agent={from_agent}&additional_services=null}")

    Call<Users> getOrder(@Query("brand_id") String brand_id, @Query("model_id") String model_id, @Query("receive_date") String receive_date, @Query("weight") String weight,
                         @Query("blocked_wheels") String blocked_wheels, @Query("blocked_wheels_cnt") String blocked_wheels_cnt, @Query("blocked_steering_wheel") String blocked_steering_wheel, @Query("low_landing") String low_landing,
                         @Query("phone") String phone, @Query("car_type") String car_type, @Query("gps_longitude") String gps_longitude, @Query("gps_latitude") String gps_latitude, @Query("address") String address,
                         @Query("manipulator_required") String manipulator_required, @Query("sdestination_address") String destination_address, @Query("comment") String comment, @Query("from_website") String from_website,
                         @Query("commission") String commission, @Query("payment_type") String payment_type, @Query("agent_commission") String agent_commission, @Query("created_at") String created_at, @Query("updated_at") String updated_at,
                         @Query("price") String price, @Query("ukey") String ukey, @Query("status") String status, @Query("tariff_found") String tariff_found, @Query("tariff_name") String tariff_name, @Query("tariff_price_loading") String tariff_price_loading,
                         @Query("tariff_price_km") String tariff_price_km, @Query("tariff_price_minute") String tariff_price_minute, @Query("tariff_price_blocked_wheel") String tariff_price_blocked_wheel,
                         @Query("tariff_price_blocked_steering_wheel") String tariff_price_blocked_steering_wheel, @Query("tariff_included_mileage") String tariff_included_mileage, @Query("tariff_discount_website") String tariff_discount_website,
                         @Query("id") String id, @Query("distance") String distance, @Query("current_user_id") String current_user_id, @Query("status_name") String status_name, @Query("is_cancelable") String is_cancelable,
                         @Query("is_payed") String is_payed,
                         @Query("from_agent") String from_agent, @Query("additional_services") String additional_services);



    ///
    //
//      аксес токен возможно надо убрать
//
    //
    @POST("order/create?access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS")
    Call<Users>get(@Body Users users);


}