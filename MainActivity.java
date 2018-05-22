package com.android.shubham.braintrainer;

import android.annotation.TargetApi;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button bb;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    TextView timerText;
    TextView  sumtext;
    TextView resultTextView;
    TextView pointsTextView;
    ArrayList<Integer> answers=new ArrayList<>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestion=0;
    Button b5;
    RelativeLayout gamelayout;
    GridLayout grid;

    public void playAgain(View view){
        score=0;
        numberOfQuestion=0;
        timerText.setText(R.string.a6);
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        b5.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {

                timerText.setText(String.valueOf(l/1000)+"sec");
            }

            @Override
            public void onFinish() {

                b5.setVisibility(View.VISIBLE);
                timerText.setText(R.string.sec0);
                resultTextView.setText("Your score:"+String.valueOf(score));
                grid.setEnabled(false);
                grid.setActivated(false);
                grid.setClickable(false);

            }
        }.start();
    }

    public void generateQuestion(){
        Random random = new Random();

        int a = random.nextInt(51);
        int b = random.nextInt(31);
        sumtext.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(90);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = random.nextInt(90);
                }
                answers.add(incorrectAnswer);
            }
        }
        b0.setText(Integer.toString(answers.get(0)));
        b1.setText(Integer.toString(answers.get(1)));
        b2.setText(Integer.toString(answers.get(2)));
        b3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText(R.string.krr);
        }else{

            resultTextView.setText(R.string.inkrr);
        }
        numberOfQuestion++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view) {
        bb.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bb = findViewById(R.id.b1);
        sumtext= findViewById(R.id.sumText);
        pointsTextView=findViewById(R.id.pointsText);
        timerText=findViewById(R.id.timerText);
        resultTextView=findViewById(R.id.result);
        b0=findViewById(R.id.button1);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        b3=findViewById(R.id.button4);
        b5=findViewById(R.id.button5);
        gamelayout=findViewById(R.id.main_layout);
        grid=findViewById(R.id.gd1);
    }
}