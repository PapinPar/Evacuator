
package com.evacuator.uses.evacuator.maps.entity.driver.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("start_time")
    @Expose
    private Integer startTime;
    @SerializedName("end_time")
    @Expose
    private Integer endTime;

    /**
     * 
     * @return
     *     The distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The start_time
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The end_time
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

}
