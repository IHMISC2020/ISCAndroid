package fr.iscgroup.isc.view.activity;


import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

import fr.iscgroup.isc.R;
import fr.iscgroup.isc.model.trials.Trial;
import fr.iscgroup.isc.state.factory.CursorStateFactory;
import fr.iscgroup.isc.view.graphics.pointer.Pointer;

import static java.lang.Math.PI;
import static java.lang.Math.log;


public class IsoActivity extends AppCompatActivity implements View.OnTouchListener {

    //This activity handles the generation
    //of random button position and sizes.
    //It also calculates the duration and difficulty
    //of each trial. Upon reaching the last trial,
    //the activity launches the ResultActivity.

    Pointer pointer;                // the pointer

    TextView button;                  // main button
    ArrayList<Trial> Trials;        // ArrayList to store trial information
    DisplayMetrics metrics;

    public static Handler HANDLER = new Handler();

    long chrono;
    boolean playMode;       //indicates if game has started
    boolean running;    //indicates if stopwatch is running or not.
    int count;      //to keep track of trials
    int screenWidth;
    int screenHeight;
    int smallestDim;
    int posX;       //stores last trial's click X position
    int posY;       //stores last trials' click Y position
    int pauseTime;     //indicates the user's reaction time.
    int numberOfTrials;
    public static Resources resources;  //to access resources from non-activity classes
    int currentButtonSize = 70;
    int r = 250; //large circle radius
    double a = PI / 8; //angle for a distribution of 16 circles
    int x,y;

