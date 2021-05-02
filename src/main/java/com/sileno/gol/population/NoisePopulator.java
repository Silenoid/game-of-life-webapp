package com.sileno.gol.population;

import com.sileno.gol.config.AppConfiguration;

public class NoisePopulator extends Populator {
    @Override
    public boolean[][] populate() {
        for (int x = 0; x < AppConfiguration.MATRIX_SQUARE_SIZE; x++) {
            for (int y = 0; y < AppConfiguration.MATRIX_SQUARE_SIZE; y++) {
                booleanMatrix[x][y] = randomizer.nextBoolean();
            }
        }
        return booleanMatrix;
    }
}
