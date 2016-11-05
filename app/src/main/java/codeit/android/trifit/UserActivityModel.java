package codeit.android.trifit;

import java.util.Date;

public class UserActivityModel {

    public String name;
    public Date date;
    public float running;
    public float biking;
    public float swimming;
    public float percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getRunning() {
        return running;
    }

    public void setRunning(float running) {
        this.running = running;
    }

    public float getBiking() {
        return biking;
    }

    public void setBiking(float biking) {
        this.biking = biking;
    }

    public float getSwimming() {
        return swimming;
    }

    public void setSwimming(float swimming) {
        this.swimming = swimming;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
