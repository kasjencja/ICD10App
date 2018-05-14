package com.example.kmrad.icd10app;


import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

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
        ArrayList<Codes> codesArrayList = new ArrayList<Codes>();
        String [] responseParts = response.split(Pattern.quote("["));
        for (int i =4; i<=Integer.parseInt(numberOfItems)+3; i++){
            String [] tempCodes = responseParts[i].split(Pattern.quote(","));
            String code = tempCodes[0];
            code = code.replace("\"","");
            String name = tempCodes[1];
            name = name.replace("\"","");
            name = name.replace("]","");
            name = translatePolish(name);
            codesArrayList.add(new Codes(code, name));
        }
        responseCodes = codesArrayList;
        return responseCodes;
    }

    private String translatePolish(String text){
        TranslateOptions options = TranslateOptions.newBuilder()
                .setApiKey("AIzaSyCcRcn_KHOp8VqO-P1HSmkNSjvS5tZegFA")
						.build();
        Translate trService = options.getService();
        Translation translation = trService.translate(text, Translate.TranslateOption.sourceLanguage("en") ,Translate.TranslateOption.targetLanguage("pl"));
        String result = translation.getTranslatedText();
        return result;
    }
}
