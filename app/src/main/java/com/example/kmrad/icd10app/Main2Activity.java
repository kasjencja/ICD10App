package com.example.kmrad.icd10app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;



public class Main2Activity extends AppCompatActivity{


    private String BASE_URL =  "https://clinicaltables.nlm.nih.gov/api/icd10cm/v3/search?sf=code,name&maxList&terms=";


    @BindView(R.id.recycler)
    RecyclerView recycler;

    private CodesResponse codesResponse = new CodesResponse();
    private ArrayList<Codes> responseCodes;
    private CodesAdapter codesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String query = bundle.getString("query");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        //query=translateEnglish(query);
        getData(query);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    private void getData(String query) {
        Ion.with(getApplicationContext()).load(BASE_URL+query).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                responseCodes = (codesResponse.createCodesList(result));
                codesAdapter = new CodesAdapter(responseCodes);
                recycler.setAdapter(codesAdapter);

            }
        });

    }

    private String translateEnglish(String text){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage("pl"),
                        Translate.TranslateOption.targetLanguage("en"));
        String result = translation.getTranslatedText();
        return result;
    }


}

