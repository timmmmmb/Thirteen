package main.java.ch.bfh.thirteen.model;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {
    private Media sound;
    private MediaPlayer mediaPlayer;

    public MusicPlayer(String musicFile) {
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    public void play(){
        mediaPlayer.play();
        mediaPlayer.getOnRepeat();
    }

    public void stop(){
        mediaPlayer.stop();
    }

}
