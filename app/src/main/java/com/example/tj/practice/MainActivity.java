package com.example.tj.practice;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    ProgressBar[] progressBar;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressBar[5];
        progressBar[0] = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar[1] = (ProgressBar)findViewById(R.id.progressBar);
        progressBar[2] = (ProgressBar)findViewById(R.id.progressBar3);
        progressBar[3] = (ProgressBar)findViewById(R.id.progressBar4);
        progressBar[4] = (ProgressBar)findViewById(R.id.progressBar5);

        for(int i = 0; i<5; i++){
            progressBar[i].setTag(((i+1)+"번마"));
        }

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Runnable[] runnables = start();

                for(int i = 0;i<5; i++){
                    Thread myThread = new Thread(runnables[i]);
                    myThread.start();
                }
            }
        });

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            String ok = bundle.getString("우승말");

            Log.d(TAG,"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            if(ok.equals("1번마")){
                Toast.makeText(getApplicationContext(), ok+" 우승", Toast.LENGTH_SHORT).show();

            }
            else if(ok.equals("2번마")){
                Toast.makeText(getApplicationContext(), ok+" 우승", Toast.LENGTH_SHORT).show();

            }
            else if(ok.equals("3번마")){
                Toast.makeText(getApplicationContext(), ok+" 우승", Toast.LENGTH_SHORT).show();

            }
            else if(ok.equals("4번마")){
                Toast.makeText(getApplicationContext(), ok+" 우승", Toast.LENGTH_SHORT).show();

            }
            else if(ok.equals("5번마")){
                Toast.makeText(getApplicationContext(), ok+" 우승", Toast.LENGTH_SHORT).show();

            }

            return false;
        }
    });

    public Runnable[] start(){
        Runnable[] runnable = new Runnable[5];

        for(int i = 0; i <5; i++){
            final int finalI = i;
            runnable[i] = new Runnable() {

                @Override
                public void run() {
                    Random random = new Random();
                    int p = finalI;
                    int k = 0;

                    while (progressBar[p].getMax() != progressBar[p].getProgress()){
                        k += random.nextInt(10)+1;
                        progressBar[p].setProgress(k);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(progressBar[p].getMax() <= progressBar[p].getProgress()){
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("우승말", progressBar[p].getTag().toString());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }
            };
        }
        return runnable;
    }


}
