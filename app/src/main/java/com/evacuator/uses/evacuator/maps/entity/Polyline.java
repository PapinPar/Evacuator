
package com.evacuator.uses.evacuator.maps.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polyline {

    @SerializedName("points")
    @Expose
    private String points;

    /**
     * 
     * @return
     *     The points
     */
    public String getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     *     The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

}