    Drawable circleClick;
    Drawable circleBasic;
    ArrayList<TextView> circleArray = new ArrayList<>();
    androidx.constraintlayout.widget.ConstraintLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iso);

        this.background = this.findViewById(R.id.background);

        initializeVariables();
        circlePositions();

        // Setup the new background
        CursorStateFactory.getInstance(this).setBackground(background);

        // Pointer creation
        this.pointer = Pointer.initPointer(this,
                findViewById(R.id.pointer),
                this.getWindowManager().getDefaultDisplay());
    }



    //initializes the application variables
    protected void initializeVariables(){
        button = findViewById(R.id.rounded_btn);
        resources = getResources();
        count = 0;
        numberOfTrials = 16;
        Trials = new ArrayList<>();
        running = false;
        playMode = false;
        circleBasic = getResources().getDrawable(R.drawable.circle);
        circleClick = getResources().getDrawable(R.drawable.circle_select);
        getScreenDimensions();
        this.background.setOnTouchListener(this);
    }

    //listener for the main button
    //Checks if the game has started or not.
    //It performs different functions depending
    //on the count and the type of touch.
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(playMode == false){
            if (event.getAction() == MotionEvent.ACTION_UP) {
                playMode = true;
                button.setBackground(null);
                button.setText(null);
                endedTouch(event);
            }
        }
        else {
            if(count == numberOfTrials){
                startedTouch(event);
                count++;
            }
            else {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startedTouch(event);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    endedTouch(event);
                }
            }
        }
        return true;
    }


    //ends a trial
    protected void startedTouch(MotionEvent event){

        double difficulty = diffultyIndex();

        Point size = new Point(0,0);
        this.getWindowManager().getDefaultDisplay().getSize(size);

        Double x = CursorStateFactory.getInstance(this).getState().getCursorLocation().getX();
        Double y = CursorStateFactory.getInstance(this).getState().getCursorLocation().getY();
        if(x == -1 && y == -1){
            posX = (int) event.getX();
            posY = (int) event.getY();
        }else{
            posX = (int) (x * size.x);
            posY = (int) (y * size.y);
        }

        intoArray(count, pauseChrono(),  difficulty, posX, posY);
        if(count == numberOfTrials){
            showResult();
        }
    }

    //starts a trial
    protected void endedTouch(MotionEvent event){
        count++;
        startChrono();

        if(count <=  numberOfTrials) {
            newPosition();
        }
    }

    //difficulté entre le dernier click et le prochain point a atteindre
    protected double diffultyIndex(){
        double distance = Math.sqrt(Math.pow(circleArray.get(count-1).getX()-posX,2) + Math.pow(circleArray.get(count-1).getY()-posY,2));
        return Math.round((log(distance / currentButtonSize + 1) / log(2))
                * 1000000d) / 1000000d;
    }

    //adds a trial to the arrayLis
    protected void intoArray(int count, int time, double d, int x, int y){
        Trials.add(new Trial(count, time, d, x, y, (int)circleArray.get(count-1).getX() + (circleArray.get(count-1).getWidth() / 2),
                (int)circleArray.get(count-1).getY() + (circleArray.get(count-1).getHeight() / 2)));
    }


    //launches result activity and finishes the current activity
    protected void showResult(){
        Intent myIntent = new Intent(this, ResultActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.putExtra("key", Trials);
        this.pointer.removePointer();
        startActivity(myIntent);
    }

    protected void circlePositions(){
        TextView c1 = findViewById(R.id.circle1);
        TextView c2 = findViewById(R.id.circle2);
        TextView c3 = findViewById(R.id.circle3);
        TextView c4 = findViewById(R.id.circle4);
        TextView c5 = findViewById(R.id.circle5);
        TextView c6 = findViewById(R.id.circle6);
        TextView c7 = findViewById(R.id.circle7);
        TextView c8 = findViewById(R.id.circle8);
        TextView c9 = findViewById(R.id.circle9);
        TextView c10 = findViewById(R.id.circle10);
        TextView c11 = findViewById(R.id.circle11);
        TextView c12 = findViewById(R.id.circle12);
        TextView c13 = findViewById(R.id.circle13);
        TextView c14 = findViewById(R.id.circle14);
        TextView c15 = findViewById(R.id.circle15);
        TextView c16 = findViewById(R.id.circle16);

        x = screenWidth / 22 * 10;
        y = screenHeight / 22 * 10;

        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp0.leftMargin = (int) (x);
        lp0.topMargin = (int) (y);
        button.setLayoutParams(lp0);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp1.leftMargin = (int) (x + r * Math.cos(PI/2));
        lp1.topMargin = (int) (y - r * Math.sin(PI/2));
        c1.setLayoutParams(lp1);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp2.leftMargin = (int) (x + r * Math.cos(3 * PI/2));
        lp2.topMargin = (int) (y - r * Math.sin(3 * PI/2));
        c2.setLayoutParams(lp2);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp3.leftMargin = (int) (x + r * Math.cos(3 * a));
        lp3.topMargin = (int) (y - r * Math.sin(3 * a));
        c3.setLayoutParams(lp3);

        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp4.leftMargin = (int) (x + r * Math.cos(11 * a));
        lp4.topMargin = (int) (y - r * Math.sin(11 * a));
        c4.setLayoutParams(lp4);

        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp5.leftMargin = (int) (x + r * Math.cos(2 * a));
        lp5.topMargin = (int) (y - r * Math.sin(2 * a));
        c5.setLayoutParams(lp5);

        RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp6.leftMargin = (int) (x + r * Math.cos(10 * a));
        lp6.topMargin = (int) (y - r * Math.sin(10 * a));
        c6.setLayoutParams(lp6);

        RelativeLayout.LayoutParams lp7 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp7.leftMargin = (int) (x + r * Math.cos(1 * a));
        lp7.topMargin = (int) (y - r * Math.sin(1 * a));
        c7.setLayoutParams(lp7);

        RelativeLayout.LayoutParams lp8 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp8.leftMargin = (int) (x + r * Math.cos(9 * a));
        lp8.topMargin = (int) (y - r * Math.sin(9 * a));
        c8.setLayoutParams(lp8);

        RelativeLayout.LayoutParams lp9 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp9.leftMargin = (int) (x + r * Math.cos(0 * a));
        lp9.topMargin = (int) (y - r * Math.sin(0 * a));
        c9.setLayoutParams(lp9);

        RelativeLayout.LayoutParams lp10 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp10.leftMargin = (int) (x + r * Math.cos(8 * a));
        lp10.topMargin = (int) (y - r * Math.sin(8 * a));
        c10.setLayoutParams(lp10);

        RelativeLayout.LayoutParams lp11 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp11.leftMargin = (int) (x + r * Math.cos(15 * a));
        lp11.topMargin = (int) (y - r * Math.sin(15 * a));
        c11.setLayoutParams(lp11);

        RelativeLayout.LayoutParams lp12 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp12.leftMargin = (int) (x + r * Math.cos(7 * a));
        lp12.topMargin = (int) (y - r * Math.sin(7 * a));
        c12.setLayoutParams(lp12);

        RelativeLayout.LayoutParams lp13 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp13.leftMargin = (int) (x + r * Math.cos(14 * a));
        lp13.topMargin = (int) (y - r * Math.sin(14 * a));
        c13.setLayoutParams(lp13);

        RelativeLayout.LayoutParams lp14 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp14.leftMargin = (int) (x + r * Math.cos(6 * a));
        lp14.topMargin = (int) (y - r * Math.sin(6 * a));
        c14.setLayoutParams(lp14);

        RelativeLayout.LayoutParams lp15 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp15.leftMargin = (int) (x + r * Math.cos(13 * a));
        lp15.topMargin = (int) (y - r * Math.sin(13 * a));
        c15.setLayoutParams(lp15);

        RelativeLayout.LayoutParams lp16 = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        lp16.leftMargin = (int) (x + r * Math.cos(5 * a));
        lp16.topMargin = (int) (y - r * Math.sin(5 * a));
        c16.setLayoutParams(lp16);

        circleArray.add(c1);
        circleArray.add(c2);
        circleArray.add(c3);
        circleArray.add(c4);
        circleArray.add(c5);
        circleArray.add(c6);
        circleArray.add(c7);
        circleArray.add(c8);
        circleArray.add(c9);
        circleArray.add(c10);
        circleArray.add(c11);
        circleArray.add(c12);
        circleArray.add(c13);
        circleArray.add(c14);
        circleArray.add(c15);
        circleArray.add(c16);
    }

    //gives the button a specific location
    protected void newPosition(){
        circleArray.get(count-1).setBackground(circleClick);
        if(count != 1){
            circleArray.get(count-2).setBackground(circleBasic);
        }
    }

    //sets the screen width, height and smallest dimension
    protected void getScreenDimensions(){
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels - getActionBarHeight() - getStatusBarHeight();
        if(screenWidth < screenHeight){
            smallestDim = screenWidth;
        }
        else{
            smallestDim = screenHeight;
        }
    }

    //returns the status bar height
    protected int getStatusBarHeight(){
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        else{
            return 0;
        }
    }

    //returns the action bar height
    protected int getActionBarHeight(){
        TypedValue dim = new TypedValue();
        if(getTheme().resolveAttribute(android.R.attr.actionBarSize, dim, true)){
            return TypedValue.complexToDimensionPixelSize(  dim.data, getResources().getDisplayMetrics()  );
        }
        else{
            return 0;
        }
    }

    //starts stopwatch
    public void startChrono(){
        if(!running){
            chrono = new Date().getTime();
            running = true;
        }
    }

    //returns the running time of stopwatch when paused.
    public int pauseChrono(){
        if(running){
            Date now = new Date();
            pauseTime = (int) (now.getTime() - chrono);
            running = false;
        }
        return pauseTime;
    }
}
