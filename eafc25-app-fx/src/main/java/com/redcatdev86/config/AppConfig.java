package com.redcatdev86.config;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    // default (scegli tu: 7 è sensato)
    private final IntegerProperty maxBenchPlayers = new SimpleIntegerProperty(7);

    private AppConfig() {}

    public static AppConfig get() {
        return INSTANCE;
    }

    public int getMaxBenchPlayers() {
        return maxBenchPlayers.get();
    }

    public void setMaxBenchPlayers(int value) {
        maxBenchPlayers.set(value);
    }

    public IntegerProperty maxBenchPlayersProperty() {
        return maxBenchPlayers;
    }
}
