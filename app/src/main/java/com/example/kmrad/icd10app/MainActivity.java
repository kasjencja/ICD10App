package com.example.kmrad.icd10app;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL =  "https://clinicaltables.nlm.nih.gov/api/icd10cm/v3/search?sf=code,name&terms=";
    ArrayList<String> codes = new ArrayList<>();

    @OnClick(R.id.button_szukaj)
    void OnClick(){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    @BindView(R.id.edit_text_diagnosis)
    EditText editDiagnosis;

    @BindView(R.id.microphone)
    ImageView microphone;

    @OnClick(R.id.microphone)
    void getSpeechInput(){
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(speechIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(speechIntent, 10);
        } else {
            Toast.makeText(this, "Twój telefon nie umożliwia wyszukiwania głosowego!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        switch (requestCode){
            case 10:
                if (resultCode == RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editDiagnosis.setText(result.get(0));
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
