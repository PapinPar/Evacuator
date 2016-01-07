package com.evacuator.uses.evacuator.Entity.Brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 07.01.16.
 */
public class Brands {

    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * No args constructor for use in serialization
     *
     */
    public Brands() {
    }

    /**
     *
     * @param response
     */
    public Brands(Response response) {
        this.response = response;
    }

    /**
     *
     * @return
     * The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

}