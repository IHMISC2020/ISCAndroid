package fr.iscgroup.isc.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iscgroup.isc.R;
import fr.iscgroup.isc.factory.Factory;
import fr.iscgroup.isc.model.trials.Trial;
import fr.iscgroup.isc.state.factory.CursorStateFactory;
import fr.iscgroup.isc.utils.Configuration;
import fr.iscgroup.isc.view.activity.enums.ActivityManager;
import fr.iscgroup.isc.view.graphics.RecyclerViewAdapter;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ResultActivity extends AppCompatActivity {

    //This activity displays the result page
    //of the app and offers the exporting
    //feature. It also allows to restart the
    //game by clicking the back button.

    RecyclerViewAdapter adapter;
    ArrayList<Trial> TrialList;
    RecyclerView recyclerView;
    Button export;
    ActionBar actionBar;
    int numberOfTrials;
    double a;
    double b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        initializeVariables();
    }

    protected void initializeVariables(){
        numberOfTrials = 16;
        export = findViewById(R.id.exit);
        recyclerView = findViewById(R.id.Trials);
        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);      //displays the back button

        TrialList = (ArrayList<Trial>) getIntent().getSerializableExtra("key");     //obtains the ArrayList passed by the intent
        adapter = new RecyclerViewAdapter(this, TrialList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        displayResults();       //displays the linear regression results.
    }


    //Exports the list information using a string
    public void onClickExit(View v) {
        CursorStateFactory.getInstance(this).getState().stopUpdatingCursor();
        Factory.getConnexionFactory(this).sendData(Configuration.QUITTER_SERVEUR_MESSAGE);
        Factory.getConnexionFactory(this).disconnect();
        System.exit(0);
    }

    public void onClickExport(View v) {
        Factory.getConnexionFactory(this).sendData(Configuration.ENVOI_CSV_MESSAGE + " " + listToString(TrialList));
    }
    public void onClickReplay(View v) {
        ActivityManager.ISO.startActivity(this);
    }

    //calculates the linear regression variables and displays them
    protected void displayResults(){
        double meanDifficulty = getDifficultyAvg();
        double meanTime = getTimeAvg();
        double difficultyStd = getDifficultyStd(meanDifficulty);
        double timeStd = getTimeStd(meanTime);
        double r = getR();

        b = Math.round((r * timeStd / difficultyStd) * 1000000d) / 1000000d;
        a = Math.round((meanTime - b * meanDifficulty) * 1000000d) / 1000000d;
        double r2 = Math.round((pow(r, 2)) * 1000000d) / 1000000d;
    }

    //returns the avg trial time
    protected double getTimeAvg(){
        double meanTime = 0;
        for(int i = 0; i < numberOfTrials; i++){
            meanTime += TrialList.get(i).getTime();
        }
        return meanTime / numberOfTrials;
    }

    //returns the avg difficulty across all trials
    protected double getDifficultyAvg(){
        double meanDifficulty = 0;
        for(int i = 0; i < numberOfTrials; i++){
            meanDifficulty += TrialList.get(i).getDifficulty();
        }
        return meanDifficulty / numberOfTrials;
    }

    //returns the avg trial time squared
    protected double getTime2Avg(){
        double mean2Time = 0;
        for(int i = 0; i < numberOfTrials; i++){
            mean2Time += pow(TrialList.get(i).getTime(), 2);
        }
        return mean2Time / numberOfTrials;
    }

    //returns the avg difficulty squared
    protected double getDifficulty2Avg(){
        double mean2Difficulty = 0;
        for(int i = 0; i < numberOfTrials; i++){
            mean2Difficulty += pow(TrialList.get(i).getDifficulty(), 2);
        }
        return mean2Difficulty / numberOfTrials;
    }

    //returns the avg time * difficulty value
    protected double getTimeXDifficultyAvg(){
        double meanTimeXDifficulty = 0;
        for(int i = 0; i < numberOfTrials; i++){
            meanTimeXDifficulty += (TrialList.get(i).getDifficulty()) * (TrialList.get(i).getTime());
        }
        return meanTimeXDifficulty / numberOfTrials;
    }

    //returns the time standard deviation
    protected double getTimeStd(double meanTime){
        double timeStd = 0;
        for(int i = 0; i < numberOfTrials; i++){
            timeStd += Math.pow(TrialList.get(i).getTime() - meanTime, 2);
        }
        return sqrt(timeStd / numberOfTrials);
    }

    //returns the difficulty standard deviation
    protected double getDifficultyStd(double meanDifficulty){
        double difficultyStd = 0;
        for(int i = 0; i < numberOfTrials; i++){
            difficultyStd += Math.pow(TrialList.get(i).getDifficulty() - meanDifficulty, 2);
        }
        return sqrt(difficultyStd / numberOfTrials);
    }

    //returns the sample correlation coefficient
    protected double getR(){
        double num = getTimeXDifficultyAvg() - getDifficultyAvg() * getTimeAvg();
        double denum1 = getTime2Avg() - pow(getTimeAvg(), 2);
        double denum2 = getDifficulty2Avg() - pow(getDifficultyAvg(), 2);

        return num / sqrt(denum1 * denum2);
    }

    //method that stores the list information in a string
    //to be exported
    public String listToString(ArrayList<Trial> arr){
        StringBuilder builder = new StringBuilder();
        //builder.append("a b r^2\n");
        //builder.append(a + " " + b + " " + getR() +"\n\n");
        builder.append("Trial,Difficulty,Time,X,Y,DestX,DestY\n");
        for (int i = 0; i < numberOfTrials; i++) {
            builder.append(arr.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    //method that is called when the back button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //method that restarts the game when called
    @Override
    public void onBackPressed() {
        Intent intent3 = new Intent(this, IsoActivity.class);
        startActivity(intent3);
    }

}
