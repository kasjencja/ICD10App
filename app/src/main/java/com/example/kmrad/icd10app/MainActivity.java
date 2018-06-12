package com.example.kmrad.icd10app;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_szukaj)
    void OnClick(){
        String query = editDiagnosis.getText().toString();
        if (query.isEmpty() == true){
            Toast.makeText(this, "Podaj diagnozę!", Toast.LENGTH_SHORT).show();
        } else {Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("query", query);
            startActivity(intent);}

    }

    @BindView(R.id.info)
    TextView info;

    @BindView(R.id.edit_text_diagnosis)
    EditText editDiagnosis;

    @BindView(R.id.microphone)
    ImageView microphone;

    @OnClick(R.id.microphone)
    void getSpeechInput(){
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
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

}
