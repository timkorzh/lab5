package com.company.collection_manage;

public class Person {
    public Person() {
        this.name = "";
        this.passportID = "";
        this.location = new Location();
    }
    public Person(String name,String passportID,Location location) {
        this.name = name;
        this.passportID = passportID;
        this.location = location;
    }

    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Поле может быть null
    private Location location; //Поле может быть null

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
