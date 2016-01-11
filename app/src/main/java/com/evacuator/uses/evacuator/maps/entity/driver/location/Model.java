
package com.evacuator.uses.evacuator.maps.entity.driver.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

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
    private Integer isTruck;
    @SerializedName("is_jeep")
    @Expose
    private Integer isJeep;
    @SerializedName("is_evacuator")
    @Expose
    private Integer isEvacuator;
    @SerializedName("weight")
    @Expose
    private Double weight;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The idBrand
     */
    public Integer getIdBrand() {
        return idBrand;
    }

    /**
     * 
     * @param idBrand
     *     The id_brand
     */
    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    /**
     * 
     * @return
     *     The isTruck
     */
    public Integer getIsTruck() {
        return isTruck;
    }

    /**
     * 
     * @param isTruck
     *     The is_truck
     */
    public void setIsTruck(Integer isTruck) {
        this.isTruck = isTruck;
    }

    /**
     * 
     * @return
     *     The isJeep
     */
    public Integer getIsJeep() {
        return isJeep;
    }

    /**
     * 
     * @param isJeep
     *     The is_jeep
     */
    public void setIsJeep(Integer isJeep) {
        this.isJeep = isJeep;
    }

    /**
     * 
     * @return
     *     The isEvacuator
     */
    public Integer getIsEvacuator() {
        return isEvacuator;
    }

    /**
     * 
     * @param isEvacuator
     *     The is_evacuator
     */
    public void setIsEvacuator(Integer isEvacuator) {
        this.isEvacuator = isEvacuator;
    }

    /**
     * 
     * @return
     *     The weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 
     * @param weight
     *     The weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
