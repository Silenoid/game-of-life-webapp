package com.sileno.gol.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class ServiceResponse<T> {

    private ResponseCodes errorCode = ResponseCodes.OK;
    @NonNull private T responsePayload;

    public void ifNoErrors(Consumer<? super T> action) {
        if(errorCode == ResponseCodes.OK && responsePayload != null) {
            action.accept(responsePayload);
        } else {
            log.error("An error occurred: {}", this.toString());
        }
    }
}
