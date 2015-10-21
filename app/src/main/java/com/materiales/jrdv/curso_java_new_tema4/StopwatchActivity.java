package com.materiales.jrdv.curso_java_new_tema4;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class StopwatchActivity extends AppCompatActivity {

    //creo dos private vars(ivars)

    private int seconds=0;
    private boolean isRunnig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);



        //como hemos guradaod ates en el metodo "onSaveInstanceState" las ivars, si existe las recuperamos

        if (savedInstanceState != null){

            seconds=savedInstanceState.getInt("seconds");
            isRunnig=savedInstanceState.getBoolean("runnnig");

        }


        //INICIMAOS LE TIMER

        runtimer();

    }


    //ESTE EMTODO SE LLMARA ANTES DE DESTROY DE LA ACTIVITY(POR EJ LA GIRARALA)

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //guardamos en el bundle las 2 ivars


        outState.putInt("seconds",seconds);
        outState.putBoolean("runnnig",isRunnig);

    }

    //los click!!!:

    public void onclickStart(View view) {
        isRunnig=true;


    }

    public void onclickStop(View view) {
        isRunnig=false;

    }

    public void onclickReset(View view) {
        isRunnig=false;
        seconds=0;

    }

    //para actualizar cada secgundo!!!

    private void runtimer (){

        //obtenemos la ref de la textview con el reloj

        final TextView timeView=(TextView) findViewById(R.id.timeView);






        ///lo metemos en un runnable que se ejecutara cada 1 segundo
        //esto se hace asi paran interferir en le mainthread


        final Handler handler=new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                //HACEMOS UN FORMATA EN HORAS:

                int horas = seconds / 3600;
                int minutos = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format("%d:%02d:%02d", horas, minutos, secs);

                timeView.setText(time);

                if (isRunnig) {

                    //si igue corriendo aumentamos un segundo
                    seconds++;
                }


                //y ahora lo repetimos cada 1000 msecs=1 segundo!!

            handler.postDelayed(this,1000);
                Log.d("timer","1 seg");



            }

        });





    }
}
