package by.savik.Jobbler.database.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Area2{
    @JsonProperty("id")
    String id;

    @JsonProperty("parent_id")
    String parent_id;

    @JsonProperty("name")
    String name;

    @JsonProperty("areas")
    ArrayList<Area> areas;

    @JsonProperty("utc_offset")
    String utc_offset;

    @JsonProperty("lat")
    public double getLat() {
        return this.lat; }
    public void setLat(double lat) {
        this.lat = lat; }
    double lat;
    @JsonProperty("lng")
    public double getLng() {
        return this.lng; }
    public void setLng(double lng) {
        this.lng = lng; }
    double lng;
}
