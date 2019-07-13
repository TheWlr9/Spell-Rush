package com.spellrush.audio;

import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.spellrush.R;
import com.spellrush.audio.AudioManager;
import com.spellrush.audio.SoundEvent;

import org.junit.Test;

import java.util.EnumMap;

import static org.junit.Assert.*;

public class AudioManagerTest {
    EnumMap<SoundEvent, MediaPlayer> myMap;
    private boolean hasInit = false;

    /*
        Need to call this method from within the other test method, because
        for some reason, the instance variables for testing get released or cleared
        after a test method. So there is no way of reading state of the AudioManager
        without hardcoding it to test AudioManager.init before any other methods.
     */
    public void init(){
        myMap = new EnumMap<SoundEvent, MediaPlayer>(SoundEvent.class);
        AudioManager.init(null, myMap);

        hasInit = true;
    }

    @Test
    public void test_adding_and_removing_songs_adds_and_removes_songs(){
        init();

        System.out.println("Starting test - test adding and removing songs adds and removes songs");

        try {
            AudioManager.addSoundToLib(SoundEvent.TITLE_MUSIC, null);
            assertFalse(myMap.isEmpty());
            AudioManager.release(SoundEvent.TITLE_MUSIC);
            assertTrue(myMap.isEmpty());
            AudioManager.addSoundToLib(SoundEvent.BATTLE_MUSIC, null);
            AudioManager.releaseAll();
            assertTrue(myMap.isEmpty());
        }
        catch (AudioManagerError ame){
            fail("Audio manager has not yet been initialized");
        }

        System.out.println("Ending test - test adding and removing songs adds and removes songs");
    }
}
