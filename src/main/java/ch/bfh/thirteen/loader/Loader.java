package main.java.ch.bfh.thirteen.loader;

import main.java.ch.bfh.thirteen.model.Game;

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
    public static Game load() throws JAXBException, FileNotFoundException {
        return load("ThirteenSave.xml");
    }

    public static Game load(String filename) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Game.class);
        Unmarshaller um = context.createUnmarshaller();
        return (Game) um.unmarshal(new FileReader(filename));
    }
}
