package com.droppledev.sqlitetest;

/**
 * Created by ayasfn on 10/13/2017.
 * ini class model yaitu setter dan getter
 */

public class Biodata {

    private int id;
    private String name, location;

    Biodata(){

    }
    Biodata(int id){
        this.id = id;
    }

    Biodata(String name, String location){
        this.name = name;
        this.location = location;
    }

    Biodata(int id, String name, String location){
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getLocation() {
        return location;
    }

    void setLocation(String location) {
        this.location = location;
    }
}
