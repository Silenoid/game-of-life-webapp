package com.sileno.gol.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="GAME_STATE")
public class GameState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="SESSION_ID", nullable=false, unique=true)
    @NonNull
    private String sessionId;

    @Lob
    @Column(name="BYTE_DATA", nullable=false, columnDefinition = "MEDIUMBLOB")
    @NonNull private byte[] bytesData;

    @Column(name="CREATION_TIME", nullable=false)
    @NonNull private LocalDateTime creationTime = LocalDateTime.now();

    @Override
    public String toString() {
        return "GameState{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", bytesData lenght=" + bytesData.length +
                ", creationTime=" + creationTime +
                '}';
    }
}
