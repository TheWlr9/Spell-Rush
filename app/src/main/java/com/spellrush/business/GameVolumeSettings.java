package com.spellrush.business;

public class GameVolumeSettings {
    final public static float DEFAULT_VOLUME = 15.0f;
    final public static float MAXIMUM_VOLUME = 30.0f;

    private static GameVolumeSettings instance = new GameVolumeSettings();
    private static float musicVolume;
    private static float sfxVolume;

    private GameVolumeSettings() {
        musicVolume = DEFAULT_VOLUME;
        sfxVolume = DEFAULT_VOLUME;
    }

    public static GameVolumeSettings getInstance() {
        return instance;
    }

    public static void setMusicVolume(float musicVolume) {
        GameVolumeSettings.musicVolume = musicVolume;
    }

    public static float getMusicVolume() {
        return musicVolume;
    }

    public static void setSfxVolume(float sfxVolume) {
        GameVolumeSettings.sfxVolume = sfxVolume;
    }

    public static float getSfxVolume() {
        return sfxVolume;
    }
}
