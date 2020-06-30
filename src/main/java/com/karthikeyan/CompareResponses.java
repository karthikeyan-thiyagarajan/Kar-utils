package com.karthikeyan;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthikeyan on 30/06/20
 */

public class CompareResponses {

    private String equal;
    private List<String> insert;
    private List<String> delete;

    public CompareResponses() {
        this.delete = new ArrayList<>();
        this.insert = new ArrayList<>();
    }

    public String getEqual() {
        if (this.delete.isEmpty() && this.insert.isEmpty()) {
            return "No Differences";
        }else {
            return this.equal;
        }
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }

    public List<String> getInsert() {
        return insert;
    }

    public void setInsert(List<String> insert) {
        this.insert = insert;
    }

    public List<String> getDelete() {
        return delete;
    }

    public void setDelete(List<String> delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void addInsert(String items) {
        insert.add(items);
    }

    public void addDelete(String items) {
        delete.add(items);
    }

}
