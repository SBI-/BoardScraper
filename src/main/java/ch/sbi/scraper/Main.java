package ch.sbi.scraper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            URL url = new URL(args[0] + "/xml/boards.php");
            InputStream stream = url.openStream();
            stream.close();
        } catch (IOException e) {
            logger.severe("Url failed to open. Reason: " + e);
        }
    }

    public static void setupLogging() throws IOException {
        LocalDateTime date = LocalDateTime.now();
        FileHandler logFile = new FileHandler("logs/" + date + ".log");
        logger.addHandler(logFile);
    }
}
