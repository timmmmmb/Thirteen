package main.java.ch.bfh.thirteen.loader;

import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.settings.Settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Loader {
    /**
     * loads from file and returns a new game
     * @return a game loaded from the savedata
     */
    public static Game loadGame() throws JAXBException, FileNotFoundException {
        return (Game) load("ThirteenSave.xml", JAXBContext.newInstance(Game.class));
    }

    public static Object load(String filename, JAXBContext context) throws JAXBException, FileNotFoundException {
        Unmarshaller um = context.createUnmarshaller();
        return um.unmarshal(new FileReader(filename));
    }

    public static Settings loadSettings() throws JAXBException, FileNotFoundException {
        return (Settings) load("Settings.xml", JAXBContext.newInstance(Settings.class));
    }
}
