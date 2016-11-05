package codeit.android.trifit;

import java.util.Date;

public class UserActivityModel {

    public int id;
    public String name;
    public String date;
    public String running;
    public String biking;
    public String swimming;
    public String total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getBiking() {
        return biking;
    }

    public void setBiking(String biking) {
        this.biking = biking;
    }

    public String getSwimming() {
        return swimming;
    }

    public void setSwimming(String swimming) {
        this.swimming = swimming;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
