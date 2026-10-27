package org.infernalstudios.celesteconfig.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "celesteconfig")
public class CelestialConfigClothConfig implements ConfigData {
    public double moonWidth = 1.0D;
    public double moonHeight = 1.0D;
    public double sunWidth = 1.0D;
    public double sunHeight = 1.0D;
}
