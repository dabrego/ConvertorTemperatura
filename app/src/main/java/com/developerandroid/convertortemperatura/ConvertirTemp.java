package com.developerandroid.convertortemperatura;

/**
 * Created by mingo on 6/28/16.
 */
public class ConvertirTemp {
    private int temperature;

    public ConvertirTemp(int temp){
        temperature = ((temp * 9)/5) + 32;
    }

    public int getTemperature(){
        return temperature;
    }

}
