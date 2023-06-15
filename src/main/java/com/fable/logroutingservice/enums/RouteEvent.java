package com.fable.logroutingservice.enums;

public enum RouteEvent {
    LOGOUT("logout"),
    LOGIN("login");

    private String status;

    RouteEvent(String status) {
        this.status = status;
    }
}
