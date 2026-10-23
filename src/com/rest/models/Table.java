package com.rest.models;

public class Table {

    public enum Availability {

        occupy, free
    }

    private int id;
    private int capasity;
    private Availability status;

    public Table(int id, int capasity) {
        this.id = id;
        this.capasity = capasity;
        this.status = Availability.free;
    }

    public int getId() {
        return id;
    }

    public int getCapasity() {
        return capasity;
    }

    public Availability getStatus() {
        return status;
    }

    public void booking() {
        status = Availability.occupy;
    }

    public void freeNow() {
        status = Availability.free;
    }
}