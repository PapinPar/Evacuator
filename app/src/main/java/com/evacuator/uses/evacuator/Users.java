package com.evacuator.uses.evacuator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Papin on 05.01.2016.
 */
public class Users
{
@SerializedName("brand_id")
@Expose
private Integer brandId;
@SerializedName("model_id")
@Expose
private Integer modelId;
@SerializedName("receive_date")
@Expose
private String receiveDate;
@SerializedName("weight")
@Expose
private Integer weight;
@SerializedName("blocked_wheels")
@Expose
private Boolean blockedWheels;
@SerializedName("blocked_wheels_cnt")
@Expose
private Integer blockedWheelsCnt;
@SerializedName("blocked_steering_wheel")
@Expose
private Boolean blockedSteeringWheel;
@SerializedName("low_landing")
@Expose
private Boolean lowLanding;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("car_type")
@Expose
private Integer carType;
@SerializedName("gps_longitude")
@Expose
private Double gpsLongitude;
@SerializedName("gps_latitude")
@Expose
private Double gpsLatitude;
@SerializedName("address")
@Expose
private String address;
@SerializedName("manipulator_required")
@Expose
private Boolean manipulatorRequired;
@SerializedName("destination_address")
@Expose
private String destinationAddress;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("from_website")
@Expose
private Boolean fromWebsite;
@SerializedName("commission")
@Expose
private Integer commission;
@SerializedName("payment_type")
@Expose
private Integer paymentType;
@SerializedName("agent_commission")
@Expose
private Object agentCommission;
@SerializedName("created_at")
@Expose
private Integer createdAt;
@SerializedName("updated_at")
@Expose
private Integer updatedAt;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("ukey")
@Expose
private String ukey;
@SerializedName("status")
@Expose
private Integer status;
@SerializedName("tariff_found")
@Expose
private Boolean tariffFound;
@SerializedName("tariff_name")
@Expose
private String tariffName;
@SerializedName("tariff_price_loading")
@Expose
private Integer tariffPriceLoading;
@SerializedName("tariff_price_km")
@Expose
private Integer tariffPriceKm;
@SerializedName("tariff_price_minute")
@Expose
private Integer tariffPriceMinute;
@SerializedName("tariff_price_blocked_wheel")
@Expose
private Integer tariffPriceBlockedWheel;
@SerializedName("tariff_price_blocked_steering_wheel")
@Expose
private Integer tariffPriceBlockedSteeringWheel;
@SerializedName("tariff_included_mileage")
@Expose
private Integer tariffIncludedMileage;
@SerializedName("tariff_discount_website")
@Expose
private Integer tariffDiscountWebsite;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("distance")
@Expose
private Object distance;
@SerializedName("current_user_id")
@Expose
private Object currentUserId;
@SerializedName("status_name")
@Expose
private String statusName;
@SerializedName("is_cancelable")
@Expose
private Integer isCancelable;
@SerializedName("is_payed")
@Expose
private Integer isPayed;
@SerializedName("from_agent")
@Expose
private Integer fromAgent;
@SerializedName("additional_services")
@Expose
private List<Object> additionalServices = new ArrayList<Object>();

        public Users() {
        }


        public Users(Integer brandId, Integer modelId, String receiveDate, Integer weight, Boolean blockedWheels, Integer blockedWheelsCnt, Boolean blockedSteeringWheel, Boolean lowLanding, String phone, Integer carType, Double gpsLongitude, Double gpsLatitude, String address, Boolean manipulatorRequired, String destinationAddress, String comment, Boolean fromWebsite, Integer commission, Integer paymentType, Object agentCommission, Integer createdAt, Integer updatedAt, Integer price, String ukey, Integer status, Boolean tariffFound, String tariffName, Integer tariffPriceLoading, Integer tariffPriceKm, Integer tariffPriceMinute, Integer tariffPriceBlockedWheel, Integer tariffPriceBlockedSteeringWheel, Integer tariffIncludedMileage, Integer tariffDiscountWebsite, Integer id, Object distance, Object currentUserId, String statusName, Integer isCancelable, Integer isPayed, Integer fromAgent, List<Object> additionalServices) {
            this.brandId = brandId;
            this.modelId = modelId;
            this.receiveDate = receiveDate;
            this.weight = weight;
            this.blockedWheels = blockedWheels;
            this.blockedWheelsCnt = blockedWheelsCnt;
            this.blockedSteeringWheel = blockedSteeringWheel;
            this.lowLanding = lowLanding;
            this.phone = phone;
            this.carType = carType;
            this.gpsLongitude = gpsLongitude;
            this.gpsLatitude = gpsLatitude;
            this.address = address;
            this.manipulatorRequired = manipulatorRequired;
            this.destinationAddress = destinationAddress;
            this.comment = comment;
            this.fromWebsite = fromWebsite;
            this.commission = commission;
            this.paymentType = paymentType;
            this.agentCommission = agentCommission;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.price = price;
            this.ukey = ukey;
            this.status = status;
            this.tariffFound = tariffFound;
            this.tariffName = tariffName;
            this.tariffPriceLoading = tariffPriceLoading;
            this.tariffPriceKm = tariffPriceKm;
            this.tariffPriceMinute = tariffPriceMinute;
            this.tariffPriceBlockedWheel = tariffPriceBlockedWheel;
            this.tariffPriceBlockedSteeringWheel = tariffPriceBlockedSteeringWheel;
            this.tariffIncludedMileage = tariffIncludedMileage;
            this.tariffDiscountWebsite = tariffDiscountWebsite;
            this.id = id;
            this.distance = distance;
            this.currentUserId = currentUserId;
            this.statusName = statusName;
            this.isCancelable = isCancelable;
            this.isPayed = isPayed;
            this.fromAgent = fromAgent;
            this.additionalServices = additionalServices;
        }

