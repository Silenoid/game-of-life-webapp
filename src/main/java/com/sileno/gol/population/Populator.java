package com.sileno.gol.population;

import com.sileno.gol.config.AppConfiguration;

import java.util.Random;

public abstract class Populator {

    boolean[][] booleanMatrix = new boolean[AppConfiguration.MATRIX_SQUARE_SIZE][AppConfiguration.MATRIX_SQUARE_SIZE];
    Random randomizer = new Random();

    public static Populator producePopulator(StrategyType strategy) {
        switch (strategy) {
            case NOISE: return new NoisePopulator();
            case VILLAGES: return new VillagesPopulator();
            case ROADS: return new RoadsPopulator();
            default: throw new IllegalStateException("Unexpected value: " + strategy);
        }
    }

    public abstract boolean[][] populate();
}
