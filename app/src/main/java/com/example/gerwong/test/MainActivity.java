package com.example.gerwong.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("Convert2Lambda")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView2);

        final int max = 7500;
        final int min = 3300;
        final int total = max - min;

        final FluidSlider slider = findViewById(R.id.fluidSlider2);
        slider.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                textView.setVisibility(View.INVISIBLE);
                return Unit.INSTANCE;
            }
        });

        slider.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                textView.setVisibility(View.VISIBLE);
                return Unit.INSTANCE;
            }
        });

        // Java 8 lambda
        slider.setPositionListener(pos -> {
            float result = total*pos/100;
            int rounded = Math.round(result);
            final String value = String.valueOf( (int)(min + rounded *100) );
            slider.setBubbleText(value);
            return Unit.INSTANCE;
        });


        slider.setPosition(0.5f);
        slider.setStartText(String.valueOf(min));
        slider.setEndText(String.valueOf(max));
    }
}
