package com.evacuator.uses.evacuator.Entity.Model;

/**
 * Created by user on 08.01.16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewModels {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id_brand")
    @Expose
    private Integer idBrand;
    @SerializedName("is_truck")
    @Expose
    private Object isTruck;
    @SerializedName("is_jeep")
    @Expose
    private Object isJeep;
    @SerializedName("is_evacuator")
    @Expose
    private Object isEvacuator;
    @SerializedName("weight")
    @Expose
    private Double weight;

    /**
     * No args constructor for use in serialization
     *
     */
    public NewModels() {
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
    public NewModels(Integer id, String name, Integer idBrand, Object isTruck, Object isJeep, Object isEvacuator, Double weight) {
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
     * The idBrand
     */
    public Integer getIdBrand() {
        return idBrand;
    }

    /**
     *
     * @param idBrand
     * The id_brand
     */
    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    /**
     *
     * @return
     * The isTruck
     */
    public Object getIsTruck() {
        return isTruck;
    }

    /**
     *
     * @param isTruck
     * The is_truck
     */
    public void setIsTruck(Object isTruck) {
        this.isTruck = isTruck;
    }

    /**
     *
     * @return
     * The isJeep
     */
    public Object getIsJeep() {
        return isJeep;
    }

    /**
     *
     * @param isJeep
     * The is_jeep
     */
    public void setIsJeep(Object isJeep) {
        this.isJeep = isJeep;
    }

    /**
     *
     * @return
     * The isEvacuator
     */
    public Object getIsEvacuator() {
        return isEvacuator;
    }

    /**
     *
     * @param isEvacuator
     * The is_evacuator
     */
    public void setIsEvacuator(Object isEvacuator) {
        this.isEvacuator = isEvacuator;
    }

    /**
     *
     * @return
     * The weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

}