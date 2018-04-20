package com.example.kmrad.icd10app;

/**
 * Created by kmrad on 17.04.2018.
 */

public class Codes {

    private String name;
    private String numer;

    public Codes(String name, String numer){
        this.name = name;
        this.numer = numer;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.numer;
    }

    public void setCode(String numer) {
        this.numer = numer;
    }
}
