package com.spellrush.audio;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;
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

    private static EnumMap<SoundEvent, MediaPlayer> soundMap;
    private static Context context;
    private static boolean initialized = false;

    public static boolean init(Context context){
        if(!initialized) {
            soundMap = new EnumMap<SoundEvent, MediaPlayer>(SoundEvent.class);
            AudioManager.context = context;
            initialized = true;

            return false;
        }
        else{
            return true;
        }
    }

    public static boolean init(Context context, EnumMap<SoundEvent, MediaPlayer> cache){
        if(!initialized) {
            soundMap = cache;
            AudioManager.context = context;
            initialized = true;

            return false;
        }
        else{
            return true;
        }
    }

    public static void releaseAll(){
        if(initialized) {
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
    }

    public static void release(SoundEvent key){
        if(initialized) {
            MediaPlayer temp = soundMap.get(key);

            if (temp != null) {
                soundMap.get(key).release();
            }

            soundMap.remove(key);
        }
    }

    /**
     * Method will set up the sound file stored at soundFilePath.
     * This method will overwrite previous sound if a sound for SoundEvent type has already been allocated.
     * @param type The SoundEvent of the sound that you want to add
     * @param resourceID The soundFile in res/raw/
     * @param looping Whether this sound should loop upon playback completion
     */
    public static void addSoundToLib(SoundEvent type, int resourceID, boolean looping){
        if(initialized) {
            MediaPlayer temp;

            //This calls prepare() automatically... Maybe we shouldn't to prepare()? (See class documentation at top)
            temp = soundMap.put(type, MediaPlayer.create(context, resourceID));

            if (temp != null) {
                temp.release();
            }

            soundMap.get(type).setLooping(looping);
        }
    }

    public static void addSoundToLib(SoundEvent type, MediaPlayer raw){
        MediaPlayer temp = soundMap.put(type, raw);

        if(temp != null){
            temp.release();
        }
    }

    public static void play(SoundEvent type) {
        if(initialized) {
            soundMap.get(type).start();
        }
    }

    public static void pause(SoundEvent type){
        if(initialized && soundMap.get(type).isPlaying()) {
            soundMap.get(type).pause();
        }
    }

    public static void stop(SoundEvent type) {
        if(initialized) {
            soundMap.get(type).stop();
        }
    }

    public static void setVolume(SoundEvent type, float newVolume){
        if(initialized) {
            float calcVolume = (float) (Math.log(newVolume) / Math.log(MAX_VOLUME));

            soundMap.get(type).setVolume(calcVolume, calcVolume);
        }
    }
}
