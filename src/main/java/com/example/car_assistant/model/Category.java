package com.example.car_assistant.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {

    @JsonProperty("autovehicle")
    AUTOVEHICLE("Autovehicle"),

    @JsonProperty("motorcycle")
    MOTORCYCLE("Motorcycle"),

    @JsonProperty("truck")
    TRUCK("Truck");

    private String category;

    Category(String category) {
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
