package com.epam.API_HT.model;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class RxObject {
    private String rxName;

    public RxObject(String rxName) {
        this.rxName = rxName;
    }

}
