package com.nikvay.schooldemo.ui.module;

public class VideoCategoryModel
{
    String id,name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideoCategoryModel(String id, String name) {
        this.id = id;
        this.name = name;



    }
}
