package com.example.kmrad.icd10app;

/**
 * Created by kmrad on 17.04.2018.
 */

public class Codes {

    private String name;
    private String number;

    public Codes(String number, String name){
        this.number = number;
        this.name = name;

    }
    public String getName() {
        return this.name;
    }

   //public void setName(String name) {
   //     this.name = name;
   // }

    public String getCode() {
        return this.number;
    }

    //public void setCode(String number) {
    //    this.number = number;
    //}
}
