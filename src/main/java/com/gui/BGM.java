package com.gui;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BGM {

    private static final String bgmPath = "./sound/3144.wav";
    private static       Clip   clip;

    static {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(BGM.bgmPath).getAbsoluteFile());
            BGM.clip = AudioSystem.getClip();
            BGM.clip.open(audioInputStream);
        } catch (Exception e) {
            ;
        }
    }

    public static void toggle() {
        if (BGM.clip.isActive()) {
            BGM.clip.stop();
        } else {
            BGM.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

}

