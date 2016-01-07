package com.evacuator.uses.evacuator.Entity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 07.01.16.
 */
public class models {

    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * No args constructor for use in serialization
     *
     */
    public models() {
    }

    /**
     *
     * @param response
     */
    public models(Response response) {
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
