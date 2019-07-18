package com.spellrush.business;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class GameVolumeSettingsTests {

    private GameVolumeSettings gameVolumeSettings;

    private static final String strPrintStart = "\nStarting GameVolumeSettingsTests: ";
    private static final String strPrintFinish = "\nFinished GameVolumeSettingsTests: ";

    @Before
    public void setUp() {
        gameVolumeSettings = mock(GameVolumeSettings.class);
        setMock(gameVolumeSettings);
    }

    @Test
    public void test_set_get_music_volume() {
        System.out.println(strPrintStart + "test_set_get_music_volume");

        gameVolumeSettings.setMusicVolume(20.0f);
        assertThat(gameVolumeSettings.getMusicVolume(), is(20.0f));

        System.out.println(strPrintFinish + "test_set_get_music_volume");
    }

    @Test
    public void test_set_get_sfx_volume() {
        System.out.println(strPrintStart + "test_set_get_sfx_volume");

        gameVolumeSettings.setSfxVolume(20.0f);
        assertThat(gameVolumeSettings.getSfxVolume(), is(20.0f));

        System.out.println(strPrintFinish + "test_set_get_sfx_volume");
    }

    private void setMock(GameVolumeSettings mock) {
        try {
            Field instance = GameVolumeSettings.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
