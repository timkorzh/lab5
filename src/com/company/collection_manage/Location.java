package com.company.collection_manage;

public class Location {
    public Location() {
        this.x = Double.valueOf(0);
        this.y = Long.valueOf(0);
        this.z = 0;

    }
    public Location(String location) {
        this.x = Double.parseDouble(location.split(";")[0].trim().replace(',', '.'));
        this.y = Long.parseLong(location.split(";")[1].trim());
        this.z = Float.parseFloat(location.split(";")[2].trim().replace(',', '.'));

    }
    private Double x; //Поле не может быть null
    public Double getX() {

        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }

    private Long y; //Поле не может быть null
    public Long getY() {

        return y;
    }
    public void setY(Long y) {
        this.y = y;
    }

    private float z;
    public float getZ() {

        return z;
    }
    public void setZ(float z) {
        this.z = z;
    }
}
