package com.sileno.gol.service;

import com.sileno.gol.population.Evolver;
import com.sileno.gol.population.Populator;
import com.sileno.gol.population.StrategyType;
import com.sileno.gol.model.GameState;
import com.sileno.gol.model.GameStateRepository;
import com.sileno.gol.utils.GolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GameService {

    private final GameStateRepository gameStateRepository;

    @Autowired
    public GameService(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public ServiceResponse<Boolean> saveState(String sessionId, byte[] bytesData){
        boolean isAlreadyOnDB = !gameStateRepository.findBySessionId(sessionId).isEmpty();
        if(isAlreadyOnDB) {
            gameStateRepository.updateSessionByteData(sessionId, bytesData);
            log.debug("Updated data for session: {}", sessionId);
        } else {
            GameState gameState = new GameState(sessionId, bytesData);
            gameState = gameStateRepository.save(gameState);
            log.debug("Saved state {}", gameState.toString());
        }
        return new ServiceResponse<>(true);
    }

    public ServiceResponse<byte[]> loadState(String sessionId) {
        byte[] dataToReturn = new byte[0];
        List<GameState> queryResult = gameStateRepository.findBySessionId(sessionId);
        if(queryResult.isEmpty()) {
            return new ServiceResponse<>(ResponseCodes.NOT_FOUND, dataToReturn);
        }
        GameState gameState = queryResult.get(0);
        log.debug("Loaded state for session {}", sessionId);
        return new ServiceResponse<>(gameState.getBytesData());
    }

    public ServiceResponse<byte[]> generatePopulation(String sessionId, int strategyType) {
        Populator populator = Populator.producePopulator(StrategyType.values()[strategyType]);
        boolean[][] booleanPopulationMap = populator.populate();
        byte[] serializedBytes = GolUtils.serialize(booleanPopulationMap);
        return new ServiceResponse<>(serializedBytes);
    }

    public ServiceResponse<byte[]> forwardGeneration(String id, byte[] serializedMap) {
        boolean[][] booleanMatrix = GolUtils.deserialize(serializedMap);
        booleanMatrix = Evolver.nextTick(booleanMatrix);
        return new ServiceResponse<>(GolUtils.serialize(booleanMatrix));
    }

    public ServiceResponse<Boolean> isSessionPresent(String sessionId) {
        boolean isPresent = !(gameStateRepository.findBySessionId(sessionId).isEmpty());
        return new ServiceResponse<>(isPresent);
    }


}
