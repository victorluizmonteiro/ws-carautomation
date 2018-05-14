package com.fiap.carautomation.utils;

public class ConversorUtils {

    public static Double convertMetersToKilometers(Double meter){

            Double calc = meter/1000;
        return calc;
    }

    public  static String getLineSeparator(){
        return System.getProperty("line.separator");
    }
}

