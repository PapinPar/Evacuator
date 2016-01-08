package com.evacuator.uses.evacuator.Entity.Brand;

import android.content.ClipData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.01.16.
 */
public class Response {

    @SerializedName("item")
    @Expose
    private List<Item> item = new ArrayList<>();
    public Response()
    {
    }

    /**
     *
     * @param item
     */
    public Response(List<Item> item) {
        this.item = item;
    }

    /**
     *
     * @return
     * The item
     */
    public List<Item> getItem() {
        return item;
    }

    /**
     *
     * @param item
     * The item
     */
    public void setItem(List<Item> item) {
        this.item = item;
    }

}