        /**
         *
         * @return
         * The brandId
         */
        public Integer getBrandId() {
            return brandId;
        }

        /**
         *
         * @param brandId
         * The brand_id
         */
        public void setBrandId(Integer brandId) {
            this.brandId = brandId;
        }

        /**
         *
         * @return
         * The modelId
         */
        public Integer getModelId() {
            return modelId;
        }

        /**
         *
         * @param modelId
         * The model_id
         */
        public void setModelId(Integer modelId) {
            this.modelId = modelId;
        }

        /**
         *
         * @return
         * The receiveDate
         */
        public String getReceiveDate() {
            return receiveDate;
        }

        /**
         *
         * @param receiveDate
         * The receive_date
         */
        public void setReceiveDate(String receiveDate) {
            this.receiveDate = receiveDate;
        }

        /**
         *
         * @return
         * The weight
         */
        public Integer getWeight() {
            return weight;
        }

        /**
         *
         * @param weight
         * The weight
         */
        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        /**
         *
         * @return
         * The blockedWheels
         */
        public Boolean getBlockedWheels() {
            return blockedWheels;
        }

        /**
         *
         * @param blockedWheels
         * The blocked_wheels
         */
        public void setBlockedWheels(Boolean blockedWheels) {
            this.blockedWheels = blockedWheels;
        }

        /**
         *
         * @return
         * The blockedWheelsCnt
         */
        public Integer getBlockedWheelsCnt() {
            return blockedWheelsCnt;
        }

        /**
         *
         * @param blockedWheelsCnt
         * The blocked_wheels_cnt
         */
        public void setBlockedWheelsCnt(Integer blockedWheelsCnt) {
            this.blockedWheelsCnt = blockedWheelsCnt;
        }

        /**
         *
         * @return
         * The blockedSteeringWheel
         */
        public Boolean getBlockedSteeringWheel() {
            return blockedSteeringWheel;
        }

        /**
         *
         * @param blockedSteeringWheel
         * The blocked_steering_wheel
         */
        public void setBlockedSteeringWheel(Boolean blockedSteeringWheel) {
            this.blockedSteeringWheel = blockedSteeringWheel;
        }

        /**
         *
         * @return
         * The lowLanding
         */
        public Boolean getLowLanding() {
            return lowLanding;
        }

        /**
         *
         * @param lowLanding
         * The low_landing
         */
        public void setLowLanding(Boolean lowLanding) {
            this.lowLanding = lowLanding;
        }

        /**
         *
         * @return
         * The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         * The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         *
         * @return
         * The carType
         */
        public Integer getCarType() {
            return carType;
        }

        /**
         *
         * @param carType
         * The car_type
         */
        public void setCarType(Integer carType) {
            this.carType = carType;
        }

        /**
         *
         * @return
         * The gpsLongitude
         */
        public Double getGpsLongitude() {
            return gpsLongitude;
        }

        /**
         *
         * @param gpsLongitude
         * The gps_longitude
         */
        public void setGpsLongitude(Double gpsLongitude) {
            this.gpsLongitude = gpsLongitude;
        }

        /**
         *
         * @return
         * The gpsLatitude
         */
        public Double getGpsLatitude() {
            return gpsLatitude;
        }

