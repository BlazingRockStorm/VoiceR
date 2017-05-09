package com.example.gryphon.voice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    public Button openMic;
    public TextView showVoiceText;
    public final int REO_CODE_SPEECH_OUTPUT = 143 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openMic = (Button) findViewById(R.id.button);
        showVoiceText = (TextView) findViewById(R.id.showVoiceOutput);

        openMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnToOpenMic();
            }
        });

    }

    public void btnToOpenMic() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "sHOUT!");

        try {
            startActivityForResult(intent, REO_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException tim) {

        }
    }

        protected void onActivityResult(int requestCode,int resultCode, Intent data){
            super.onActivityResult(requestCode,resultCode,data);

            switch (requestCode){
                case REO_CODE_SPEECH_OUTPUT:
                    if(resultCode==RESULT_OK&&null!=data){
                        ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        showVoiceText.setText(voiceInText.get(0));
                    }
                    break;
            }
        }
    }
