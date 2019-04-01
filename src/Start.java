import com.sun.javafx.application.LauncherImpl;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.application.ThirteenPreloader;

public class Start {
    public static void main(String[] args) {
        LauncherImpl.launchApplication(ThirteenApplication.class, ThirteenPreloader.class, args);
    }
}
