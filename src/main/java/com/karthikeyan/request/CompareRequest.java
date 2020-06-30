package com.karthikeyan.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author Karthikeyan on 30/06/20
 */

public class CompareRequest {

    @SerializedName("a")
    private String a;
    @SerializedName("b")
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
