package com.capitalone.nsb.marketing.stockpile.api;

public enum ResponseType {
    SUCCESS("SUCCESS"),
    NOT_LOGGED_IN("NOT_LOGGED_IN"),
    ACCESS_DENIED("ACCESS_DENIED"),
    ERROR("ERROR");
    private String type;

    public String getType() {
        return type;
    }

    private ResponseType(String type) {
        this.type = type;
    }
}
