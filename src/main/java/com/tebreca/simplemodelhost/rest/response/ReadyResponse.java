package com.tebreca.simplemodelhost.rest.response;

public class ReadyResponse {

    int id;

    boolean on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public ReadyResponse(int id, boolean on) {
        this.id = id;
        this.on = on;
    }
}
