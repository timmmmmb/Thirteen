package main.java.ch.bfh.thirteen.saver;

import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.model.GameState;

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
     * @param game the game that shall get saved
     */
    public static void save(Game game) throws JAXBException {
        save(game, "ThirteenSave.xml");
    }
    /**
     * saves the game as xml
     * @param game the game that shall get saved
     * @param filename the name of the file that shall get saved
     */
    public static void save(Game game, String filename) throws JAXBException {
        if(game.getBoard().getGameState() != GameState.RUNNING){
            return;
        }
        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(game.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to File
        m.marshal(game, new File(filename));
    }
}
