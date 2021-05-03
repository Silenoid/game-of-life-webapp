package com.sileno.gol.population;

import com.sileno.gol.config.AppConfiguration;
import com.sileno.gol.utils.GolUtils;

public class RoadsPopulator extends Populator {
    @Override
    public boolean[][] populate() {
        int roadsNum = (int) (AppConfiguration.MATRIX_SQUARE_SIZE * 0.5) + randomizer.nextInt(AppConfiguration.MATRIX_SQUARE_SIZE);
        for (int i = 0; i < roadsNum; i++) {
            int roadSize = 5 + randomizer.nextInt(20);
            int xRoad = randomizer.nextInt(AppConfiguration.MATRIX_SQUARE_SIZE);
            int yRoad = randomizer.nextInt(AppConfiguration.MATRIX_SQUARE_SIZE);
            while (roadSize > 0) {
                roadSize--;
                xRoad = GolUtils.limitRange(xRoad -1 + randomizer.nextInt(3), 0, AppConfiguration.MATRIX_SQUARE_SIZE - 1);
                yRoad = GolUtils.limitRange(yRoad -1 + randomizer.nextInt(3), 0, AppConfiguration.MATRIX_SQUARE_SIZE - 1);
                booleanMatrix[xRoad][yRoad] = true;
            }
        }
        return booleanMatrix;
    }
}
