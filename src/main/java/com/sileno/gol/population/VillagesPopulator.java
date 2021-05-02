package com.sileno.gol.population;

import com.sileno.gol.config.AppConfiguration;

public class VillagesPopulator extends Populator {

    @Override
    public boolean[][] populate() {
        int villagesNum = 3 + randomizer.nextInt(10);
        for (int i = 0; i < villagesNum; i++) {
            int villageSize = 1 + randomizer.nextInt(5);
            int xCenter = randomizer.nextInt(AppConfiguration.MATRIX_SQUARE_SIZE);
            int yCenter = randomizer.nextInt(AppConfiguration.MATRIX_SQUARE_SIZE);
            for (int x = Math.max(xCenter - villageSize, 0); x < Math.min(xCenter + villageSize, AppConfiguration.MATRIX_SQUARE_SIZE); x++) {
                for (int y = Math.max(yCenter - villageSize, 0); y < Math.min(yCenter + villageSize, AppConfiguration.MATRIX_SQUARE_SIZE); y++) {
                    booleanMatrix[x][y] = true;
                }
            }
        }
        return booleanMatrix;
    }
}

