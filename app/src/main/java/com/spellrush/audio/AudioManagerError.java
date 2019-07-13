package com.spellrush.audio;

public class AudioManagerError extends Exception {
    public AudioManagerError(String errorMessage){
        super(errorMessage);
    }

    public AudioManagerError(String errorMessage, Throwable error){
        super(errorMessage, error);
    }
}