        /**
         *
         * @param gpsLatitude
         * The gps_latitude
         */
        public void setGpsLatitude(Double gpsLatitude) {
            this.gpsLatitude = gpsLatitude;
        }

        /**
         *
         * @return
         * The address
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         *
         * @return
         * The manipulatorRequired
         */
        public Boolean getManipulatorRequired() {
            return manipulatorRequired;
        }

        /**
         *
         * @param manipulatorRequired
         * The manipulator_required
         */
        public void setManipulatorRequired(Boolean manipulatorRequired) {
            this.manipulatorRequired = manipulatorRequired;
        }

        /**
         *
         * @return
         * The destinationAddress
         */
        public String getDestinationAddress() {
            return destinationAddress;
        }

        /**
         *
         * @param destinationAddress
         * The destination_address
         */
        public void setDestinationAddress(String destinationAddress) {
            this.destinationAddress = destinationAddress;
        }

        /**
         *
         * @return
         * The comment
         */
        public String getComment() {
            return comment;
        }

        /**
         *
         * @param comment
         * The comment
         */
        public void setComment(String comment) {
            this.comment = comment;
        }

        /**
         *
         * @return
         * The fromWebsite
         */
        public Boolean getFromWebsite() {
            return fromWebsite;
        }

        /**
         *
         * @param fromWebsite
         * The from_website
         */
        public void setFromWebsite(Boolean fromWebsite) {
            this.fromWebsite = fromWebsite;
        }

        /**
         *
         * @return
         * The commission
         */
        public Integer getCommission() {
            return commission;
        }

        /**
         *
         * @param commission
         * The commission
         */
        public void setCommission(Integer commission) {
            this.commission = commission;
        }

        /**
         *
         * @return
         * The paymentType
         */
        public Integer getPaymentType() {
            return paymentType;
        }

        /**
         *
         * @param paymentType
         * The payment_type
         */
        public void setPaymentType(Integer paymentType) {
            this.paymentType = paymentType;
        }

        /**
         *
         * @return
         * The agentCommission
         */
        public Object getAgentCommission() {
            return agentCommission;
        }

        /**
         *
         * @param agentCommission
         * The agent_commission
         */
        public void setAgentCommission(Object agentCommission) {
            this.agentCommission = agentCommission;
        }

        /**
         *
         * @return
         * The createdAt
         */
        public Integer getCreatedAt() {
            return createdAt;
        }

        /**
         *
         * @param createdAt
         * The created_at
         */
        public void setCreatedAt(Integer createdAt) {
            this.createdAt = createdAt;
        }

        /**
         *
         * @return
         * The updatedAt
         */
        public Integer getUpdatedAt() {
            return updatedAt;
        }

        /**
         *
         * @param updatedAt
         * The updated_at
         */
        public void setUpdatedAt(Integer updatedAt) {
            this.updatedAt = updatedAt;
        }

        /**
         *
         * @return
         * The price
         */
        public Integer getPrice() {
            return price;
        }

        /**
         *
         * @param price
         * The price
         */
        public void setPrice(Integer price) {
            this.price = price;
        }

        /**
         *
         * @return
         * The ukey
         */
        public String getUkey() {
            return ukey;
        }

        /**
         *
         * @param ukey
         * The ukey
         */
        public void setUkey(String ukey) {
            this.ukey = ukey;
        }

        /**
         *
         * @return
         * The status
         */
        public Integer getStatus() {
            return status;
        }

        /**
         *
         * @param status
         * The status
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

        /**
         *
         * @return
         * The tariffFound
         */
        public Boolean getTariffFound() {
            return tariffFound;
        }

        /**
         *
         * @param tariffFound
         * The tariff_found
         */
        public void setTariffFound(Boolean tariffFound) {
            this.tariffFound = tariffFound;
        }

        /**
         *
         * @return
         * The tariffName
         */
        public String getTariffName() {
            return tariffName;
        }

        /**
         *
         * @param tariffName
         * The tariff_name
         */
        public void setTariffName(String tariffName) {
            this.tariffName = tariffName;
        }

        /**
         *
         * @return
         * The tariffPriceLoading
         */
        public Integer getTariffPriceLoading() {
            return tariffPriceLoading;
        }

        /**
         *
         * @param tariffPriceLoading
         * The tariff_price_loading
         */
        public void setTariffPriceLoading(Integer tariffPriceLoading) {
            this.tariffPriceLoading = tariffPriceLoading;
        }

