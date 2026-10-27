package org.infernalstudios.celesteconfig;

public class CommonClass {

    public static void init(double moonWidth, double moonHeight, double sunWidth, double sunHeight) {
        Constants.moonWidth = moonWidth;
        Constants.moonHeight = moonHeight;
        Constants.sunWidth = sunWidth;
        Constants.sunHeight = sunHeight;
    }
}