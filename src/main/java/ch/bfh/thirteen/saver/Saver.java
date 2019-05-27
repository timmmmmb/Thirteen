package main.java.ch.bfh.thirteen.saver;

import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.model.GameState;
import main.java.ch.bfh.thirteen.settings.Settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * this class saves a game
 */
public class Saver {
    /**
     * saves the game as xml
     *
     * @param game the game that shall get saved
     */
    public static void save(Game game) throws JAXBException {
        if (game.getBoard().getGameState() != GameState.RUNNING) {
            return;
        }
        save("ThirteenSave.xml", game, JAXBContext.newInstance(Game.class));
    }

    /**
     * saves an object as xml
     *
     * @param filename the name of the file that shall get saved
     * @param o the object to save
     * @param context the jaxbcontext of the object
     * @throws JAXBException if an error occurs while saving
     */
    public static void save(String filename, Object o, JAXBContext context) throws JAXBException {

        // create JAXB context and instantiate marshaller
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to File
        m.marshal(o, new File(filename));
    }

    /**
     * saves the settings as xml
     *
     * @param settings the settings that shall get saved
     */
    public static void saveSettings(Settings settings) throws JAXBException {
        save("Settings.xml", settings, JAXBContext.newInstance(Settings.class));
    }
}
