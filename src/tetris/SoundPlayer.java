/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.media.AudioClip;

/**
 *
 * @author ilkay
 */
public class SoundPlayer implements AutoCloseable {

    AudioClip au;
    public SoundPlayer(String file) {
        au = new AudioClip(SoundPlayer.class.getResource(file).toExternalForm());
       
    }

    public void play() {
        au.play();
    }

    public void pause() {

    }

    @Override
    public void close() {
        au.stop();
    }
    
    public boolean isPlaying(){
        return au.isPlaying();
    }
}
