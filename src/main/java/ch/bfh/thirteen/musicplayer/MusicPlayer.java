package main.java.ch.bfh.thirteen.musicplayer;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.Objects;

public class MusicPlayer {
    private MediaPlayer mediaPlayer;

    public MusicPlayer(String musicFile) throws URISyntaxException {
        Media sound = new Media(Objects.requireNonNull(MusicPlayer.class.getClassLoader().getResource(musicFile)).toURI().toString());
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
