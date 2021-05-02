package com.sileno.gol.population;

import com.sileno.gol.config.AppConfiguration;

public class Evolver {

    private static int mapSize = AppConfiguration.MATRIX_SQUARE_SIZE;

    public static boolean[][] nextTick(boolean[][] booleanMatrix) {
        boolean[][] evolvedBooleanMatrix = new boolean[mapSize][mapSize];
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                int cnt = countSurvivors(booleanMatrix, x, y, 1);
                if(booleanMatrix[x][y]) {
                    cnt--;  // Subtracting the center value occurrence
                    evolvedBooleanMatrix[x][y] = (cnt == 2 || cnt == 3);
                } else {
                    evolvedBooleanMatrix[x][y] = (cnt == 3);
                }
            }
        }
        return evolvedBooleanMatrix;
    }

    private static int countSurvivors(boolean[][] map, int xCenter, int yCenter, int kernelRadius) {
        int ctr = 0;
        for (int x = Math.max(0, xCenter - kernelRadius); x < Math.min(mapSize, xCenter + kernelRadius + 1); x++) {
            for (int y = Math.max(0, yCenter - kernelRadius); y < Math.min(mapSize, yCenter + kernelRadius + 1); y++) {
                if (map[x][y]) ctr++;
            }
        }
        return ctr;
    }

}
