package com.evacuator.uses.evacuator.Entity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 07.01.16.
 */
public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id_brand")
    @Expose
    private String idBrand;
    @SerializedName("is_truck")
    @Expose
    private String isTruck;
    @SerializedName("is_jeep")
    @Expose
    private String isJeep;
    @SerializedName("is_evacuator")
    @Expose
    private String isEvacuator;
    @SerializedName("weight")
    @Expose
    private String weight;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     *
     * @param isTruck
     * @param id
     * @param idBrand
     * @param weight
     * @param name
     * @param isJeep
     * @param isEvacuator
     */
    public Item(String id, String name, String idBrand, String isTruck, String isJeep, String isEvacuator, String weight) {
        this.id = id;
        this.name = name;
        this.idBrand = idBrand;
        this.isTruck = isTruck;
        this.isJeep = isJeep;
        this.isEvacuator = isEvacuator;
        this.weight = weight;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
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
     * The idBrand
     */
    public String getIdBrand() {
        return idBrand;
    }

    /**
     *
     * @param idBrand
     * The id_brand
     */
    public void setIdBrand(String idBrand) {
        this.idBrand = idBrand;
    }

    /**
     *
     * @return
     * The isTruck
     */
    public String getIsTruck() {
        return isTruck;
    }

    /**
     *
     * @param isTruck
     * The is_truck
     */
    public void setIsTruck(String isTruck) {
        this.isTruck = isTruck;
    }

    /**
     *
     * @return
     * The isJeep
     */
    public String getIsJeep() {
        return isJeep;
    }

    /**
     *
     * @param isJeep
     * The is_jeep
     */
    public void setIsJeep(String isJeep) {
        this.isJeep = isJeep;
    }

    /**
     *
     * @return
     * The isEvacuator
     */
    public String getIsEvacuator() {
        return isEvacuator;
    }

    /**
     *
     * @param isEvacuator
     * The is_evacuator
     */
    public void setIsEvacuator(String isEvacuator) {
        this.isEvacuator = isEvacuator;
    }

    /**
     *
     * @return
     * The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

}