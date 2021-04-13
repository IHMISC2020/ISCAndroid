package fr.iscgroup.isc.model.trials;


import android.util.Log;

import java.io.Serializable;

/**
 * Trial class that stores the information for each individual trial.
 */
public class Trial implements Serializable, Comparable<Trial> {

    /**
     * The Trial.
     */
    protected int trial;
    /**
     * The Difficulty.
     */
    protected double difficulty;
    /**
     * The Time.
     */
    protected int time;

    private int posX, posY, destX, destY;

    /**
     * Instantiates a new Trial.
     *
     * @param trial      the trial
     * @param time       the time
     * @param difficulty the difficulty
     * @param posX       the pos x
     * @param posY       the pos y
     * @param destX      the dest x
     * @param destY      the dest y
     */
    public Trial(int trial, int time, double difficulty, int posX, int posY, int destX, int destY){
        this.trial = trial;
        this.time = time;
        this.difficulty = difficulty;
        this.setPosX(posX);
        this.setPosY(posY);
        this.setDestX(destX);
        this.setDestY(destY);
    }
    @Override
    public String toString() {
        return  trial + "," +
                difficulty + "," +
                time + "," +
                this.posX + "," +
                this.posY + "," +
                this.destX + "," +
                this.destY;
    }
    @Override
    public int compareTo(Trial t){
        Log.i("custom",""+this.trial +" and "+t.trial);
        return (Double.compare(this.difficulty, t.difficulty));
    }

    /**
     * Gets trial.
     *
     * @return the trial
     */
    public int getTrial() {
        return trial;
    }

    /**
     * Sets trial.
     *
     * @param trial the trial
     */
    public void setTrial(int trial) {
        this.trial = trial;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public double getDifficulty() {
        return difficulty;
    }

    /**
     * Sets difficulty.
     *
     * @param difficulty the difficulty
     */
    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Gets pos x.
     *
     * @return the pos x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets pos x.
     *
     * @param posX the pos x
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Gets pos y.
     *
     * @return the pos y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets pos y.
     *
     * @param posY the pos y
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Gets dest x.
     *
     * @return the dest x
     */
    public int getDestX() {
        return destX;
    }

    /**
     * Sets dest x.
     *
     * @param destX the dest x
     */
    public void setDestX(int destX) {
        this.destX = destX;
    }

    /**
     * Gets dest y.
     *
     * @return the dest y
     */
    public int getDestY() {
        return destY;
    }

    /**
     * Sets dest y.
     *
     * @param destY the dest y
     */
    public void setDestY(int destY) {
        this.destY = destY;
    }
}


