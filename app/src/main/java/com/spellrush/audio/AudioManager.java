package com.spellrush.audio;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Collection;
import java.util.EnumMap;

/**
 * See https://developer.android.com/images/mediaplayer_state_diagram.gif
 * for the state diagram of the {@link MediaPlayer}
 *
 * This whole class can be optimized potentially if the preparation operations
 * block too long, by changing them to prepareAsync operations instead.
 * (If that is done, then we might need to check if the MediaPlayer is in state Preparing
 * before calling any operations)
 */
public abstract class AudioManager {
    final public static float MAX_VOLUME = 30.0f;
    final private static String NOT_INIT_ERROR_MSG = "The audio manager was not yet initialized " +
            "before calling: ";

    public static int sfxVolume;

    private static EnumMap<SoundEvent, MediaPlayer> soundMap;
    private static Context context;
    private static boolean initialized = false;

    public static void init(Context context){
        if(!initialized) {
            soundMap = new EnumMap<SoundEvent, MediaPlayer>(SoundEvent.class);
            AudioManager.context = context;
            initialized = true;
        }
    }

    public static void init(Context context, EnumMap<SoundEvent, MediaPlayer> cache){
        if(!initialized) {
            soundMap = cache;
            AudioManager.context = context;
            initialized = true;
        }
    }

    public static void releaseAll() throws AudioManagerError {
        if(!initialized){
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "releaseAll");
        }

        Collection<MediaPlayer> values = soundMap.values();
        MediaPlayer[] players = new MediaPlayer[values.size()];

        players = values.toArray(players);

        for (int i = 0; i < players.length; i++) {
            if(players[i] != null) {
                players[i].release();
            }
        }

        soundMap.clear();
    }

    public static void release(SoundEvent key) throws AudioManagerError {
        if(!initialized){
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "release");
        }

        MediaPlayer temp = soundMap.get(key);

        if (temp != null) {
            soundMap.get(key).release();
        }

        soundMap.remove(key);
    }

    /**
     * Method will set up the sound file stored at soundFilePath.
     * This method will overwrite previous sound if a sound for SoundEvent type has already been allocated.
     * @param type The SoundEvent of the sound that you want to add
     * @param resourceID The soundFile in res/raw/
     * @param looping Whether this sound should loop upon playback completion
     *
     * @throws AudioManagerError If the AudioManager has not yet been initialized, prior to calling
     * this method.
     */
    public static void addSoundToLib(SoundEvent type, int resourceID, boolean looping) throws AudioManagerError {
        if(!initialized){
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "addSoundToLib");
        }

        MediaPlayer temp;

        //This calls prepare() automatically... Maybe we shouldn't to prepare()? (See class documentation at top)
        temp = soundMap.put(type, MediaPlayer.create(context, resourceID));

        if (temp != null) {
            temp.release();
        }

        soundMap.get(type).setLooping(looping);
    }

    public static void addSoundToLib(SoundEvent type, MediaPlayer raw) throws AudioManagerError {
        if(!initialized){
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "addSoundToLib");
        }

        MediaPlayer temp = soundMap.put(type, raw);

        if(temp != null){
            temp.release();
        }
    }

    /**
     * Plays the sound
     * @param type The SoundEvent key that is linked to the resource ID of the sound file
     * @param overwrite Should the sound cancel itself if its already playing and then start again?
     *                  Usually all sound effects should be set to true, otherwise all music
     *                  should be set to false
     * @throws AudioManagerError If the AudioManager has not been initialized yet by calling AudioManager.init()
     */
    public static void play(SoundEvent type, boolean overwrite) throws AudioManagerError {
        if(!initialized) {
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "play");
        }

        //This try-catch block is so our tests don't have to worry about setting up the audio
        //manager before testing
        try {
            if(overwrite){
                soundMap.get(type).seekTo(0);
            }

            soundMap.get(type).start();
        }
        catch(NullPointerException npe){
            System.err.println(npe);
        }
    }

    public static void pause(SoundEvent type) throws AudioManagerError {
        if(!initialized){
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "pause");
        }
        if(soundMap.get(type) != null && soundMap.get(type).isPlaying()) {
            soundMap.get(type).pause();
        }
    }

    public static void stop(SoundEvent type) throws AudioManagerError {
        if(!initialized) {
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "stop");
        }

        try {
            soundMap.get(type).seekTo(0);
        }
        catch (Exception e) {
            throw new AudioManagerError("Could not perform seek on " + type +":" + e.toString());
        }

        if(soundMap.get(type).isPlaying()) {
            soundMap.get(type).pause();
        }
    }

    public static void setVolume(SoundEvent type, float newVolume) throws AudioManagerError {
        if(!initialized){
            throw new AudioManagerError(NOT_INIT_ERROR_MSG + "setVolume");
        }

        float calcVolume = (float) (Math.log(newVolume) / Math.log(MAX_VOLUME));

        soundMap.get(type).setVolume(calcVolume, calcVolume);
    }
}
