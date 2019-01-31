package com.example.android.doomsday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Random;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    int qtt = 0;
    int ttp = 0;
    String name = "Bob";

    int timer = 0;

    int century = 20;
    int year = 0;
    int month = 1;
    int day = 1;
    int doomsday = 2;
    int doomsdate = 6;
    byte answer;
    String dayName = "a";
    int streak = 0;
    boolean attempt = true;


    public void randomDate(View view) {

        Random i0 = new Random();
        century = i0.nextInt(21 - 19) + 19;

        Random i1 = new Random();
        year = i1.nextInt(100 - 0) + 0;

        Random i2 = new Random();
        month = i2.nextInt(13 - 1) + 1;

        int maxDay = 28;
        boolean leap = false;

        if ((year % 4) == 0 ) leap = true;
        else leap = false;

        int yearName = century * 100 + year;
        String monthName = "a";

        switch(month) {
            case 1: maxDay = 31; monthName = "January"; break;
            case 2: if (leap == true) maxDay = 29; else maxDay = 28; monthName = "February"; break;
            case 3: maxDay = 31; monthName = "March"; break;
            case 4: maxDay = 30; monthName = "April"; break;
            case 5: maxDay = 31; monthName = "May"; break;
            case 6: maxDay = 30; monthName = "June"; break;
            case 7: maxDay = 31; monthName = "July"; break;
            case 8: maxDay = 31; monthName = "August"; break;
            case 9: maxDay = 30; monthName = "September"; break;
            case 10: maxDay = 31; monthName = "October"; break;
            case 11: maxDay = 30; monthName = "November"; break;
            case 12: maxDay = 31; monthName = "December"; break;
            default:
                maxDay = 28; break;
        }

        Random i3 = new Random();
        day = i3.nextInt((maxDay+1) - 1) + 1;



        doomsday = 2 + yearName + (yearName/4) - (yearName/100) + (yearName/400);
        doomsdate = dayOfWeek(yearName,month,day);
        //if (leap == true && month < 3) doomsdate++;
        //if (doomsdate < 0 ) doomsdate = doomsdate + 7;




        switch(doomsdate){
            case 0: dayName = "Sunday"; break;
            case 1: dayName = "Monday"; break;
            case 2: dayName = "Tuesday"; break;
            case 3: dayName = "Wednesday"; break;
            case 4: dayName = "Thursday"; break;
            case 5: dayName = "Friday"; break;
            case 6: dayName = "Saturday"; break;
        }

        TextView yearTextView = (TextView) findViewById(R.id.year_text_view);
        yearTextView.setText("" + yearName);

        TextView monthTextView = (TextView) findViewById(R.id.month_text_view);
        monthTextView.setText("" + monthName);

        TextView dayTextView = (TextView) findViewById(R.id.day_text_view);
        dayTextView.setText("" + day);

        TextView doomsdayTextView = (TextView) findViewById(R.id.doomsday_text_view);
        doomsdayTextView.setText("?????");


        TextView answerTextView = (TextView) findViewById(R.id.answer_text_view);
        answerTextView.setText("What day is it?");

        attempt = false;

    }




    public void sunday(View view){
        answer = 0;
        submitAnswer();
    }

    public void monday(View view){
        answer = 1;
        submitAnswer();
    }

    public void tuesday(View view){
        answer = 2;
        submitAnswer();
    }

    public void wednesday(View view){
        answer = 3;
        submitAnswer();
    }

    public void thursday(View view){
        answer = 4;
        submitAnswer();
    }

    public void friday(View view){
      answer = 5;
        submitAnswer();
    }

    public void saturday(View view){
        answer = 6;
        submitAnswer();
    }

    public void submitAnswer(){
        if (attempt == false){


            TextView answerTextView = (TextView) findViewById(R.id.answer_text_view);
            if (doomsdate == answer)    {
                answerTextView.setText("Right! It's a");
                streak++;

            }
            else {
                switch(answer){
                    case 0: answerTextView.setText("Not a Sunday. It's a"); break;
                    case 1: answerTextView.setText("Not a Monday. It's a"); break;
                    case 2: answerTextView.setText("Not a Tuesday. It's a"); break;
                    case 3: answerTextView.setText("Not a Wednesday. It's a"); break;
                    case 4: answerTextView.setText("Not a Thursday. It's a"); break;
                    case 5: answerTextView.setText("Not a Friday. It's a"); break;
                    case 6: answerTextView.setText("Not a Saturday. It's a"); break;
                    default: answerTextView.setText("Not a Sunday. It's a"); break;
                }
                streak=0;
            }

            TextView doomsdayTextView = (TextView) findViewById(R.id.doomsday_text_view);
            doomsdayTextView.setText("" + dayName);

            TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);
            scoreTextView.setText("Score: " + streak);

            attempt = true;
        }
    }



    public void helpGuide(View view){
        Toast.makeText(getApplicationContext(), "1900 = 3 \n 2000 = 2 \n 4/4 6/6 8/8 10/10 12/12 \n 3/0 5/9 7/11 9/5 11/7\n 1/3.4 2/28.29", Toast.LENGTH_LONG).show();
    }

    public void howToPlay(View view){
        Toast.makeText(getApplicationContext(), "Press 'Random' to generate a random date, guess or calculate what day of the week it is.\n" +
                "If you get it right, score will increase by 1 if not it'll reset to 0", Toast.LENGTH_LONG).show();
    }


    public int dayOfWeek (int y, int m, int d){
        byte t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        y -= (m < 3)? 1: 0;
        return ((y + y/4 - y/100 + y/400 + t[m-1] + d) % 7);
    }



}
