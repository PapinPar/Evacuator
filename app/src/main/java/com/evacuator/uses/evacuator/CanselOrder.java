package com.evacuator.uses.evacuator;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by user on 13.01.16.
 */
public class CanselOrder {

    private boolean isOk = false;

    CanselOrder(){

    }

    private class CancelResponse {

        @SerializedName("errorMessage")
        private String errorMessage;

        @SerializedName("error")
        private int error;

        CancelResponse()
        {

        }


        public int getError() {
            return error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setError(int error) {
            this.error = error;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    public interface Api{
        @POST("order/cancellation/access-token=WCgXZEmnOuiqWdiM0tQ-wS7KgldScNOS")
         public Call<CancelResponse> cancelOrder(@Query("orderId") int orderId);
    }


    public boolean cancelOrder(int id) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.bb-evacuator.ru/api/").addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);
        Log.d("SD11", "OK");
        Call<CancelResponse> call = api.cancelOrder(id);
        call.enqueue(new Callback<CancelResponse>() {

            @Override
            public void onResponse(Response<CancelResponse> response, Retrofit retrofit)
            {
                CancelResponse re = response.body();
                Log.d("SD11", response.message());
                if ((response.code()!=404 && response.code()!=401) ){
                    isOk = re.getError() == 0;
                    Log.d("SD11", String.valueOf(response.code()));
                    Log.d("SD11",re.getErrorMessage());
                    Log.d("SD11", String.valueOf(re.getError()));
                    re.getErrorMessage();
                    re.getError();
                }else {
                    isOk = false;
                }
                Log.d("SD11", String.valueOf(isOk));
            }
            @Override
            public void onFailure(Throwable t)
            {
                Log.d("SD11","eror" + t.toString());
            }
        });


        return isOk;

    }
}