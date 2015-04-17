package com.neoandroid.app;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Date;

class Criminal {
    String firstName;
    String middleName;
    String lastName;
    String alias;
    Date birthday;
    BigDecimal reward;
//    Gender gender;
//    CriminalStatus status;
    BigDecimal height;
    BigDecimal weight;


    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    static public Criminal create(String serializedData) {
        Gson gson = new Gson();
        return gson.fromJson(serializedData, Criminal.class);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
