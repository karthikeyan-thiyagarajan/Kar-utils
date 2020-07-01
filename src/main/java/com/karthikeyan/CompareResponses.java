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
    private List<Integer> insertIndex;
    private List<Integer> deleteIndex;
    private List<Integer> equalIndex;
    private String result = "";

    public CompareResponses() {
        this.delete = new ArrayList<>();
        this.insert = new ArrayList<>();
        this.insertIndex = new ArrayList<>();
        this.deleteIndex = new ArrayList<>();
        this.equalIndex = new ArrayList<>();
    }

    public String getEqual() {
        if (this.delete.isEmpty() && this.insert.isEmpty()) {
            return "No Differences";
        } else {
            return this.equal;
        }
    }

    public List<Integer> getEqualIndex() {
        return equalIndex;
    }

    public void setEqualIndex(List<Integer> equalIndex) {
        this.equalIndex = equalIndex;
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }

    public List<Integer> getInsertIndex() {
        return insertIndex;
    }

    public void setInsertIndex(List<Integer> insertIndex) {
        this.insertIndex = insertIndex;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result += result;
    }

    public List<Integer> getDeleteIndex() {
        return deleteIndex;
    }

    public void setDeleteIndex(List<Integer> deleteIndex) {
        this.deleteIndex = deleteIndex;
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

    public void addInsertIndex(Integer items) {
        insertIndex.add(items);
    }

    public void addDeleteIndex(Integer items) {
        deleteIndex.add(items);
    }

    public void addEqualIndex(Integer items) {
        equalIndex.add(items);
    }

}
