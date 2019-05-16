import com.sun.javafx.application.LauncherImpl;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.application.ThirteenPreloader;

public class Start {
    /**
     * this is the entry point of the game it starts a preloader(loadingscreen) and when the application is ready it starts the application
     *
     * @param args arguments for the application
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(ThirteenApplication.class, ThirteenPreloader.class, args);
    }
}
