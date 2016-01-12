package com.evacuator.uses.evacuator.Entity.Tarifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.01.16.
 */
public class Tarif {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("car_types")
    @Expose
    private List<Integer> carTypes = new ArrayList<Integer>();
    @SerializedName("evacuator_types")
    @Expose
    private List<Integer> evacuatorTypes = new ArrayList<Integer>();
    @SerializedName("weight_from")
    @Expose
    private Integer weightFrom;
    @SerializedName("weight_to")
    @Expose
    private Integer weightTo;
    @SerializedName("price_loading")
    @Expose
    private Integer priceLoading;
    @SerializedName("price_km")
    @Expose
    private Integer priceKm;
    @SerializedName("price_minute")
    @Expose
    private Integer priceMinute;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The carTypes
     */
    public List<Integer> getCarTypes() {
        return carTypes;
    }

    /**
     *
     * @param carTypes
     * The car_types
     */
    public void setCarTypes(List<Integer> carTypes) {
        this.carTypes = carTypes;
    }

    /**
     *
     * @return
     * The evacuatorTypes
     */
    public List<Integer> getEvacuatorTypes() {
        return evacuatorTypes;
    }

    /**
     *
     * @param evacuatorTypes
     * The evacuator_types
     */
    public void setEvacuatorTypes(List<Integer> evacuatorTypes) {
        this.evacuatorTypes = evacuatorTypes;
    }

    /**
     *
     * @return
     * The weightFrom
     */
    public Integer getWeightFrom() {
        return weightFrom;
    }

    /**
     *
     * @param weightFrom
     * The weight_from
     */
    public void setWeightFrom(Integer weightFrom) {
        this.weightFrom = weightFrom;
    }

    /**
     *
     * @return
     * The weightTo
     */
    public Integer getWeightTo() {
        return weightTo;
    }

    /**
     *
     * @param weightTo
     * The weight_to
     */
    public void setWeightTo(Integer weightTo) {
        this.weightTo = weightTo;
    }

    /**
     *
     * @return
     * The priceLoading
     */
    public Integer getPriceLoading() {
        return priceLoading;
    }

    /**
     *
     * @param priceLoading
     * The price_loading
     */
    public void setPriceLoading(Integer priceLoading) {
        this.priceLoading = priceLoading;
    }

    /**
     *
     * @return
     * The priceKm
     */
    public Integer getPriceKm() {
        return priceKm;
    }

    /**
     *
     * @param priceKm
     * The price_km
     */
    public void setPriceKm(Integer priceKm) {
        this.priceKm = priceKm;
    }

    /**
     *
     * @return
     * The priceMinute
     */
    public Integer getPriceMinute() {
        return priceMinute;
    }

    /**
     *
     * @param priceMinute
     * The price_minute
     */
    public void setPriceMinute(Integer priceMinute) {
        this.priceMinute = priceMinute;
    }

}