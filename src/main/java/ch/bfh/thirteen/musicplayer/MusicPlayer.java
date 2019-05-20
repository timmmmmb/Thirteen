package main.java.ch.bfh.thirteen.musicplayer;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.Objects;

/**
 * adds background music from a music file to the game
 */
public class MusicPlayer {
    private MediaPlayer mediaPlayer;

    /**
     * constructor for the music player
     * @param musicFile the path for the music file
     * @throws URISyntaxException Checked exception thrown to indicate that a string could not be parsed as a URI reference
     */
    public MusicPlayer(String musicFile) throws URISyntaxException {
        Media sound = new Media(Objects.requireNonNull(MusicPlayer.class.getClassLoader().getResource(musicFile)).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    /**
     * plays and repeats the music
     */
    public void play(){
        mediaPlayer.play();
        mediaPlayer.getOnRepeat();
    }

    /**
     * stops the music
     */
    public void stop(){
        mediaPlayer.stop();
    }

}
