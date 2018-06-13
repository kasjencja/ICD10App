package com.example.kmrad.icd10app;

import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by kmrad on 18.04.2018.
 */

public class CodesResponse {

    private String numberOfItems;

    private ArrayList<Codes> responseCodes;

    public String getNumberOfItems(String response) {
        String [] responseParts = response.split(Pattern.quote("["));
        numberOfItems = responseParts[1];
        return numberOfItems;
    }

    public ArrayList<Codes> createCodesList(String response){
        numberOfItems = getNumberOfItems(response);
        numberOfItems = numberOfItems.replace(",", "");
        if (Integer.parseInt(numberOfItems)>=300) {
            ArrayList<Codes> codesArrayList = new ArrayList<Codes>();
            codesArrayList.add(new Codes("errorMax", "errorMax"));
            responseCodes = codesArrayList;
        } else if(Integer.parseInt(numberOfItems)==0){
            ArrayList<Codes> codesArrayList = new ArrayList<Codes>();
            codesArrayList.add(new Codes("errorMin", "errorMin"));
            responseCodes = codesArrayList;
        }else {
            ArrayList<Codes> codesArrayList = new ArrayList<Codes>();
            String[] responseParts = response.split(Pattern.quote("["));
            for (int i = 4; i <= Integer.parseInt(numberOfItems) + 3; i++) {
                String[] tempCodes = responseParts[i].split(Pattern.quote(","));
                String code = tempCodes[0];
                code = code.replace("\"", "");
                String name = tempCodes[1];
                name = name.replace("\"", "");
                name = name.replace("]", "");
                codesArrayList.add(new Codes(code, name));
                responseCodes = codesArrayList;
            }

        }
        return responseCodes;
    }


}
