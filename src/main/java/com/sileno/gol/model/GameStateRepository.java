package com.sileno.gol.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GameStateRepository extends CrudRepository<GameState, Long> {

    @Query("SELECT gs FROM GameState gs WHERE gs.sessionId = ?1")
    List<GameState> findBySessionId(String sessionId);

    @Modifying
    @Transactional
    @Query("update GameState gs set gs.bytesData = ?2 where gs.sessionId = ?1")
    void updateSessionByteData( String sessionId, byte[] bytesData);

}