        /**
         *
         * @return
         * The tariffPriceKm
         */
        public Integer getTariffPriceKm() {
            return tariffPriceKm;
        }

        /**
         *
         * @param tariffPriceKm
         * The tariff_price_km
         */
        public void setTariffPriceKm(Integer tariffPriceKm) {
            this.tariffPriceKm = tariffPriceKm;
        }

        /**
         *
         * @return
         * The tariffPriceMinute
         */
        public Integer getTariffPriceMinute() {
            return tariffPriceMinute;
        }

        /**
         *
         * @param tariffPriceMinute
         * The tariff_price_minute
         */
        public void setTariffPriceMinute(Integer tariffPriceMinute) {
            this.tariffPriceMinute = tariffPriceMinute;
        }

        /**
         *
         * @return
         * The tariffPriceBlockedWheel
         */
        public Integer getTariffPriceBlockedWheel() {
            return tariffPriceBlockedWheel;
        }

        /**
         *
         * @param tariffPriceBlockedWheel
         * The tariff_price_blocked_wheel
         */
        public void setTariffPriceBlockedWheel(Integer tariffPriceBlockedWheel) {
            this.tariffPriceBlockedWheel = tariffPriceBlockedWheel;
        }

        /**
         *
         * @return
         * The tariffPriceBlockedSteeringWheel
         */
        public Integer getTariffPriceBlockedSteeringWheel() {
            return tariffPriceBlockedSteeringWheel;
        }

        /**
         *
         * @param tariffPriceBlockedSteeringWheel
         * The tariff_price_blocked_steering_wheel
         */
        public void setTariffPriceBlockedSteeringWheel(Integer tariffPriceBlockedSteeringWheel) {
            this.tariffPriceBlockedSteeringWheel = tariffPriceBlockedSteeringWheel;
        }

        /**
         *
         * @return
         * The tariffIncludedMileage
         */
        public Integer getTariffIncludedMileage() {
            return tariffIncludedMileage;
        }

        /**
         *
         * @param tariffIncludedMileage
         * The tariff_included_mileage
         */
        public void setTariffIncludedMileage(Integer tariffIncludedMileage) {
            this.tariffIncludedMileage = tariffIncludedMileage;
        }

        /**
         *
         * @return
         * The tariffDiscountWebsite
         */
        public Integer getTariffDiscountWebsite() {
            return tariffDiscountWebsite;
        }

        /**
         *
         * @param tariffDiscountWebsite
         * The tariff_discount_website
         */
        public void setTariffDiscountWebsite(Integer tariffDiscountWebsite) {
            this.tariffDiscountWebsite = tariffDiscountWebsite;
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
         * The distance
         */
        public Object getDistance() {
            return distance;
        }

        /**
         *
         * @param distance
         * The distance
         */
        public void setDistance(Object distance) {
            this.distance = distance;
        }

        /**
         *
         * @return
         * The currentUserId
         */
        public Object getCurrentUserId() {
            return currentUserId;
        }

        /**
         *
         * @param currentUserId
         * The current_user_id
         */
        public void setCurrentUserId(Object currentUserId) {
            this.currentUserId = currentUserId;
        }

        /**
         *
         * @return
         * The statusName
         */
        public String getStatusName() {
            return statusName;
        }

        /**
         *
         * @param statusName
         * The status_name
         */
        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        /**
         *
         * @return
         * The isCancelable
         */
        public Integer getIsCancelable() {
            return isCancelable;
        }

        /**
         *
         * @param isCancelable
         * The is_cancelable
         */
        public void setIsCancelable(Integer isCancelable) {
            this.isCancelable = isCancelable;
        }

        /**
         *
         * @return
         * The isPayed
         */
        public Integer getIsPayed() {
            return isPayed;
        }

        /**
         *
         * @param isPayed
         * The is_payed
         */
        public void setIsPayed(Integer isPayed) {
            this.isPayed = isPayed;
        }

        /**
         *
         * @return
         * The fromAgent
         */
        public Integer getFromAgent() {
            return fromAgent;
        }

        /**
         *
         * @param fromAgent
         * The from_agent
         */
        public void setFromAgent(Integer fromAgent) {
            this.fromAgent = fromAgent;
        }

        /**
         *
         * @return
         * The additionalServices
         */
        public List<Object> getAdditionalServices() {
            return additionalServices;
        }

        /**
         *
         * @param additionalServices
         * The additional_services
         */
        public void setAdditionalServices(List<Object> additionalServices) {
            this.additionalServices = additionalServices;
        }

}
