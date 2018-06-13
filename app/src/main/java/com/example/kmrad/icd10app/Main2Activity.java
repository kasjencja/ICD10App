package com.example.kmrad.icd10app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

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
        getData(query);
    }

    private void getData(String query) {
            Ion.with(getApplicationContext()).load(BASE_URL + query).asString().setCallback(new FutureCallback<String>() {
                @Override
                public void onCompleted(Exception e, String result) {
                        responseCodes = (codesResponse.createCodesList(result));
                        if (responseCodes.get(0).getCode() == "errorMax") {
                            Toast.makeText(Main2Activity.this, "Too many records. Precise the diagnosis.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(intent);
                        } else if(responseCodes.get(0).getCode() == "errorMin"){
                            Toast.makeText(Main2Activity.this, R.string.no_results, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            codesAdapter = new CodesAdapter(responseCodes);
                            recycler.setAdapter(codesAdapter);
                        }
                    }

            });

    }
}

