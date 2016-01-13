package com.evacuator.uses.evacuator.Entity.Verification.entity;

/**
 * Created by root on 13.01.16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("resultMessage")
    @Expose
    private String resultMessage;

    /**
     *
     * @return
     *     The result
     */
    public Integer getResult() {
        return result;
    }

    /**
     *
     * @param result
     *     The result
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     *
     * @return
     *     The resultMessage
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     *
     * @param resultMessage
     *     The resultMessage
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

}