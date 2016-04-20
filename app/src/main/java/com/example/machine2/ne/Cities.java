package com.example.machine2.ne;

import android.graphics.Bitmap;

public class Cities {

    private String name;
    private String temperature;
    private Bitmap icon;

    public Cities() {

    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public Bitmap getIcon() {
        return icon;
    }
}