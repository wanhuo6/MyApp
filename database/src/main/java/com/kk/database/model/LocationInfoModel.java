package com.kk.database.model;

/**
 * Created by Ma Zhihua on 2016/8/23.
 * Description :
 */
public class LocationInfoModel {

    private String lat,lng ;

    private String pause ;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }
}
