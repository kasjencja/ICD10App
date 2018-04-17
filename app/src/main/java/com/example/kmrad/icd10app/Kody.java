package com.example.kmrad.icd10app;

/**
 * Created by kmrad on 17.04.2018.
 */

public class Kody {

    private String nazwa;
    private String numer;

    public Kody(String nazwa, String numer){
        this.nazwa = nazwa;
        this.numer = numer;
    }
    public String getName() {
        return this.nazwa;
    }

    public void setName(String name) {
        this.nazwa = name;
    }

    public String getCode() {
        return this.numer;
    }

    public void setCode(String numer) {
        this.numer = numer;
    }
}
